import plotly.plotly as py
from plotly.graph_objs import *
import sys
from Entry import Entry
from datetime import datetime
from datetime import timedelta

fileName = sys.argv[1]

fp = open(fileName, 'r')


table = {}
rollingTable = {}

totalX = 0
totalY = 0
totalEntries = 0

for line in fp:
    
    splitLine = line.strip().split(",")
    
    idNum = int(splitLine[0])
    x = int(splitLine[1])
    y = int(splitLine[2])
    time = int(splitLine[3])
    
    totalX += x
    totalY += y
    totalEntries += 1
    
    current = Entry(idNum, x, y, time)
    
    if(table.has_key(idNum)):
        table.get(idNum).append(current)
        
    else:
        tempList = []
        tempList.append(current)
        table[idNum] = tempList


totalX = 0
totalY = 0
totalEntries = 0
timeMin = sys.maxint;
timeMax = 0;

for key in table.keys():
    for ent in table.get(key):
        
        totalX += ent.x
        totalY += ent.y
        totalEntries += 1
        
        if ent.time < timeMin:
            timeMin = ent.time
        
        if ent.time > timeMax:
            timeMax = ent.time
            
            
currentTime = timeMin
Xdict = {}
Ydict = {}

times = []
Xlist = []
Ylist = []

increment = (timeMax - timeMin) / 10

while currentTime < timeMax:
    
    for key in table.keys():
        for ent in table.get(key):
            if(ent.time >= currentTime and ent.time < currentTime + increment):
                
                Xdict[ent.idNum] = ent.x
                Ydict[ent.idNum] = ent.y
                
    
    Xs = Xdict.values()
    Ys = Ydict.values()
    
    curX = sum(Xs)/len(Xs)
    curY = sum(Ys)/len(Ys)
    
    change = currentTime - timeMin
    hours = change / 3600
    
    minutes = (change - hours * 3600) / 60
    if minutes < 10:
        minutes = "0"+str(minutes)
    else:
        minutes = str(minutes)
    
    
    seconds = change % 60
    if seconds < 10:
        seconds = "0" + str(seconds)
    else:
        seconds = str(seconds)
    
    
    times.append(str(hours) + ":" + minutes + ":" + seconds)
    Xlist.append(curX)
    Ylist.append(curY)
    
    currentTime += increment        


  
        
avgX = totalX / totalEntries
avgY = totalY / totalEntries

print "Average Understanding Score: " + str(avgX)
print "Average Engagement Score: " + str(avgY)
print "Increments are " + str(increment) + " seconds"

trace0 = Scatter(x=times, y=Xlist, name="Engaged");
trace1 = Scatter(x=times, y=Ylist, name="Understood");

data = Data([trace0, trace1])

unique_url = py.plot(data, filename = 'Data')        

fp.close()               
