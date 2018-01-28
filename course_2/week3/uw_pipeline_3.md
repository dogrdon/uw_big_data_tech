Building Data Pipeline Class 3 - 20180124
=========================================

### Assn recap

- If experiencing performance issues, bump driver memory up to 8gb (that will also increase executor mem)
- if you get warning about task size being too large, it's not a big deal, it's just a warning.
- again, have less larger files than more smaller files
- in talking about assn solution, we'll learn ways to better understand how to split up the files (close to a million messages)
- in testing: start with a sample of the data, not the full thing.

### Batch v Stream

- most analytics still batch oriented.
- can treat batch like a stream
- Logs can be best down with streaming now (but it's classically a log issue)
- Store logs in a relational database?
- Streaming does not replace RDBMS
- You could have a massively parallel RDBMS if you'd rather spend money on infrastructure rather than data engineers

### The modern data warehouse

- Use EDW and Big Data together to their individual strenghts

### Data lake v data warehouse

- reducing work in ingesting data to get data in
- into something horizontally scalable (hdfs, for example) - so don't worry about data
- then worry about performance on access (schema on read)
- generally made up of multiple big data technologies (hdfs, x-swift, hbase cluster)
- data lake is more like a junkyard than a well organized warehouse (where items are labeled and placed in specific locations)
- data lake can be a staging location for further etl (which may then go to sql db or not)
- data lake can extend usefulness of a data ware house.
- alternative to data lake is mpp database (but still for structured data)
- mpp is a distributed sql database...no real open source alternatives (e.g., terradata, greenplum database), requires specialized hardware
- data lake can also be archive (cheap to store large amts of data)
- finally it can be used to ingest data formats you have not seen before

#### Data Lake Zones

- Temporary, transient
- Raw, staged
- Curated (cleaned and ready for other systems)
- Analytics Sandbox

#### Other data lake considerations

- Can be everything that is not your data warehouse
- Can be older data that doesn't get queried regularly by analysts

#### Other stuff

- Amazon Athena - S3 data lake, sql on S3 data store.


### Spark in Data Warehouse - integrating with batch data

- Spark not meant to be an ingest tool for large data into an RDBMS (jdbc driver not great)
- better to use to push things out to data warehouse
- NiFi (not meant for large raw data out of some source)
	- meant for streaming flows
- Sqoop: reads data out of SQL and puts into HDFS (that's it)
	- Its simple
	- uses hadoop & map/reduce
	- Sqoop2 tries to do too much

- hdfs for data staging is best (s3 good too, but look out for cost)

### Part about different data formats

< GO BACK TO THIS IN SLIDES >

- Avro good for serialized transport and streaming, not great for backend analytics.
- Good for batching up and compressing

### Assignment

- RFM analysis (simple way of clustering - machine learning has better approaches). Scoring the value of a customer.