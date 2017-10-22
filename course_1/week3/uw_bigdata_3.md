Big Data Class 3 - Spark 1
==========================

### Review

- These are new CPU burst machines, but they might not be working properly.
- Leading to inconsistent installations]
- sudo service docker restart (unless docker is in a really fucked up state)
    - so then you have to kill the docker
    : `ps -ef | grep 'docker'` for /usr/bin/dockerd
    : 

- `du -sh *` to see top level dir sizes in a directory

- cleaning up docker volumes: https://lebkowski.name/docker-volumes/
- cleanup: https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes

### Using Spark pt. 1

- fast and general engine for large-scale data processing

- operations for store transfer and transform data

- not just queries, not just transformation

- spark should be run distributed, for massive datasets. Few megabytes to 100 of gb to terabytes of data

- hadoop is awkward, spark meant to be general purpose

- spark is: spark sql + spark streaming + MLib (machine learning) + GraphX (graph)

- process data inflight, don't have to store it

- Kafka is messaging, not processing

- code for doing a little job or a huge job will look the same

### What is in Spark

- driver is the main part, the orchestrator

- driver figures out how to run your code

- executor does all the work. 1 or more. 

- you can run spark on a laptop. mac or linux.

- or you can run on a cluster. have the driver run on a process or on a node within a cluster. 

- if you run driver and the machine crashes, everything we be wasted. if you run driver in cluser, outside the client, it's like screen you can come and go closing the cluster.

- cluster will always be available

### Functional programming

- function mapped over a data structure
- functional making use of immutability, no state, so good for distributed systems
- anytime you modify something in spark, you are doing an in memory tranformation and outputting an new thing (immutable)
- define inputs and outputs and spark figures out the path through
- tell it what functions you want to run, and spark figures out what order to run it in

### RDD

- Resilient Distributed Dataset
- fault tolerant
- partitioned across cluster
- can be text, or key/val, or custom
- They are IMMUTABLE!!
- For an RDD, you probably want it to be homogenous, not mixed data types

- lazy execution: waits to do what you said until some condition happens, might do same transformations on one rdd, even if you specify actions on new rdds each time

- stateless: operations don't depend on eachother. can do in any order

#### creating rdds

- can generate in memory
- can load from text file on file system
- can load file from hdfs
- i'm sure load from database
- don't do things that pull data from the driver, will crash driver
- things like `a.take(20)`, you can sample a representation, but don't want to crash your driver

