from sightengine.client import SightengineClient
import json

client = SightengineClient('{api_key}', '{api_secret}')

def detect_weapons(data):
    global client
    videopath = 'Link to video'
    output = client.check('wad').video_sync(videopath)
    print("Probability of Weapon: ", output['weapon'])

##### ADD FUNCTIONALITY HERE

if __name__ == '__main__':
    detect_weapons("test.jpg")
