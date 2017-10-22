Big Data Class 2
================

### Review

- There is one book that's close to a text book.
- back to benchmarks: 
	- TPC www.tpc.org
	- specifications and tools for sample data generation
	- not good from marketing perspective, but is good for 
	- Also [YCSB](https://github.com/brianfrankcooper/YCSB) for NoSQL database benchmarking

### Hadoop and the Hadoop ecosystem

#### Big Data 

- process a set of log files
- serial processing is slow
- what's the state of everything when it crashed out
- e.g. audit transactions with 20, 30tb of data

- cloudera puts a spin on the products the provide as a service

- using hortonworks version of hadoop provides a little vendor lock in.
- if you build your hadoop with one providers tools, it might be hard to migrate
- Ambari is behind cloudera, playing catch up with cloudera cluster manager. We'll use ambari in this class

#### Big data approach to the transaction logs with HDFS

- big files in hdfs, which splits for you 
- use map reduce engine to process each chunk and aggregate results
- Problems:
	- code is complex
	- operational complexity
- 1tb file -> 256mb parts. 
- logically it looks like one file but it really is broken up and stored in different locations
- HDFS is not a linux fs, it's a software product on top of the file system
- **Name node** manages the metadata for each bit. maps the file system to the distribution of the files
- **Data nodes** store file blocks on fs, replicate blocks to multiple datanodes for redundancy.
- Multiple name nodes for HA = high availiblity and redundancy
- HDFS makes 3 copies (original and two copies on blocks?)
- hdfs writes are slow and there is alot of network traffic involved bc you're brining your code to the data (but if evens out because it's happening in parallel)

- [QUESTION] Does hdfs require having more than one machine? do hadoop jobs make sense on one machine ever?

- Issues with HDFS: slow, replication requires lots of space, file size limits

#### MapReduce

- map reduce doesn't really have good use cases anymore
- should use spark
- storm, flink, kafka streams are a replacement for MapReduce (streaming processing v. batch processing)
- Spark is aimed at batch processing
- hadoop was built as an ETL tool on large datasets with cheap hardware

#### Hive

- sql type work in HDFS
- strucutures unstructured data
- can use MapReduce engines, Tez, Spark
- *have to write MapReduce in Java, sorry*
- Tez, similar to spark
- can also use spark w/ hive
- has impala taken over from hive? No. it's proprietary
- using hive, you can have other applications read from a hive store (impala can't do this)
- hive is putting a table on top of data stored as is in HDFS
- hive stores as columnar
- ddl
- orc (optimized row column) files? hive optimized files
- or avro
- partition table base on what you query the most