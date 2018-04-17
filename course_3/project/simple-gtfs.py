# https://developers.google.com/transit/gtfs-realtime/examples/python-sample
# pip install --upgrade --user gtfs-realtime-bindings


from google.transit import gtfs_realtime_pb2
import urllib2
from google.protobuf.json_format import MessageToJson

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

irt_train_gtfs_raw = urllib2.urlopen(get_gtfs_url("irt")).read()
ace_train_gtfs_raw = urllib2.urlopen(get_gtfs_url("ace")).read()
nqrw_train_gtfs_raw = urllib2.urlopen(get_gtfs_url("nqrw")).read()
bdfm_train_gtfs_raw = urllib2.urlopen(get_gtfs_url("nqrw")).read()



feed.ParseFromString(gtfs_raw)

print(feed.header.timestamp)

msgs=[]
for entity in feed.entity:
	msg = json.loads(MessageToJson(entity))
	msgs.append(msg)
