import urllib.request
import json
import matplotlib.pyplot as plt
from matplotlib import animation
import cartopy.crs as ccrs
from cartopy.io.img_tiles import OSM
#Must unable the Mute Inline Plotting to see the graph.
#Figures now render in the Plots pane by default.
#To make them also appear inline in the Console,
#uncheck "Mute Inline Plotting" under the Plots pane options menu.
#IF YOU HAVE THE API ADRESS URL OF YOUR FLIGHT ENTER IT, IF NOT USE THE ONE IN THE
EXAMPLE
#This code gives users with information of their flights:
#the code of the flight/plane,
#the manifacturer of the plane,
#the model of the plane,
#then is the plane a wide body aircraft?
#Then lastly, how confortabel and shaped the plane is? is a uld20 shape?
#PSEUDOCODE
#Enter API url of flight to be checked
#Provide token
#Provide key
#Provide Host key
#Then code, manufacturere, plane model will be displayed
#However, users has to ENTER:
#URL -> https://aviation-reference-data.p.rapidapi.com/icaoType/B738
#x-access-token -> 018d802377f140a4e81a7030d6b318ab
#x-rapidapi-key -> 89ec2a3b25msh86596a6549d5dcep12ec0djsn7e8d6f554630
#x-rapidapi-host -> aviation-reference-data.p.rapidapi.com
#REFERENCE (site I took the above information from):
#API Example: https://rapidapi.com/proground/api/aviation-reference-data/
#UPDATE for to improve the code:
#The X-.. information are the only ones I found so far,
#so my update will be to let users enter this information,
#but users will have to find it themselves
import requests
#ENTER url https://aviation-reference-data.p.rapidapi.com/icaoType/B738
#or http://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?
lat=40.639722&lng=-73.778889&fDstL=0&fDstU=20
url = input("Enter the API url of your flight: ")
querystring = {"origin":"LED","destination":" MOW","currency":"RUB","length":"3"}
headers = dict()
headers ["x-access-token"] = "018d802377f140a4e81a7030d6b318ab"
headers ["x-rapidapi-key"] = "89ec2a3b25msh86596a6549d5dcep12ec0djsn7e8d6f554630"
headers ["x-rapidapi-host"] = "aviation-reference-data.p.rapidapi.com"
response = requests.request("GET", url, headers=headers, params=querystring)
print(response.text)
#Now lets map your flight and flow the flight trajectory on the map
#The first part of the code display just the flight information.
#But this part shows and allow users to follow the flight trajectory
#While the plane is in the air.
#REMENBER: This code can only track flight within km from the center
#So, JFK in this.
#Users can change it to his liking
#This define the figure: Oval? Square? or round? in this case Flat square
fig, ax = plt.subplots()
#This is to set the axes. How?
#We first plot the figure and set the axex into flat squares
ax=plt.axes(projection=ccrs.PlateCarree())
ax.set_ylim(40.6051,40.6825)
ax.set_xlim(-73.8288,-73.7258)
#This add the basemap to define the location of a center
#So the image will rotate around it
#In my case I choe=se the John F. Kennedy International Airport in New York
#with coordinate "40.639722N,73.778889W."
#Taken on https://skyvector.com/airport/JFK/John-F-Kennedy-International-
Airport#:~:text=Location%20Information%20for%20KJFK%20Coordinates%3A
%20N40%C2%B038.40%27%20%2F%20W73%C2%B046.72%27,York%2C%20New%20York%20on
%205200%20acres%20of%20land.
osm_tiles=OSM()
ax.add_image(osm_tiles,13)
ax.text(-73.778889,40.639722,'JFK Intl',horizontalalignment='right',size='large')
ax.plot([-73.778889],[40.639722],'bo')
#PLOT TRACK
track, = ax.plot([], [],'ro')
opener = urllib.request.build_opener()
opener.addheaders = [('User-agent', 'Mozilla/5.0')]
#UPDATE FUNCTION
#This methond send query: Latitude and longitude
#After sending the query, it reads the response,
#decodes it and loads it as JSON.
def update(self):
fp=opener.open('https://aviation-reference-data.p.rapidapi.com/icaoType/B738')
mybyte=fp.read()
mystr=mybyte.decode("utf8")
js_str=json.loads(mystr)
fp.close()
#Declare empty list to contains the operations query
lat_list=[]
long_list=[]
op_list=[]
#Thos block loop into the json, parse it, and extra the longitude and latitude
#At this point, the map will contain dots for the cordinate
#Then we lable them for easy reading
for num,flight_data in enumerate(js_str['acList']):
lat=flight_data['Lat']
lon=flight_data['Long']
lat_list.append(lat)
long_list.append(lon)
op_list.append(flight_data['Op'])
track.set_data(long_list,lat_list)
for num, annot in enumerate(anotation_list):
annot.remove()
anotation_list[:]=[]
#This create the a list to contain all the labels for all the dots
#positions, queryies
for num,annot in enumerate(js_str['acList']):
annotation=ax.annotate('text',xy=(0,0),size='smaller')
anotation_list.append(annotation)
#As the plane moves, its coordinates moves as well
#So, this block update the labels, and the list of lables create above
for num,ano in enumerate(anotation_list):
ano.set_position((long_list[num],lat_list[num]))
ano.xy = (long_list[num],lat_list[num])
txt_op=str(op_list[num])
ano.set_text(txt_op)
return track,ano,
#This is to make it update every second UNLESS the plane stops
#Which will means that the plane arrives
anim = animation.FuncAnimation(fig, update,interval=1000, blit=False)
plt.show()
