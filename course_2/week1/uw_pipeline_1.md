Building Data Pipeline Class 1
==============================

### Course Setup

- Put things together
- Apply fundamentals to complex situations


### Intro by way or recap

- cloudera
- hortonworks
- mapr (proprietary, rewrote everything, api compatible)
	 - not sold on this becase we're not using hdfs anymore?

- so what are alternatives to HDFS, we used hdfs a lot last term. Should I have been using something else?


### What you need to know to proceed

- hortonworks sandbox tutorial again:
	http://hortonworks.com/hadoop-tutorial/learning-the-ropes-of-the-hortonworks-sandbox

- proxies forwarding ports into sandbos from VM
- Rogue jupyter processes
- shutdown unused services
- /usr/hdp/current...
	- this is where the executables are
	- like this is where spark is
	- e.g., scoop

- hdfs

	- namenodes vs datanodes
	- hdfs://localhost:8020/....
	- splits large files to smaller 256 mb blocks by default
	- better to have less larger files than many more smaller

- other ecosystem things:

	- zookeeper: manages state across distributed applications, a distributed locking tool
	- hbase: online key/val store on hdfs
	- hive: structured data support for hdfs
	- yarn: resource manager for distributed applications
		- yarn.scheduler.capacity.maximum-am-resource-percent 

#### SPARK

- client, driver and one or more executor
- driver and client can be the same
- client starts application, if clientexits, whole thing goes away
- driver has spark context, scheudler,spark application
- don't collect a large amount of databack to driver, it will crash andeverything will die.

#### RDDs (spark)

- Actions: process RDD and return a result
- creating with spark context.

- RDDs how work
	- Lazy execution (execution graph) -> jobs -> stages  (of jobs) -> tasks ( individual units of excecution )

#### Other spark notes

- Note: use SparkSession as a standalone appliction (don't need spark context)

- use spark ui port 4040 to see the usages and plan for the queries

- spark is defacto standard for batch processing

- really understand spark and how it works and other things like Kafka and Flink will make sense.

- Spark still playing catch up on streaming.

#### NoSQL

- see slides for dimensions you want to consider when selecting a NoSQL solution


### Azure cli

INSTALL
then:
`az login`
`az vm start --resource-group Big_Data_Technologies  --name asgor01`
`az vm list-vm-resize-options --resource-group Big_Data_Technologies`

in az tools you need to deallocate, not just stop. stop just restarts.

`az vm resize --resource-group Big_Data_Technologies --name asgor01 --size Standard_A4m_v2`