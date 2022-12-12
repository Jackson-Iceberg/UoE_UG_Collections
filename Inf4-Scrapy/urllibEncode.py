# @Time: 03/07/2022 14:39
# @Author: Jackson Zhou
# urllib encode的应用场景：多个参数

import urllib.parse
import urllib.request

base_url = 'https://www.baidu.com/s?'

data = {
    'wd':'周杰伦',
    'sex':'男',
    'location':'中国台湾省'
}

data = urllib.parse.urlencode(data)

# 请求资源路径
url = base_url + data

# 请求对象的定制是为了解决反爬的第一种手段
headers = {
    'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}
# 请求对象的定制
request = urllib.request.Request(url=url,headers=headers)
# 模拟浏览器向服务器发送请求
response = urllib.request.urlopen(request)
# 获取响应的内容
content = response.read().decode('utf-8')
# 打印数据
print(content)