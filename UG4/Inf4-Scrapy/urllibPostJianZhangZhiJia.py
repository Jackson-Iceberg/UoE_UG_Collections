# @Time: 14/07/2022 18:15
# @Author: Jackson Zhou
# 1. 请求对象的定制
# 2. 获取网页的源码
# 3. 下载

# 需求 下载前十页的图片

# https://sc.chinaz.com/tupian/shanshuifengjing.html
# https://sc.chinaz.com/tupian/shanshuifengjing_2.html
import urllib.request
from lxml import etree


def create_request(page):
    if (page == 1):
        url = 'https://sc.chinaz.com/tupian/shanshuifengjing.html'
    else:
        url = 'https://sc.chinaz.com/tupian/shanshuifengjing_' + str(page) + '.html'

    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36'
    }

    # 1. 请求对象的定制
    request = urllib.request.Request(url=url, headers=headers)
    return request


def get_content(request):
    # 2. 获取响应数据
    response = urllib.request.urlopen(request)
    content = response.read().decode('utf-8')
    return content


def down_load(content):
    tree = etree.HTML(content)
    name_list = tree.xpath('//div[@id="container"]//a/img/@alt')
    src_list = tree.xpath('//div[@id="container"]//a/img/@src')
    # print(len(name_list))
    # print(len(src_list))
    for i in range(len(name_list)):
        name = name_list[i]
        src = src_list[i]

        url = 'https:' + src
        urllib.request.urlretrieve(url=url, filename=name + '.jpg')


if __name__ == '__main__':
    start_page = int(input('请输入起始页码'))
    end_page = int(input('请输入结束页码'))

    for page in range(start_page, end_page + 1):
        request = create_request(page)
        content = get_content(request)
        down_load(content)
