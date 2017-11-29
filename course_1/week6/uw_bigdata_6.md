Big Data Class 6 - Big Data Management and NoSql
================================================

### Review

- week 8 (graph dbs & ML) will be recorded
- assn 4: 
- imports:

	import org.apache.spark.sql.SparkSession
	val ss = SparkSession.builder.getOrCreate
	import org.apache.spark.sql.functions._
	import org.apache.spark.sql.types._
	import org.apache.spark.sql.Row

- Docs: https://spark.apache.org/docs/2.2.0/api/scala/index.html
- A dataframe is a Dataset (org.apache.spark.sql.Dataset) (collection) of rows, a dataset where the type is row
- Row is a row of
- output form a DataFrame
- problem with using `long`:
	- change the name at source
	- change it in the data (but maybe too many files)
	- don't encode it as a dataset (scala only) - `withColumnRenamed` - `as [HomeData] (class)`
- Bug with working with datasets in Toree kernal in jupyter (but will work in spark shell)
- PROTIP: `.persist` - if you're using the same data for multiple for applications in the notebook...cached, so subsequent queries are faster. Without persist, each time you call the data, it's pulled into memory, completed then thrown away. Reads from disk each time. Otherwise it's just a reference to something on disk.
- Won't let you cast on something that will lose precision on the data field
- `groupBy` givers you an aggregated dataframe, then you can do an average. 
- persist derivatives of your dataset, the things your really want
- **checkpointing and persistance**: writing persistent cache data to disk.

	- checkpoint the processing
	- if something crashes, restore from checkpoint

### Project

- open ended
- group or not
- next class, pipeline (should be done in group?)
- pick data and analyze
- there will be some suggestions 
- don't use twitter for streaming (it's overdone)
- explain why you used the storage you used
- maybe get started over the holiday

### Intro data management

- spark and streaming analytics frameworks is the reconstitution of things like Hadoop, that went through the hype cycle and currently is in the trough of disillusionment.
- problem was that all these tools were mis-implemented with the wrong people in the wrong contexts
- 1.5 million dollar hardware and support contract, and what they got was what could be done on a single laptop. "We're not doing big data anymore, this was stupid."
- Greater adoption from drag and drop tools and more abstraction that keeps you from having to do low level work all the time.
- big data is not magical, it's just data for which you need to have different answers to questions of how to manage.

#### Data modeling

- You can model big data, just not with with traditional ERM tools.
- Describe structure
- Data constraints (formats)
- Relationships, what is related between pieces of data
    - Different named variables, same thing

- Schema on write (old way)
	- Schema defined upfront 
	    - Setup table
	    - map relationships form data to actual columns
	    - write sql
	... **SEE SLIDES**

- Schema on read (new nosql way)
	- Schema applied at read time based on application
	- Flexible
	- Not as querable
	- Data consistency not as easy here

- Data governance and quality - how to ensure your data is high quality, organized for gaining value.
- Quality: Metadata, standardization, cleansing, matching, enrichment

- Tools
	:: Open source, governance - ranger, atlas, falcon
	:: Open source, Nifi for governance
	:: Vendor, legacy dw - teradata, informatica
	:: Vendor, big data - cloudera (IBM of big data)... Hortonworks if you want DIY

- Security 

**SEE SLIDES**

- **Book for nosql data modeling**

Next time: Cap Theorem for NoSql, video recorded class

