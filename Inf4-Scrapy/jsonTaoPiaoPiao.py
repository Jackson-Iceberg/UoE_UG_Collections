# @Time: 15/07/2022 12:27
# @Author: Jackson Zhou
import urllib.request

url = 'https://dianying.taobao.com/cityAction.json?activityId&_ksTS=1657884603644_108&jsoncallback=jsonp109&action=cityAction&n_s=new&event_submit_doGetAllRegion=true'

headers = {
    # 'accept': 'text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01',
    #   'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
    'cookie': 't=2f810b299a525d0d17eb15ef8fa41703; cookie2=149b8e64c1d7c86989ef39201382edd3; v=0; _tb_token_=3b3433e7e30e5; cna=GcpUG7o2a14CAcApcuLVgDUT; xlly_s=1; tb_city=110100; tb_cityName="sbG+qQ=="; isg=BAMDcpTcZbuOhSlPakzkKY0fksGteJe6uuqqETXg1GLZ9CIWvUkwCqclb5S6oe-y; l=eBSswB0nLD14vJXdBO5Bourza77TmIRb8FNzaNbMiInca1ofTFtM6NCHh9uJ7dtx3tCXFetrUECqHRE9-iUNixDDBeV-1NKmXxvO.; tfstk=ciSABm08_8HAMmLB5tUk7nzWrqIAaoaJ_qOnXGS7tHJtNJotfs0VxMdkM-9BTIeR.',
    'referer': 'https://dianying.taobao.com/index.htm?spm=a1z21.3046609.header.3.2a0c112acxkLkj&n_s=new',
    # 'sec-ch-ua': '" Not A;Brand";v="99", "Chromium";v="100", "Google Chrome";v="100"',
    # 'sec-ch-ua-mobile': '?0',
    # 'sec-ch-ua-platform': "macOS",
    # 'sec-fetch-dest': 'empty',
    # 'sec-fetch-mode': 'cors',
    # 'sec-fetch-site': 'same-origin',
    'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36x-requested-with: XMLHttpReque'
}
request = urllib.request.Request(url=url, headers=headers)
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')
# print(content)
content = content.split("(")[1].split(')')[0]
print(content)
with open('jsonTaoPiaoPiao.json', 'w', encoding='utf-8') as fp:
    fp.write(content)
