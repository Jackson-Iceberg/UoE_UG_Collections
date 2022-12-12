# @Time: 02/07/2022 22:54
# @Author: Jackson Zhou
import urllib.request

# https://www.baidu.com/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6

# 需求 获取 https://www.baidu.com/s?wd=周杰伦

# 模拟浏览器向服务器发送请求
url = 'https://www.baidu.com/s?wd='
response = urllib.request.urlopen(url)

# 请求对象的定制是为了解决反爬的第一种手段
headers = {
    'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

# 将 周杰伦 三个字 变成 unicode编码的格式
# 需要依赖urllib。parse
name = urllib.parse.quote('周杰伦')
url = url+name
print(url)
# 请求对象的定制
request = urllib.request.Request(url=url,headers=headers)
# 模拟浏览器向服务器发送请求
response = urllib.request.urlopen(request)
# 获取响应的内容
content = response.read().decode('utf-8')
# 打印数据
print(content)