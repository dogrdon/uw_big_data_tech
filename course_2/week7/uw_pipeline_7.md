Building Data Pipeline Class 7 - 20180228
=========================================


## Landing data for analysis - Access

- mostly creating access, not doing the analysis
- raw data in a data lake?
- online or offline?
- interactive applications?
- Which data formats to use?

### Raw Data

- HDFS still the gold standard for raw data lakes
- But HDFS cluster in the cloud is difficult to keep up and is costly.
- but it's more performant than an object store (s3)
- Alluxio: abstraction over different file systems (used to be Tackion - file system for spark), unified abstraction over many different file stores. Virtual fs over s3, hdfs, google thing, open stack
- Kafka connect can take data from kafka and shove it into s3 or hdfs.
- AWS Kinesis is kafka, will do the same thing but with s3
- What formats?
	- parquet (file format)
	- avro (protocol buffer - creating and object for the data, must create a schema - its properly typed, google uses it all over the place - can be used to store and serialize and make more efficient than working with raw json, issue might be that putting JSON to RDD is that it splits it arbitrarily, w/ avro it would not be splittable, but the schema helps to use the serialized form of it (???))
	- text
- For compression (decompressing does not add that much overhead anymore), default is to compress...
	- snappy/lzo
	- gzip


#### More on formats

- parquet on spark will work better than spark and orc

<MISSED PART ON HOT/COLD DATA>

- hive, bucketing, parquet partitioning respected? supported?

<MISSED Part about how to write to HDFS from streaming (If you can't keep writing to same file)> is the answer partitioning...?

- spark -> data warehouse with JDBC

- Custom sync (output)? (scala only?)

- no structured streaming JDBC
- give structured streaming a directory and new file for each batch ("small files problem" - each executor writes out its own file)

- generally speaking partition by date in parquet (can get parquet to parittion automatically from spark)


#### NoSql

- Can be "online" live data too, not just persistence

- Spark doesn't write to NoSql out of the box...Spark-packages.org

### Architecture

- (2014) simple streaming service. log -> flume (log aggregator) -> kafka & hdfs -> spark -> elastic search

** keep raw data because if indexer crashed and lost data, need to reaggregate

- (2018) updated: cassandra db after spark, no more flume, still HDFS for raw, and solr instead of elastic search (solr only does indexing, no search. does indexing reall well.)

- redshift spectrum to query an s3 (only parquet or csv)
- elasticcache (in memory lookups, redis, only last hour or so for dashboard)


#### Lambda arch

- go back to this