# coding: utf-8
import numpy as np
import time
from datetime import datetime, date, time, timedelta
import random
import math
import sys
from time import gmtime, strftime
from datetime import datetime

begin_dt= datetime.strptime("20160404", "%Y%m%d")
end_dt= datetime.strptime("20180404", "%Y%m%d")
str_class = "c"

cur_dt = begin_dt
count = 0
while cur_dt <= end_dt: 
    print(cur_dt.strftime("%Y%m%d"), str_class)
    cur_dt = cur_dt + timedelta(days=1)
    count = count + 1

print(count)
