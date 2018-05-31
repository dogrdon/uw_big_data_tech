Emerging Technologies Class 8
==============================


### Recommendation Engines

#### Alternating least squares
- shilling attack?
- let the data speak for itself (beer and diapers)
	- not real
	- 

- REF: https://bugra.github.io/work/notes/2014-04-19/alternating-least-squares-method-for-collaborative-filtering/

- There are two models
	- Users x, items y
	- items x, users y
	- Alternate until convergence between both sides using coefficients
	- Given these things about the user and these things about the items, this is the best audience for the product

- Uses matrix factorization to decompose into user matrix and item matrix separately - then compute the coefficients

- Things to consider, people who are unreliable reviewers
- Use gradiant descent in optimization till we get a solution
- These matrices can get pretty out of hand (large) pretty quickly

- ALS is in Spark
- MLLib in Spark is deprecated
- ML is a rewrite

#### Realtime ALS Model

- Streaming ALS model can't be done in Spark
- ALS runs in sequential iterations, parts that are not parallizable


#### XXXX

- Ontologies required for <missed>


#### Amazon's Product Graph

- graph database of all products in the world and relationships between them (to assess quality)


#### End to end recommendation engine

- Elasticsearch spark (for spark 2.0) - in maven
- 

- RMSE is a standard way to validate your model

- precision and recall are for classification


#### Graph databases

- Grows out of schemas and ontologies
- The most famous graph database is wikipedia
- Types:

	- Semantic - triple stores
	- Property graphs

#### Semantic
- RDF
- SPARQL
- Less spatially efficient

#### Property
- Compromise between semantic and relationship

#### SPARQL

- Select, Construct, Ask, Describe
- Only focussing on select here