Emerging Technologies Class 3
==============================

## Distributed Systems

### Homework

- ttest, comparing two things
- ttest tells you if there's a difference between the two different types of aspiration for example (if there wasn't a difference, then it would seem that it doesn't have much impact one way or the other)

- pvalue is probability that difference is based on random chance

- intercept = 

value for predicted = intercept + coefficient1 * val1 + coefficient2 * val2 + coefficientn * valn

### Parallelization

- finding a crashed plane using baseyian statistics

- Embarassingly parallel

	- hyperparameter search
	- dna sequencers (separate than regconstruct)

- Hill climb algo

	- Compute loss function
	- Do it while optimal score is not found, stop when find optimal score
	- Problem: can get stuck at a local algorithm, because it's not being compared across all population, just the neighbors
	- Solve: parallelize it and pick lots of starting points, then pick best from all the optimals

- Parallizing w' DAGs
	
	- and map reduce (pig)


### Mongodb

- one giant store of the pre-joined, denormalized data

- do a docker pull to install?

- `docker ps` - what containers do we have

- everything at once: 
    var file = cat('./data.json')
    var data = JSON.parse(file)
    db.{Collection}.insert(data)

- Use when you want to scale easily because you can just dump data in 

- denormalized view has to be processed with ETL, you have to know what's in there

- mongodb could be your data lake
- elasticsearch better for inverted docs (for searching fast)

- mongodb is storage, but you can query
- elasticsearch is search index, but you can store stuff in it.

### elasticsearch

- and kibana, the ui

- write to elasticesearch faster with beam (writing with multiple machines all at once)
