# https://developers.google.com/transit/gtfs-realtime/examples/python-sample
# pip install --upgrade --user gtfs-realtime-bindings


from google.transit import gtfs_realtime_pb2
import urllib2

api_key = 'cd11239777abea125dc7d109a6f4c29f'

feed = gtfs_realtime_pb2.FeedMessage()


gtfs_raw = urllib2.urlopen(
		"http://datamine.mta.info/mta_esi.php?key={api_key}&feed_id=1".format(api_key=api_key)).read()


feed.ParseFromString(gtfs_raw)

print(feed.header.timestamp)

for entity in feed.entity:
	print(entity)