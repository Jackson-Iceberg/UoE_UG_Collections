import scrapy


class TcSpider(scrapy.Spider):
    name = 'tc'
    allowed_domains = ['https://bj.58.com/']
    start_urls = ['https://bj.58.com/']

    def parse(self, response):
        print('成功加载58同城')
        pass

# scrapy项目的结构
# 项目名字
#     项目名字
#         spiders文件夹
#             init

#         init
#         items    定义数据结构的地方，爬取的数据都包括哪些
#         middleware 中间件 代理
#         pipelines 管道 用于处理下载的数据
#         settings  配置文件 robots协议 ua定义等

# response的属性和方法
#     response.text 获取的是响应的字符串
#     response.body 获取的是二进制数据
#     response.xpath 直接是xpath方法来解析response中的内容
#     response.extract() 提取seletor对象的data属性值
#     response.extract_first()提取的是seletor列表的第一个数据
