import json
import requests

def parse_data(data):
	summary_record = {}
	
	current = data['currently']
	for k, v in current.items():
		summary_record[k] = v

	summary_record['minute_status'] = data['minutely']['summary']
	summary_record['latitude'] = data['latitude']
	summary_record['longitude'] = data['longitude']
	summary_reocrd['timezone'] = data['timezone']

	return summary_record


if __name__ == '__main__':

	SOURCE = "https://api.darksky.net/forecast/2b34107e885f4e81dea4b0389c54cad4/40.730610,-73.935242"
	res = requests.get(SOURCE)
	data = json.loads(res.content)

	record = parse_data(data)	

	''' Send record to first raw storage '''