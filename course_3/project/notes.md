Course 3 UW Big data final project notes
========================================

Predicting delays on the subway includes:
	- MTA real time subway:
		- http://datamine.mta.info/list-of-feeds
		- http://datamine.mta.info/sites/all/files/pdfs/GTFS-Realtime-NYC-Subway%20version%201%20dated%207%20Sep.pdf
		- https://transitfeeds.com/p/mta/79/latest
	- MTA turnstile reports:
		- http://web.mta.info/developers/turnstile.html
	- weather:
		- https://www.satori.com/livedata/channels/METAR-AWC-US
	- Citibike data?
	- https://public.enigma.com/datasets/94fe9c6a-93d4-4be6-ac13-dd247146bd82
	- https://public.enigma.com/datasets/46dd416d-aefb-4de4-a145-998a34d44f6a



#### MTA

-- https://stackoverflow.com/questions/27531172/gtfs-how-to-combine-the-protocol-buffers-and-gtfs-file
-- `pip install --user --upgrade gtfs-realtime-bindings`

	api: cd11239777abea125dc7d109a6f4c29f


-- SUBWAYSTATUS: http://web.mta.info/status/serviceStatus.txt

- http://web.mta.info/status/ServiceStatusSubway.xml

	- updates every 1 min.

-- HISTORICAL DATA:
	- http://web.mta.info/developers/resources/nyct/MTA-Bus-Time-documentation.htm
	- http://web.mta.info/developers/MTA-Subway-Time-historical-data.html

-- TURNSTILE DATA:
	- http://web.mta.info/developers/turnstile.html

-- Weather
	- Darksky: 

	api: 2b34107e885f4e81dea4b0389c54cad4
	sample https://api.darksky.net/forecast/2b34107e885f4e81dea4b0389c54cad4/40.730610,-73.935242

#### Frequency:

3, 5, 8, 10, 15 minutes



#### Justification

Want to capture realtime as the historical data for other independant variables (e.g., weather) are not generally available. Though we can compare with historical subway data for certain things.


#### Proto-approach

- Use airflow (or NiFi) to schedule grabbing from these once every 15 min
- write data of interest to Kafka topics
- Grab signal from line status text - classify delay text (?)
- Recieve data from kafka topics and store somewhere for analysis
- Some kind of online model?
- Some kind of flink MR on the GTFS data
- Anything to do with the weather?
- Analysis


#### Questions

- Compare a poorly running route (A) with a better route (1, Q)?
- issues with turnstile data - http://www.jbencina.com/blog/2015/06/25/cleaning-nyc-turnstile-usage-data/

- how and what to collect from the gtfs feed



#### Ref

https://blog.dataiku.com/new-yorkers-to-mta-stop-the-subway-debacle.-start-predictive-maintenance

https://jameskao.me/analyzing-the-nyc-subway-dataset/

https://signalproblems.substack.com/p/subway-knowledge-base

http://gtfs.org/best-practices/

Kafka & Mongo: https://www.slideshare.net/ConfluentInc/data-streaming-with-apache-kafka-mongodb

Kafka & Airflow: https://medium.com/@paddlesoft/getting-hourly-usgs-flow-information-with-airflow-and-kafka-d88baf2a0950

#### Technical

https://developers.google.com/protocol-buffers/docs/reference/python/
https://developers.google.com/protocol-buffers/docs/reference/python-generated#message
Weather: https://forecast-v3.weather.gov/documentation?redirect=legacy
dark sky: https://darksky.net/dev

Kafka installation on ubuntu 16.04: https://devops.profitbricks.com/tutorials/install-and-configure-apache-kafka-on-ubuntu-1604-1/ (with updated binary at: http://apache.claz.org/kafka/1.1.0/kafka_2.11-1.1.0.tgz)

Kafka installed at `/opt/Kafka`

install scala with `sudo apt-get install scala=2.11.\*`
install sbt using: https://gist.github.com/alexislucena/5947c925afc3a0f26b04c3785d482e9f
install spark? https://medium.com/@josemarcialportilla/installing-scala-and-spark-on-ubuntu-5665ee4b62b1

Used this to install spark: https://blog.sicara.com/get-started-pyspark-jupyter-guide-tutorial-ae2fe84f594f

This might have more details: https://www.santoshsrinivas.com/installing-apache-spark-on-ubuntu-16-04/

spark installed at `/opt/spark`

Install ELK stack using: https://www.digitalocean.com/community/tutorials/how-to-install-elasticsearch-logstash-and-kibana-elk-stack-on-ubuntu-16-04

- start `pyspark` (or `spark-submit`) with: `pyspark --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0`

- Running processor: `/opt/spark/bin/spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0,org.mongodb.spark:mongo-spark-connector_2.11:2.2.2 --conf "spark.mongodb.input.uri=mongodb://127.0.0.1/mta_delays_dev.nyc_weather?readPreference=primaryPreferred" --executor-memory 4g --driver-memory 6g ~/code/dags/mta_delays/process/mta_processor.py`

#### Process

DarkSky API authentication failure 5/3, 2:30AM-9:00AM EST (http://status.darksky.net/2018/05/03/authentication-errors.html)