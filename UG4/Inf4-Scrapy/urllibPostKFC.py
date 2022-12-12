# @Time: 12/07/2022 15:35
# @Author: Jackson Zhou
import urllib.parse
import urllib.request


def create_request(pages):
    base_url = 'http://www.kfc.com.cn/kfccda/ashx/GetStoreList.ashx?op=cname'
    data = {
        'cname': '北京',
        'pid': '',
        'pageIndex': pages,
        'pageSize': '10'
    }
    data = urllib.parse.urlencode(data).encode('utf-8')

    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/100.0.4896.88 Safari/537.36 '
    }
    requests = urllib.request.Request(url=base_url, headers=headers, data=data)
    return requests


def get_content(request):
    response = urllib.request.urlopen(request)
    content = response.read().decode('utf-8')
    return content


def down_load(content, page):
    fp = open('kfc_' + str(page) + '.json', 'w', encoding='utf-8')
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
