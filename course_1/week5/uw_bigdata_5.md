Big Data Class 5 - Spark 3
==========================

### Review

- Containers v VMs
	- VMs hardware isolation - through a hypervisor
	- VMs a slice of a hardware system
	- containers software isolation
	- containers over virtualization: can more easily share with the external systems
	- containers allow you to do microservices. swap out services.
	- parrallelize - creates RDD from something, distributes it amongst the cluster.

### Homework

- MapPartitions with index, pull header from first partition, don't look at other partitions. Get rest?
- Bonus 1 - could just get seattle zips to array, then for each line in other data file, if zip in zip array, get that.
- But how would you send a very large GB Array to each task? (for discussion later - broadcast variable)

### New Stuff

- Broadcast variables: 

	- map of data sent only once to each node as cached variables
	- this is not automatic
	- would be good for moving large pieces of data to nodes 
	- stored in the executor space, not task workspace ("500mb of data you don't have to store in your task")
	- mark as unpersist and it will make the broadcast variable eligible for garbage collection

- Accumulators?

	-

- Spark web ui

	- understand what is going on with your jobs
	- how to set up so that it gets outside of the sandbox

- Spark SQL thrift server
	- DB client, SQL to spark server, SQL parses to Spark SQL API -> data and back

- Things just go much faster in Spark than in Hive w/ Tez

- spark for data processing, data warehouse for storage and analysis

- Notebooks:

	- jupyter
		 - but spark scala support is buggy, 
		 - needs special installation
	- Zepplin
		 - apache
		 - hortonworks
		 - in ambari
		 - more for big data
		 - harder to use than jupyter
		 - zepplin will allow you to use different kernals in a notebook (e.g., scala spark and pyspark in the same note)