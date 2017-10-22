Big Data Class 1
================

### Course Setup


Spark streaming not in this course, in next one.

Kafka in second course

Covering big data retrieval (like Hive?)

When we talk about hadoop, we'll talk about HBase (hadoop nosql)

MapReduce code is on the way out...
Hadoop is losing favor

Learn to use the technologies beneath Redshift or kinesis

Have to attend 6/10 classes to pass

[break]

### Intro

- Too big to fit on a laptop.
- V's 
- Going to learn how to process 10tb of data in a matter of seconds
- Data is useless if it doesn't meet the SLA (time in which it's expected to be delivered)

[Article] - data engineers: https://www.cio.com/article/3166060/analytics/15-data-and-analytics-trends-that-will-dominate-2017.html

- Most data scientists hate doing the engineering work. They just want to dig into the data.

- every application is a unique snowflake. 

- Types of systems:
    - Batch
    - Real-time/streaming
    - ML
    - Predictive
    - Graph
    - etc.

- Well engineered solution, need to know what the requirements are.

- Micro-benchmarking, benchmarking for components in your system, but no real whole system benchmarks for big data.

- Some attempts to standardize metrics for big data (terrasort data challenge?)

- doing a lot of disk writing? want more SSDs

- ML needs more RAM than Disk space

- Focus on benchmarks that are most important for the job you're trying to 

- Cloud computing has become dominate go to for big data stuff

- Types of cloud: 

    - public
    - private
    - hybrid

- AWS Redshift (data warehouse), Kinesis (kafka) and Data Pipeline (hadoop)

- Until the last year or two, security on these data tools have been poor.

- People hijacking cloud based hadoop clusters and holding the data for ransom

- Largest source of fraud AWS - mining shit-coin from stealing other peoples compute resources

[ASSIGNMENT] - set up instance on azure with virtual machine

- ubuntu (or centos)
- netid##
- SSD
- use keypair
- cheap instance
- only allow ssh on port 22
- set up auto shutdown


https://www.udacity.com/course/configuring-linux-web-servers--ud299