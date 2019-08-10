from sightengine.client import SightengineClient
import json

client = SightengineClient('{api_key}', '{api_secret}')

def detect_weapons(data):
    global client
    output = client.check('wad').set_file(data)
    print("Probability of Weapon: ", output['weapon'])

##### ADD FUNCTIONALITY HERE

if __name__ == '__main__':
    detect_weapons("test.jpg")