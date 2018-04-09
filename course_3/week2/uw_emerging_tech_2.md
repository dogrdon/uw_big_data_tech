Emerging Technologies Class 2
==============================

## Lambda and Kappa architecture

### Lambda [Batches] 

- Facebook uses Hive for analytics all day, writing SQL all day on Hive, which is SQL layer on top of HDFS

- Heron is now better than Storm

- Apache streaming is actually mini-batches

- batch v. streaming
	

#### Use case 1 - Medical Data - Lambda architecture

- medical data comes in and batch processes for NLP out to other systems
- storm (streaming) -> 1.5 mill docs per hour
- 30 min on old infrastructure now milliseconds in ElasticSearch
- (trident is like storm but for batches)


#### use case 2

Events [server] -> Queue [kafka] -> Store [hadoop] -> Query [hbase/terradata/hive]


### Kafka [Not batches]

- immutable persistent logs
- not good for really involved deep learning algorithms that take a long time to run
- 1. Everything is a stream
- 2. immutable data sources
- 3. Single analytics framework
- 4. Replay functionality

Data -> kafka -> Flink [back to kafka] & Elastic Search -> kibana (UI and visaulizations for Elasticsearch)

You can scale elasticsearch with bean(?)

#### kafka v storm

- storm has bulbs, passing data (similar to flink) where can massage the data

#### Tech

- samza
- spark
- storm
- kafka
- onyx

#### Advantages

- unit testing
- revision of computations
- constant streaming so no batches


#### Disadvantages

- But some ML processes need holding something in memory more than once or for a long time, 
- So kappa not best for this algorithm for streams not the same as batch


#### Use case - Bayesian anomaly detection

- kafka -> flink -> kafka - flink -> kafka -> elasticsearch & kibana (dashboard)
- every row updates the distribution, constantly streaming in 
- Kafka routes, Flink processes
Raw -> statistical extractor -> statistical information -> bayseian anomaly detection -> novelties -> dashboard

** About the HW **

- One hot encoding and perfect colinearity
	- https://stats.stackexchange.com/questions/224051/one-hot-vs-dummy-encoding-in-scikit-learn
	- https://github.com/pandas-dev/pandas/issues/12042

- Checking coefficients to say this variable is useful, but this 

- Really good r-squared (score) = 0.9, anything above 0.6 is ok. Something low says it's not really linear (e.g., more than half of variation is random chance ??? )


### Demo ( Microsoft streaming )

- Event hubs
- (service bus = kafka)

- for docker, keep containers as light as possible, don't install a ton of stuff into one
- Service bus explorer - GUI to send messages Over event hub 

0. create a resource
1. create an event hub (just like a vm)
2. Create stream analytics jobs (but expensive, charged by the message)
3. Test with small dataset like the data you will stream

- Azure ML
	- machine learning without a single line of code

	- decision jungle is decision tree tweaked (fruits of labor of Microsoft ML engineers)

	- you can use azure machine learning inside of Azure notebooks

	- azure streaming very expensive, dont use


- Nifi is good because you can start creating data pipelines with just a single person or a small team

- data is not plotting correctly because it's not ordered, need to order first