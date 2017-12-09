Big Data Class 7 - NoSQL Databases Part 2
================================================

Cassandra and ElasticSearch

Mongo is used for NoSQL space, but not analytics

### Review

### Cassandra

[SLIDES]

- Sparse, partitioned row-oriented
- can have lots of columns, but it's not acutally columnar
- Has query language
- No single point of failure or bottlenecks
- Read AND write scalibility (because of no master)
- Tunable consistency

- can replace relational if you don't have many ACID constraints

- Cassandra works similar to Kafka? (w/o the bottlenecks)

- As long as a node is available, you can still access the cluster.

- Bad: cassandra not meant for small columns lots of rows, means lots of files, slower performance.

- Cassandra good for spanning multiple datacenters...knows where to put replicas. ("HDFS has rack awareness, but not data center awareness")

- Organization based on tokens, hashed IDs - row keys

- Token ring, how the token hashes are generated and ensure that things don't have to be moved around or reorganized alot.

- If you're frequently going through your data, cassandra is not the best for that.

- can specify cosnsistency levels for writing data depending on how much consistency you need (maybe just a little bit is okay, but sometimes you need all of it - and this will take longer)

- good for log analysis
- need to denormalize becuase you can treat like a relational db, but you can't do joins (?)

### ElasticSearch

- Not an analytics framework
- Again good for logs
- Meant for search with some additional query abilities

- you can have separate ingest nodes (as opposed to write nodes) if you have ingest intensive processes

- Good use case is put your main data in a Data lake and use elastic search as a search index, it is not a primary data store.

- 32 GB Java heap limit. Because it's java, you can't load too much stuff or it really drags because it's serving everything out of memory.

- DISABLE IPV6 on server where elastic search is running, you don't need it and it causes issues