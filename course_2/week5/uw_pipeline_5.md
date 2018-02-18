Building Data Pipeline Class 5 - 20180207
=========================================

## Kafka 2


### Initial questions

- Don't use network storage for Kafka


### Week 3 Assignment Recap

- Should have cached the dataframe
- Bonus:
	- could have just used the quantileDiscretizer



### Unified Log Model

- Kafka is an implementation of ULM
- Stream of discrete events ordered by time
- Consumers should use every item in a topic. If not, maybe there's too much in the topic
- Rolling window, you don't want Kafka keeping every record ever (archive old stuff to HDFS, for example)
- (least current) archiving behind anaylytics behind alerting (most current)
- Complex event processing (multiple event processing) - usually a vendor thing (SAS?)

--- break ---

### Spark Streaming (deprectated)

- rdds or dataframes for unbounded tables
- flink, in comparison (non-structured streaming?) is a little more difficult to use than spark streaming
- now there is a `streamingContext`
- Dstream - rdd for streams, another abstraction
	- can cache
	- can checkpoint

- Spark streaming is many many small batches, micro-batches
- every 50ms, lots of overhead
- same API calls on DStream as RDD (but not everything is available) - use `transform()` function to get access to full RDD API
- Can join two streams together

#### Windows 

- Microbatch is a "tumbling window" - don't know what happened in previous or next window
- Sliding window lets you look at overlapping batches, processing multiple batches at once
- Do sliding windows with structured streaming (to avoid writing too much code)
- maintaining state across batches...in memory database (cache), put in a file, `mapWithState`, keep a running total or keep track of a maximum value so far.

#### Sources

- fileStream
- textFileStream

** No custom receiver in Python
** Newest Kafka API does not support Python (for spark streaming)
** I think the message here is that Spark Streaming made an attempt to do streaming, but it's already on the way out - Spark Structured Streaming is the way forward.
- use Kafka pre-.10 api (gaurantees exactly one, supports python, it's easier to use)
- direct approach pre-0.10 api

### Spark Structured Streaming (use this one)

- Difference between arrival time and event time
- unbounded dataframe
- handls event time out of order, lateness (watermark)
- continuously executed
- keeps everything in memory, but you can't do that bc application will die eventually
- Watermark - everything prior to the watermark, just drop it (everything after 1 hour, 2 hours, 1 day etc.)

- CAN'T: do distinct (yet)
- CAN'T: join two structured streams, only join structured stream to regular dataframe
- Only stream from files or Kafka
- Data needs to have a schema - no graph, no ML for structured streams.
