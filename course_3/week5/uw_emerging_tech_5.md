Emerging Technologies Class 5
==============================


### ELT & Presto

- Presto is for quicker queries over nosql (as opposed to Hive)
- Hive is good for batch, end of collection queries, presto is quicker.
- 

#### ELT

- Example is pig, you parse structure on read
- Spark, Flink, Presto replace pig

* linked docker (mongo, presto, postgres)
* /etc/catalog (presto catalogs)

- Presto workers go to catalog and retrieve the data
- Closest Apache project to Presto is Drill
- Drill is open source version of BigQuery
- Amazon athena is similar and is used with s3 (pay per query)

- presto does not write intermitently to disk like map reduce, so that speeds things up

#### Presto Architecture

- many connectors, one for almost any data store
- presto has steep learning curve and not a big community
- Scraped amazon data (jmcauley.ucsd.edu/data/amazon)
- Airbnb uses presto and they've created a ui for it (Airpal)

#### NiFi

- NiFi for azure data lake
- NiFi docker container - change port before installing
	 - https://hub.docker.com/r/apache/nifi/
- How to connect nifi to something else...?
- nifi dataflow templates: https://cwiki.apache.org/confluence/display/NIFI/Example+Dataflow+Templates