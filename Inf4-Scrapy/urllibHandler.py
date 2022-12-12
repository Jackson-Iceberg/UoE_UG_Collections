# @Time: 13/07/2022 20:55
# @Author: Jackson Zhou
import urllib.parse
import urllib.request

url = 'http://www.baidu.com'

headers = {
    'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

request = urllib.request.Request(url=url,headers=headers)

# handler build_opener open
# 1. 获取handler对象
handler = urllib.request.HTTPHandler()
# 2. 获取opener对象
opener = urllib.request.build_opener(handler)
# 3. 调用opener方法
response = opener.open(request)

content = response.read().decode('utf-8')

print(content)