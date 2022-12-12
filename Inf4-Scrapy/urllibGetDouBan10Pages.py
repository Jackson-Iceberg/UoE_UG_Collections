# @Time: 12/07/2022 14:49
# @Author: Jackson Zhou
# https://movie.douban.com/j/chart/top_list?type=11&interval_id=100%3A90&action=&start=0&limit=20
# https://movie.douban.com/j/chart/top_list?type=11&interval_id=100%3A90&action=&start=20&limit=20
# https://movie.douban.com/j/chart/top_list?type=11&interval_id=100%3A90&action=&start=40&limit=20

# start = 0,20,40 corresponding to page 1,2,3
# Hence the page will be (page-1)*20 = start words

# 下载豆瓣电影前10页的数据
# 1. 请求对象的定制
# 2. 获取响应数据
# 3. 下载数据
import urllib.parse
import urllib.request


def create_request(page):
    base_url = "https://movie.douban.com/j/chart/top_list?type=11&interval_id=100%3A90&action=&"

    data = {
        'start': (page - 1) * 20,
        'limit': 20
    }

    data = urllib.parse.urlencode(data)
    url = base_url + data

    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/100.0.4896.88 Safari/537.36 '
    }
    requests = urllib.request.Request(url=url, headers=headers)
    return requests


def get_content(request):
    response = urllib.request.urlopen(request)
    content = response.read().decode('utf-8')
    return content


def down_load(content, page):
    fp = open('douban_' + str(page) + '.json', 'w', encoding='utf-8')
    fp.write(content)


# 程序的入口
if __name__ == '__main__':
    start_page = int(input('请输入起始页码'))
    end_page = int(input('请输入结束页码'))

    for pages in range(start_page, end_page + 1):
        #         每一页都有自己的请求对象
        request = create_request(pages)
        # 2. 获取响应数据
        content = get_content(request)
        #         3. download
        down_load(content, pages)
