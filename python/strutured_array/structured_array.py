# coding: utf-8
import numpy as np
import time
from odps.udf import annotate
from odps.udf import BaseUDTF
from odps.distcache import get_cache_table
import datetime
import random
import math
import sys
from time import gmtime, strftime
from datetime import datetime

x = np.array([(1,2.,'Hello'), (2,3.,"World")],
        dtype=[('foo', 'i4'),('bar', 'f4'), ('baz', 'S10')])
print(x)
print(x[np.where(x['foo']==1)]['bar'])

feasible_cashpools = []
print(len(feasible_cashpools))
sys.exit

print(strftime("%Y-%m-%d %H:%M:%S", gmtime()))
print(str(datetime.now()))

