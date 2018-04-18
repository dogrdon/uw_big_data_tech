# https://developers.google.com/transit/gtfs-realtime/examples/python-sample
# pip install --upgrade --user gtfs-realtime-bindings


from google.transit import gtfs_realtime_pb2
import requests
from google.protobuf.json_format import MessageToJson
import json

api_key = 'cd11239777abea125dc7d109a6f4c29f'

feed = gtfs_realtime_pb2.FeedMessage()

feeds = {
	"irt":1,
	"ace":26,
	"nqrw":16,
	"bdfm":21	
}

def get_gtfs_url(feed):
	return "http://datamine.mta.info/mta_esi.php?key={api_key}&feed_id={feed_id}".format(api_key=api_key, feed_id=feeds[feed])

irt_train_gtfs_raw = requests.get(get_gtfs_url("irt")).content
ace_train_gtfs_raw = requests.get(get_gtfs_url("ace")).content
nqrw_train_gtfs_raw = requests.get(get_gtfs_url("nqrw")).content
bdfm_train_gtfs_raw = requests.get(get_gtfs_url("bdfm")).content



feed.ParseFromString(nqrw_train_gtfs_raw)

filename = "./nqrw{}.json".format(feed.header.timestamp)

msgs=[json.loads(MessageToJson(entity)) for entity in feed.entity]

with open(filename, 'w') as ofile:
	json.dump(msgs, ofile, indent=4)