import xml.etree.ElementTree as ET
import requests


def etree_to_dict(t):
    return {t.tag : map(etree_to_dict, t.iterchildren()) or t.text}

def parse_xml(r):
	subway_status = {}
	subway_status['timestamp'] = root.find('./timestamp').text
	subwaylines = root.findall("./subway/line")

	subway_status['lines'] = []
	for i in subwaylines:
		line_record = {}
		line_record['line'] = i.find('name').text
		line_record['status'] = i.find('status').text
		line_record['raw_text'] = i.find('text').text
		subway_status['lines'].append(line_record)

		# strip entries to clean things up

		for k, v in line_record.items():
			if v is not None:
				v.strip()
	
	return subway_status


if __name__ == '__main__':
	

	SOURCE = "http://web.mta.info/status/serviceStatus.txt"
	res = requests.get(SOURCE)
	xmlstring = res.text
	root = ET.fromstring(xmlstring)

	status_data = parse_xml(root)

	'''Send to raw store'''
	