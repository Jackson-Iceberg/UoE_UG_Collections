# @Time: 17/07/2022 00:58
# @Author: Jackson Zhou
import requests
import json

url = 'https://www.baidu.com'
response = requests.get(url=url)
response.encoding = 'utf-8'
print(response.text)
print(response.url)
print(response.content)
print(response.status_code)
print(response.headers)

url = 'https://www.baidu.com/s?'

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}
data = {
    'wd': '北京'
}

response = requests.get(url=url, params=data, headers=headers)
content = response.text
print(content)
# 总结 request get请求
# 1. 参数使用params传递
# 2. 参数不需要urlencode编码
# 3. 不需要请求对象定制
# 4. 请求资源路径中的？可以加或者不加

url2 = 'https://fanyi.baidu.com/sug'
data2 = {
    'kw': 'eye'
}
response2 = requests.get(url=url, data=data, headers=headers)
content2 = response2.text

obj = json.loads(content2, encoding='utf-8')
print(obj)
# 总结 request post请求
# 1. post请求的参数是data
# 2. 参数不需要urlencode编码
# 3. 不需要请求对象定制
