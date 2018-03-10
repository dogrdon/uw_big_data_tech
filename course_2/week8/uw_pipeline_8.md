Building Data Pipeline Class 8 - 20180307
=========================================


### Now and coming up for next class

- flow based programming
- ni-fi more next class?
- next time talk about more details about what we couldn't get into this course (talk about scaling & mesos)


### New in big data

- Jupyterlab
- spark 2.3, you should see more performance equity across parquet, orc, or otherwise (only difference will be from the underlying format, but not from spark itself)
- spakr 2.3, stream to stream joins now


### Assns

- Turn logging way down	
- run similator only for a finite amount of time and then draw out what you have and see if you are getting what you think you are getting
- flatten the input to dataframe (not as nested) - to make the rest easier - e.g., explode `select(sensors.*).explode`
- in validating the values (nothing better than looking at all the cells)
- careful with UDFs, spark cannot optimize them like built-in function
- build a schema first so it does not infer and give you something you don't want (map v. struct, any difference?)
- Don't have to define every sensor in schema, 
	- could rather do something like `structtype(mapType(string,integer))`


### Project

- build a pipeline to support a scenario (don't worry about the analysis, only if time)
- have a diagram to talk through (what you chose and why to build)
- if can't finish, say what you would change
- problem + complication (what do we want to solve, how do we want to solve, and what are some of the challenges we have to overcome. Also how to improve. Also alternative solutions?)
- aside: can get mlb data from google big query

*** was there any further discussion of projects or homework? (yes, about an hour that was not recorded ***

### Apache beam

- used in cutting edge places, don't just jump straight to beam
- using spark first then beam when you need to start doing other things
- but first learn how to use the frameworks first, then jump to beam
- flink much closer to beam
- it's also new, paper written in 2016

### Apache nifi

- everything is a stream
- flow based programming
- nifi is focused more on data moving through the system
- flowfile (which isn't a file, it's an event or a record) - provides provenance
- good for QA, debugging, audit trail
- has backpressure regulation, to throttle anything that's really backing up or getting behind the stream
- can give you option to balance your concerns for, for instance the difference between arvhiving you data or processing in realtime e.g., depending on your system needs, give priority and more resources to processes that need them more.
- nifi came out of the government - NSA, need to be able to track data as it moves through the system
- can define a flow and share it
- drag and drop (like a sql modeling tool)
- confusing, hortonworks data flow uses nifi.
- can save a flow as an xml|json file, but would not code this, use the UI. but it can run scripts as a process in the flow
- won't get as much performance out of nifi as with kafka and apache
- better for tracking what's going on with data moving through
- best of both worlds, nifi doesn't handle your whole process, it's just part of your process, so it connects to kafka and spark when it can't do those things best
- nifi can show that a datafile was deidentified properly (hmm) as part of provenance ( can pinpoint if this process broke down )


### Scaling (Mesos)

- cluster managers
- mesos better than yarn?
- more robust for spark (yarn wasn't working)
- yarn only really works when managing hadoop alone
- mesos makes your cluster look like one big machine
- mesos similar to how in hdfs you only see one file, even if there are multiple
- docker to isolate
- similar and intertwined with kubernetes (orchestration for containers), for mesos, don't need a container, you can run anything
- can shut down resources (or schedule them) based on what kind of resources are being used (deny a request to use a request)
- deploy services to a mesos cluster, then mesos will allocate based on application/service needs (sping down resources for spark if spark not being used)
- each executor is it's own container (or cgroup)
- it's an os for a cluster
- can interact with via sdk or a rest API called marathon

#### Mesos architecture

- master
- slave
- offer (slave offers resources to master and master offers them to frameworks)
	- makes offers to Spark for example, "mesos: i have 1 core for you, spark: that's not good enough, i'll wait for more"
- framework
- task
- apache zookeeper ( to coordinate everything )

#### Offers

- framework starts and begins to ask make me offers
- slaves constantly sending up offers
- executors running on slaves
	- these are resources, not servers themselves
- mesos translates to implementation

#### Marathon

- like an init deamon for a data center
- runs longtime, ensures continued running
- has web user interface
- submit a docker container directly?