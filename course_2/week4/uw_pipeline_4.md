Building Data Pipeline Class 4 - 20180131
=========================================

## Streaming data & Kafka

### ASSN No. 1

#### exploring data

- is there a standard (ietf standard for emails in this case)
- needed to handle the to: field separately (because there were new lines after 'to:')
- don't rely on regular expressions (java doesn't handle them very well) - in python it's  alittle better

### ASSN No. 2

- denormalize = we don't want to do joins so let's put everything together before putting into our hdfs (put all information we want in one record - this will make everything work better)

- point is extract the data so we can do advanced analytics, queries of interest are more performant



### Streaming Data

- Real-time v streaming (not the same)
- Streaming = data into system in real-time, but it's consumed when needed
- Delivery requirements ("only once, at least once etc."?)
- streaming has tolerance for delay (unlike real-time)
- edges
- is there more processing at the edge?
- generally it's ok not to do the processing at the edge, minimal processing at collection.
- api for accessing data.
- without messaging and collection, if things crashed during analysis, how do you recover?
- collection and messaging decouples analysis from source and provides a place to replicate from, and keep things from backing up.


#### Delivery semantics

- at most once (no dupes, might lose some, sent and that's it)
- at least once (might be dupes, but ensured to get record at somepoint - sent until received, application needs to know there might be dupes), collection and messaging working together.
	- message queueing tier is where things are saved and buffered (staged)
- exactly once (does not exist?)

#### Time w/i streaming applications

- time more important in streaming:
	- streaming time into system (when recieved)
	- event time in the records itself (when it actually happened)

### Kafka

- reliable streaming (not just messaging)
- defacto
- AWS built something just like it.
- has storage like a database (under topics)
- best for coordinating distributed systems

#### Sequential v random processing
- computers like sequential better
- sequential io is much faster
- hdfs does not allow modified files, because it's handled sequentially.


#### Best use cases

- data pipelines for moving data between systems or applications
- applications that trasnform or react to streams of data in near or real time

- Kafka topics are sequential read/write. Constantly appending to file constantly.

- read from offset till the end
- adds reliability (better than most pub/sub)
- handles paralellism (way better than most pub/sub)

- kafka allows horizontal partitioning (Like hdfs) of streamin data.

#### key to kafka

- **cluster** (1...n servers)
- **clients**
	- producer: client that sends data
	- consumer: client that recieves data
- **record**
	- is a message
	- (key, value (content), timestamp (when recieved))
		- key is not offset
		- value is binary (array of bytes) - can send anything: images, whatever
- **topic**
	- groups of related records
- **partition**
	- machinism for distributing data in a single topic across multiple machines

#### offset

- if a new consumer subscribers, they can listen to any point in the stream, they can start from the beginning and replay, or start at other offset.
- or you drop the stream at offset n, you can pick back up from there.
- framework should handle picking up where you left off, has information about consumers

#### Topics
 
- Topics are partitioned log files
- writes immediately to disk
- each partition is immutable set of records
	- appended to end only
	- **Each record identified by an offset INSIDE EACH PARTITION, not over all across all**

- can replicatte records across nodes
	- leader/follower
	- only one node handling read/writes
	- automatic failover

- multiple partitions in multiple nodes (no hard relationship between partitions and nodes, can configure as needed)

#### Producers

- which partition for each record
- "key" is partitioning key (key is locator within different partitions)
	- hash partitioner, like object store records w/ same key go to same partition
	- if no key, picked randomly and sent to that record for a period of time.
	- equal distribution across cluster over time, but will take a while

#### Consumer

- multiple consumers grouped together
	- records delivered once per group
		- all consumers in same group for load balancing
		- consumers in different groups to act independantly

- each spark streaming application is a consumer group: received only one time
- each partition area all in sequential order
- only way to have total order gaurauntee only from kafka is to have only one partition
- why would you split up topics on partitions (bc one topic might not fit on one machine)
- kafka can handle millions of records a second - 
- how to gaurantee the order across two partitions? other services/frameworks
- often times, your window of analysis does not require total ordered.
- **total ordering** 
	- logic in consumer to order things for you
	- only one partition

#### Consumer performance
- scales automatically
- kafka manages no state
- efficient linus utilization

#### Typical useges

- MISSED ONE (SEE SLIDES)
- can be used as storage system
	- data gauranteed to disk
	- replicated
	- scaling
	- replaces hdfs??
	- * not recommeded for long term, it's hard to get out (store infrequently used to disk)

- real time straem processing

**Kafka keeps track of what's been read, but up to specific client to figure out what to do with that information**

### Using Kafka

- Mostly next week, but a little bit now.

#### Cluster sizing
- scales linearly: increases linearly as you add more nodes
- consider hardware, network, data size
- At least 3 nodes

- most data served in and out of memory

- message size should be 1k optimally at 800k/1m records a second

#### Topics
- business rules, not parallelization
- keep joinging things from two topics? Just put all in one topic

#### Replication
- for durability, not performance
- one leader only
- sync v async
	- synchronous (at least once?), more the 1 will result in performance drop
	- asynchronous, at most once (fire and forget)

**ACK** = acknowledgement or signal that something was received?

#### Paritioning
- parallelsim for r/w
- writing 
	- even dist
	- **Kafka is basically a nosql db**
	- make sure actual key is evenly dist. 
- reading
	- same key on same partition??
		- don't do joins a lot among topics


(GOT REALLY DISTRACTED REVIEW LAST 20 MIN of recording)

### Lab

- requires zookeeper

### Extra lab

- use kafka python to write a simple producer (read in file and send to kafka)
