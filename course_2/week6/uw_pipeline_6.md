Building Data Pipeline Class 6 - 20180221
=========================================


## Streaming Processing Part 2

- question: windowing and watermarks...
- there's usually some stragglers, but if there's 20% of events falling outside of your window, there's something wrong with your pipeline
- you're not necessarily doing analytics right on the stream, a little more batching involved.


### Project examples

1. Meetup RSVP analysis
	- how many RSVP per topic per day
	- hearmap or RSVP by City and or country

2. NYC taxi data
	- simulate streaming it
	- current avg ride time/fare to certain points in city
	- compare against historical (actual v. expected)

3. Weather API
	- 1000 queries from dark sky
	- get forcasts for major airports
	- compare current
	- compare historical
	- query API and send to Kafka

4. Retail transactions
	- use data from previous assignment and simulate streaming
	- top products
	- recommendation

5. Bitcoin transaction analysis
	- streaming or batch
	- https://blockchain.info/api

6. Choose your own adventure
	- Wikipedia [https://www.mediawiki.org/wiki/API:Recent_changes_stream]

- Project choice by next week.


### Refresher from streaming data 1

- break things into tiers for managing errors and for performance reasons (can tune things individually rather than working with a monolithic application)
- **Unified Log Model**
	- single event v. multiple event (number of events you look at at a time)

- spark structured streaming is better than spark streaming (it's like dataframe v rdd)
- spark structured streaming is like an ever growing database table

- Outputs
	- Append - add new rows since last trigger
	- Complete - output everything (not ideal)
	- Update - update rows that changed since last trigger

### Spark structured streaming

- Sent over the wire, the key and value are sent and received in SSS as a binary (normally would not just process as binary, have to transform, cast to string add to separate dataframe (don't need other stuff)

- only works with console, {File???}, and kafka sources


### Single Event Stream processing

- First step
- Fast, (no waiting for windowing)
- Fault tolerant, 
- not complicated don't need a specific framework (if you don't need something like spark)

- **Reco** Don't optimize up front, use many stages that do different things, not one big one that handles everything all at once
- **Reco** get things working one thing at a time
- Goal is to process records as fast as they are coming in (basically one to one): can you handle 1000 events a second if that's the rate they are coming in?

### Multiple event stream

- Need a way to persist state (knowledge of history)
- Remote storage as cache, not as durable storage.
- State (e.g., store 60 min worth of data if that's the level I am analysinv at. State of the data is the 60 min window.)

- Sliding window:
	- each window has current window and previous window
	- keep track of which window the events belong to
	- overlapping windows

- event time v stream time (processing time)
	- stream time is when event arrives at processing engine

- What happens if events are "late"

**Don't use Kafka for transactions (no transactional gaurantees and atomicity). For analytics really.**


- Delivery semantics again
	- At least once (like sms, might get dupes)
	- At most once (no dupes, might lose)
	- Exactly once (does this exist?)

- Kafka only gaurantees at least once
- Exacltly once needs a processing framework:
	- Spark streaming or Flink.
	- Not kafka streams, storm or samza.

### Kafka Streams & Flink

- Stream processer from kafka is a little less powerful than spark and flink, but more simple
- Just need Kafka, nothing else for orchestration
- handles delivery and parallelization
- Stream v table -> Insert v Update
- KStream for stateless series of events
	- No dependance on previous records
	- What is the current value now
- KTable for data that is changing overtime
	- e.g., sensor that updates a previous value

- KStreams - tumbling and sliding windows, no Python support (for now)
- KStreams good for single or simple multi-event, goot for writing data back to kafka after processing
- code fumctional, similar to spark

### Flink

- everything is  a stream
- batches are finite streams
- flink is just much smaller and middling, not super active. no python support