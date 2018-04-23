import xml.etree.ElementTree as ET
import requests


def etree_to_dict(t):
    return {t.tag : map(etree_to_dict, t.iterchildren()) or t.text}


SOURCE = "http://web.mta.info/status/serviceStatus.txt"

res = requests.get(SOURCE)

xmlstring = res.text

root = ET.fromstring(xmlstring)

subways = root.findall("./subway/line")

for i in subways:
	line = i.find('name')
	status = i.find('status')
	print(line.text, status.text)