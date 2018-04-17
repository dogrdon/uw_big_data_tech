Course 3 UW Big data final project notes
========================================

1) predicting delays on the subway includes:
	- MTA real time subway:
		- http://datamine.mta.info/list-of-feeds
		- http://datamine.mta.info/sites/all/files/pdfs/GTFS-Realtime-NYC-Subway%20version%201%20dated%207%20Sep.pdf
	- MTA turnstile reports:
		- http://web.mta.info/developers/turnstile.html
	- weather:
		- https://www.satori.com/livedata/channels/METAR-AWC-US
	- Citibike data?
	- https://public.enigma.com/datasets/94fe9c6a-93d4-4be6-ac13-dd247146bd82
	- https://public.enigma.com/datasets/46dd416d-aefb-4de4-a145-998a34d44f6a

2) enigma shipment data (updated weekly):
	- https://public.enigma.com/spotlight/bill-of-lading

3) refine and continue wiki project?

4) anyway to get good housing/rent data (maybe just newyork?)
	- https://www.nar.realtor/research-and-statistics
	- Street easy
	- Craigslist
	- Airbnb data: 
		- http://tomslee.net/airbnb-data-collection-methodology-and-accuracy
	- ???



#### MTA

-- https://stackoverflow.com/questions/27531172/gtfs-how-to-combine-the-protocol-buffers-and-gtfs-file

-- `pip install --user --upgrade gtfs-realtime-bindings`

api: cd11239777abea125dc7d109a6f4c29f


-- SUBWAYSTATUS: http://web.mta.info/status/serviceStatus.txt

- http://web.mta.info/status/ServiceStatusSubway.xml

	- updates every 1 min.

-- HISTORICAL DATA:

	- http://web.mta.info/developers/resources/nyct/MTA-Bus-Time-documentation.htm

-- TURNSTILE DATA:



#### Justification

Want to capture realtime as the historical data for other independant variables (e.g., weather) are not generally available. Though we can compare with historical subway data for certain things.


#### Proto-approach

- Use airflow to schedule grabbing from these once every 15 min
- write data of interest to Kafka topics
- Recieve data from kafka topics and store somewhere for analysis
- Some kind of online model?
- Analysis


#### Questions

- Compare a poorly running route (A) with a better route (1, Q)?
- issues with turnstile data - http://www.jbencina.com/blog/2015/06/25/cleaning-nyc-turnstile-usage-data/

- how and what to collect from the gtfs feed



#### Ref

https://blog.dataiku.com/new-yorkers-to-mta-stop-the-subway-debacle.-start-predictive-maintenance

https://jameskao.me/analyzing-the-nyc-subway-dataset/

https://signalproblems.substack.com/p/subway-knowledge-base
