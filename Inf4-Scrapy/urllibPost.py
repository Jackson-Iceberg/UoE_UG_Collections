# @Time: 03/07/2022 15:13
# @Author: Jackson Zhou
import urllib.request
import urllib.parse

url = 'https://fanyi.baidu.com/sug'

headers = {
    'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

data = {
    'kw':'king'
}

# post 请求的参数，必须进行编码
data = urllib.parse.urlencode(data).encode('utf-8')

# post的请求的参数，是不会拼接在url的后面，而是需要放在请求对象定制的参数中
request = urllib.request.Request(url=url,data=data,headers=headers)

# 模拟浏览器向服务器发送请求
response = urllib.request.urlopen(request)

# 获取响应的数据
content = response.read().decode('utf-8')

# post请求方法的参数必须编码，data = urllib.parse.urlencode(data)
# 编码后，必须调用encode方法，data = urllib.parse.urlencode(data).encode('utf-8')
# 参数是放在请求对象定制的方法中 request = urllib.request.Request(url=url,data=data,headers=headers)

print(content)

# 字符串-》json对象
import json

obj = json.loads(content)
print(obj)