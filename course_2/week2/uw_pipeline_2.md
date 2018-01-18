Building Data Pipeline Class 2 - 20180117
=========================================

## Building pipelines

- making data available for downstream analysis
- Not just code
	- cluster planning
	- tuning
	- devops
	- data management
	- secops


### Example pipeline

- data => ingest -> staging -> processing -> data & workflow managment (archiving, keep the raw data) -> access => insight

- This process is the Productionization of data

### Different pipelines

#### Batch oriented data

- RDBMS/EDW (don't stream from a db)
- Files, Sensors
- Event processing

#### Streaming

- Files, sensors
- Continuous Event processing

#### hybrid

- Unified Log Prcessing Model


#### Coming up...

- ingest
- batches as bounded streams-


#### Acquiring and Discovery

- First you need to build a business case
	- you're usually starting from ugly data and you need to build a business process to clean that data and make it useful

- You also need subject matter experts to help you figure out how to clean the data and make it useful.

- Before thinking of details:
	- Where is data coming from
	- how is it used
	- How is it going to be used

- Explore data in the context of its specific domain
	- Pass smell test? (does it look like what I expect?)
	- And standards being used?
	- Large pieces missing?

- Get into details
	- start small. sample, use pandas
	- look for "schema" match - does the data follow the schema it says it does
	- examine distribution of data (are outliers ok or indication of bad data)
	- is there missing or incomplete data - what if it was cleaned out at a step upstream?

- what if missing
	- delete rows
	- delete columns
	- add a default val
	- impute the value

- why missing?
	- bad parsing
	- hidden chars
	- random?
	- user errors

**DOS2UNIX** utility to convert dos linefeeds to linux linefeeds in data

- Get more familiar with vi for data processing, will be more obvious in vi where the bad characters are

- Don't start with spark, do the discovery work, develop the process, then sparkify it

### Complicated Pipeline

- Flume is ingest, source of the data (even if not true source, at least we control it)
- Kafka is staging (this will make more sense when we get into streaming architecture - it's a messaging tier)
- 
