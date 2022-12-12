# @Time: 13/07/2022 21:24
# @Author: Jackson Zhou
import urllib.request

url = 'http://www.baidu.com/s?wd=ip'

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
}

request = urllib.request.Request(url=url, headers=headers)

proxies = {
    # 需要购买稳定的匿名ip
    'http': '47.105.91.226:8118'
}

# 1. 获取handler对象
handler = urllib.request.ProxyHandler(proxies=proxies)
# 2. 获取opener对象
opener = urllib.request.build_opener(handler)
# 3. 调用opener方法
response = opener.open(request)
# 保存

content = response.read().decode('utf-8')

print(content)
