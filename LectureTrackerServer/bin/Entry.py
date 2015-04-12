from datetime import datetime


class Entry:
    
    
    def __init__(self, idNum, x, y, time):
        
        self.idNum = idNum
        self.x = x
        self.y = y
        self.time = time
        self.date = datetime.fromtimestamp(time)





