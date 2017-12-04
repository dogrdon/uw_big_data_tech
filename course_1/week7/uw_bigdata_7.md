Big Data Class 7 - NoSQL Databases Part 1
================================================

### Review

- uc san diego has a repository of machine learning data.

- common workflow: 
	- process data, get it down to something smaller
	- then load into R, or Scikit

- ["awesome datasets"](https://github.com/caesar0301/awesome-public-datasets#climate-weather)

- Cassandra stuff not part of spark. Connector in Cassandra that supports Spark, but not the other way around.

### NoSQL

- Back to CAP Theorem (Brewers theorem)
- Availibility, Consistency, Partition tolerance...pick 2
- RDBMS = CA
- You don't always need Consistency, (analytics v. core, master data - i.e., don't always have to have the latest view)

- ACID:
	- Atomic, consistent, isolation, durable
	- Hard to enforce in distribution

- BASE:
	- Relaxes ACID
	- BA: basic availibility
	- S: Soft State
	- E: Eventual consistency (strong or weak) - relax consistency gaurantees, so you can have a distributed system w/o worrying that everything has to be up to date everywhere
	- 


- HIVE is kind of a no sql database, but it's not really a database

- Some people consider memcache as a nosql database (even though it's not)

- NoSQL is just "Not only sql", ways to store data that don't require SQL and a schema.

- Data Modeling tools starting to support nosql

- Egg book talks about how to model data (modeling notation) across programming data, rdbms, and nosql

- Databases that are entirely memory?
- Cassandra is eventually consistent, but it can be stricter.

#### Where NoSQL excels

- gradebook assignment storing data
	- Week 1
		- student
		- text box
		- grade
    - Week 2
    	- student
    	- multiple choice
    	- grade
    - Week 3
    	- student
    	- file upload
    	- description
    	- jar file
    	- grade

- you can't incorporate this into an RDBMS without adding a lot of new tables, or having a complicated schema.

- but for HBASE, you can have a more flexible schema....all in one "row", each row does not need same schema
- storage, there's not Headers with null values if no entry, there's nothing there if no data.
- supports horizontal scalibility.
- verticle scaling is making it bigger on one server (typical RDBMS approach)


#### Redis "It's simple"

- Very fast
- database, cache or message broker
- its an in memory database
- key/value store
- can be used with pub/sub event bus type thing
- can do geospatial queries
- can use as cache because keys can expire, time out session after an hour (for example)
- Clustering in redis is kind of like automated sharding, but it can be a little flakey
- determin which node a record is in by finding the hash slot for you (if it's not in the node you've been connected tools)

- hashes are tables in Redis
- key and the value can be JSON
- you can store sorted sets (get the top 10 scores for every hour at a game company)
- usually something like redis is not your only datastore, for instance it only stores flexible profile data, but not all user data, that goes into an RDBMS - primary key stored in RDBMS


#### HBase

- was google bigtable
- needs an HDFS
- Like HIVE adds schema to HDFS files, HBASE creates a database for HDFS cluster
- data stored in actual cells, not a large in memory hashmap like in redis
- no schema upfront enforced, up to the client to use data correctly
- tracks history of updates to a cell, versioning of cells
- Constent and available (but it's CP in CAP)

- Includes - HMaster, Zookeeper (coordination), Regoin server, apache pheonix (sql skin)

- region servers are nodes?

**MULTIdimensional hashmap** - columnular
- column families organize storage for a family  on disk
- column is just a qualifier for a value
- similar to cassandra (which is next week)
- Cassandra is better for write heavy workloads and better for querying (version 3 has a query language) - Hbase does not have a query language
- multidemnsional key to represent cells
    - (key, col_fam, col, timestamp) -> value.
    - w/o timestamp, just get the latest

- at 10GB, hbase will split your table into two different region servers
- Hbase does not handle sequential writes well, all writes go to the latest server. Hotspotting!
- Cumulo? Like Hbase
- Fix sequential, change the key to a hash (not a sequential number). Then it will distribute data better.
- So don't use timestamp
- Facebook used Hbase for messaging tool
	- each user was a row and each column was a message
- Pinterest largest Hbase usage in the cloud
- you create column families when you create a table, to modify you have to disable the table, then bring it back up and then it has to shift things around.
- so use 2-5 column families, not more than that, otherwise lots of overhead
- datastax is paid cassandra

- First query metadata table (ask zookeeper) for which region record is in , the check region for record
- region server keeps new record in memstore, then will flush memstore to file on hdfs
- if there is a crash, has to replay transaction log
- Writes are very slow using Phoenix, don't use for writing data
- Can scan and filter (row by row)
- Deletes, doesn't actually remove except with a major compaction (only marked as deleted, soft delete)

- Good: low latency data access w/ updates
- Good: analytics with large scans
- Bad: can't do write heavy loads well
- Bad: data written and access sequentially
- Bad: can't handle Large Cell values, works better with many small cell values

- Cassandra better if you don't have a database otherwise

- can use Hbase in sandbox