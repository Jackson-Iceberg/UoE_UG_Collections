# @Time: 03/07/2022 17:53
# @Author: Jackson Zhou
import urllib.request
import urllib.parse

# get请求
# 获取豆瓣电影第一页数据，并且保存起来

url = "https://movie.douban.com/j/chart/top_list?type=11&interval_id=100%3A90&action=&start=0&limit=20"

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

# 1. 请求对象的定制
request = urllib.request.Request(url=url, headers=headers)

# 2. 获取响应数据
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')

# 3. 下载数据到本地
# open方法默认情况下使用gbk的编码，如果想保存汉字，那么需要在open方法中指定编码格式为utf-8
fp = open('douban.json', 'w', encoding='utf-8')
fp.write(content)

with open('douban2.json', 'w', encoding='utf-8') as fp:
    fp.write(content)

# print(content)
