# -*- encoding: utf-8 -*-

import re
import math
from ngnix_regex import *
from urlparse import urlparse

#msg='192.168.0.1 912 63 0.030 - - [07/Jun/2016:14:57:13 +0800] "GET /index/collectpname/type/get?os=19&vc=300050113&v=5.1.13&md=NX507J&sn=4.589389937671455&cpu=qualcomm+msm8974pro-aa&ca1=armeabi-v7a&ca2=armeabi&m=d594466e6bedfac456f68070c375bce3&m2=efe8307215dd9823ec4bf1f243f2d28e&ch=300001&ppi=1080_1920&startCount=1&re=1&tid=0&cpc=1&snt=-1&nt=1&s_3pk=1 HTTP/1.1" 200 - "-" "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; NX507J Build/KVT49L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30;360appstore" *.mobilem.360.cn 192.168.0.1 - - ssl:'
#regexNew = '(?P<remote_addr>%s?)\ (?P<request_length>%s?)\ (?P<body_bytes_sent>%s?)\ (?P<request_time>%s?)(\ \-){2}\ \[(?P<time_local>%s?)\]\ "(?P<request>%s?)"\ (?P<status>%s?)\ (?P<http_referer>.*?)\ "\-"\ "(?P<http_user_agent>%s?)"\ (?P<server_name>%s?)\ (?P<server_addr>%s?)\ (?P<http_x_forwarded_for>[^\ ]*?)\ (?P<http_x_real_ip>[^\ ]*?)\ ssl\:(?P<https>[^\ ]*?)'%(IPORHOST, INT, INT, FLOAT, HTTPDATE, REQUEST, INT, AGENT, SERVERNAME, IP)
regexNew = '(?P<remote_addr>.*?)\ (?P<request_length>.*?)\ (?P<body_bytes_sent>.*?)\ (?P<request_time>.*?)\ \-\ \-\ \[(?P<time_local>.*?)\]\ \"(?P<request>.*?)\"\ (?P<status>.*?)\ (?P<http_referer>.*?)\ \"\-\"\ \"(?P<http_user_agent>.*?)\"\ (?P<server_name>.*?)\ (?P<server_addr>.*?)\ (?P<http_x_forwarded_for>.*?)\ (?P<http_x_real_ip>.*?)\ ssl\:(?P<https>.*?)'

def format_shouzhu_log(log):
    ret = {}
    try: 
        m = re.match(regexNew, log)
        ret = m.groupdict()
    except Exception as e:
        pass
		#print log,"<>", e
    return ret 

msg='192.168.0.1 912 63 0.030 - - [07/Jun/2016:14:57:13 +0800] "GET /index/collectpname/type/get?os=19&vc=300050113&v=5.1.13&md=NX507J&sn=4.589389937671455&cpu=qualcomm+msm8974pro-aa&ca1=armeabi-v7a&ca2=armeabi&m=d594466e6bedfac456f68070c375bce3&m2=efe8307215dd9823ec4bf1f243f2d28e&ch=300001&ppi=1080_1920&startCount=1&re=1&tid=0&cpc=1&snt=-1&nt=1&s_3pk=1 HTTP/1.1" 200 - "-" "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; NX507J Build/KVT49L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30;360appstore" *.mobilem.360.cn 192.168.0.1 - - ssl:'
#msg='192.168.0.1 - - [07/Jun/2016:14:57:13 +0800] "GET /channel/getUrl?src=8293705&app=zs HTTP/1.1" 302 5 "-" "AndroidDownloadManager" 141 0.054 0.013 ssl:'
for i in range(1,1000000):
    format_shouzhu_log(msg)
