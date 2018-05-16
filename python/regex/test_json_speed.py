import json
msg={'remote_addr': '221.205.146.134', \
     'request_length': '912', \
     'body_bytes_sent': '63', \
     'request_time': '0.030', \
     'time_local': '07/Jun/2016:14:57:13 +0800', \
     'request': "GET /index/collectpname/type/get?os=19&vc=300050113&v=5.1.13&md=NX507J&sn=4.589389937671455&cpu=qualcomm+msm8974pro-aa&ca1=armeabi-v7a&ca2=armeabi&m=d594466e6bedfac456f68070c375bce3&m2=efe8307215dd9823ec4bf1f243f2d28e&ch=300001&ppi=1080_1920&startCount=1&re=1&tid=0&cpc=1&snt=-1&nt=1&s_3pk=1 HTTP/1.1", \
     'status': '200', \
     'http_referer': "-", \
     'http_user_agent': "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; NX507J Build/KVT49L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30;360appstore",  \
     'server_name': '*.mobilem.360.cn',  \
     'server_addr': '10.121.212.185', \
     'http_x_forwarded_for': '-', \
     'http_x_real_ip': '-', \
     'https': ''}
json_encoded = json.dumps(msg)
for i in range(1,1000000):
    json.loads(json_encoded)
