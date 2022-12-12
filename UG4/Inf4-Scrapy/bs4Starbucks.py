# @Time: 16/07/2022 19:11
# @Author: Jackson Zhou
import urllib.request

url = 'https://www.starbucks.com.cn/menu/'

response = urllib.request.urlopen(url)
content = response.read().decode('utf-8')

from bs4 import BeautifulSoup

soup = BeautifulSoup(content,'lxml')

name_list = soup.select('ul[class="grid padded-3 product"] strong')

for name in name_list:
    print(name.get_text())