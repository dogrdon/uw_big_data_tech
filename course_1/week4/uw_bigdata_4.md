Big Data Class 4 - Spark 2
==========================

### Review

- Killing an application in Yarn (hadoop resource manager):
	- Yarn creates application grouping and allocates resources when something is kicked off (e.g., hive)
	- go to yarn UI in ambari
	- `yarn application -list` to list applications
	- `yarn application -kill <Application ID>`
	- Get application ID from list or UI



### Spark 2


#### Partitioning and Shuffling

- Good to work with Hive via spark
- Filter over multiply partitioned dataset will spin up as many filter tasks as there are partitions.
	- too many partitions = too spread out
	- too few mean under provisioned.
	- how to define the # of parititions

	- default is normally total # of cores in cluster
	- or config in `spark.default.parallelism`
	- or HDFS block size
	- write to HDFS and you don't have to write a script to go and collect all the partitioning?
	- load in text file, gets divided into 4 partitions, 4 tasks, so 4 files

- increasing partitions -> reshuffling
- coalescing??

- Shuffling = data moving on multiple nodes, code to data, not the other way around. Executes code on data on node. When application is running, sometimes need to move data to other nodes. So shuffling.

- narrow or wide dependency
	- narrow, filter only happening on one node.
	- wide, word count - has to move data to other nodes to group by count. written on disk cache, so need available disk space.
	- if you scale up your job, but don't have the storage space, it will blow out.
	- most actions require a shuffle (it's the reduce phase)

- partitioning and shuffling is an optimization thing, find out where your process is slow and figure out a better way to tune it.

- find out which block it was operating on in the logs when it was running very slow

- if you're using HDFS, pay attention to HDFS block size

- Stages: a whole unit of execution not dependant on other nodes. You can't get a total from one stage until you get a result from previous stage.

- Batching creates many stages?? (come back to this)

- Spark figures out the stages itself. Much more efficent than map reduce

- **log files** spark generates more logs than you can deal with: raw executor logs, driver logs, cluster manager, etc...

- UI logs will give you a better view in history

#### Best practices (partitioning & shuffling)

- know your data
- mapPartitions can be more efficient than map. (especially with web service calls or database connections)
- foreachPartition v foreach - more efficient iterations

- Use closures because everything needs to be packaged up with the function, there is no global state shared across paritions. EVERYTHING NEEDS TO BE DEFINED IN THE FUNCTION. Pass function to foreachPartition, for example.

- Why would you use map or foreach (if you're not doing partition level stuff), partition versions make it more complicated.


#### Persistance & Caching

`rdd.persist` - loads rdd only once, rather than twice (without it)
- see _storage levels_

#### Spark SQL

- Everythings is still on top of RDDs
- DataFrame v Dataset
- DataFrame is types and names for columns
- Datasets are objects themselves, each is a Car object
- `import spark.implicits._` - just do it.
- datasets strongly typed
- rdd = raw form , handles partitioning
