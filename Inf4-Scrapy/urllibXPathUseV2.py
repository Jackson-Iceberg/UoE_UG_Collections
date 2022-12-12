# @Time: 14/07/2022 17:11
# @Author: Jackson Zhou
import urllib.request
from lxml import etree

url = "https://www.baidu.com"
headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

# 1. 请求对象的定制
request = urllib.request.Request(url=url, headers=headers)

# 2. 获取响应数据
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')

# 3. 解析服务器文件
tree = etree.HTML(content)

result = tree.xpath('//*[@id="su"]/@value')
print(result[0])
