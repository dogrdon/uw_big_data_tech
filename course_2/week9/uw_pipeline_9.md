Building Data Pipeline Class 9 - 20180314
=========================================


## Assignment week 7

- Use struct over map in defining schema because it's more catchall (that is, if something isn't there, it's still a struct)
- Make sure you get rid of the top level "parsed_value" (and flatten while you're at it)
- CheckpointLocation is used for recovering if there is a cache, it keeps metadata about the jobs
- In python you can use `.subtract()` to join two streams where the left stream is everythingn but what is in the stream called in the the `subtract` function
- You can't do an |MISSED THIS|
- Second number in a window is the slide


## Project notes

- Can enforce non-nullable fields in schema, so that you can't have nulls for those. Not specifying is more convenient, but many nulls

- Use nifi for a client, need to be more robust or you will have to write that all yourself


## Production considerations

- Data and workflow management
- Governance
- Scaling (mesos)


### Workflow management tools

- You can't schedule a notebook
- You can use Ansible/Puppet for scheduling and pipelining


- Oozie, workflow for hadoop 
	- can use if your workflow is still just Sqoop and hadoop
	- very hard too use, 
	- verbose xml config

- Apache Falcon
	- next iteration of oozie
	- whole lifecycle

- Luigi
	- heavy python users
	- Doesn't have scheduling, have to use cron

- Apache Airflow
	- This is the recommended one
	- Operators
	- Understands SLA concept - give priority to certain tasks, or if this task doesn't finish in X minutes, send me an email.
	- Unlike luigi,airflow uses a database for persistence

### Governance tools

- Apache Atlas *** Hortonworks (wouldn't work with RDBMS?)
	- Data classification
	- Metadata schema central storage
	- Auditing
	- Security/Policy engine...
	- Most places skip this stuff
	- Allows you to role your own
	- Limited support

- Commercial tools are better for this than open source...examples?
	- Informatica master data management tools
	- Cloudera navigator (you have to pay for this)


- Apache Ranger *** Security
	- Not good for multitenant

- Apache Knox
	- 

- Again commercial product would be better
	- Would use commercial products when working with government

- This stuff is not super exciting so it's hard to get many committers for these tools. Which is why it's better to go with commercial
