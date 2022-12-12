import scrapy


class BaiduSpider(scrapy.Spider):
    # 爬虫的名字，用于运行爬虫，使用的值
    name = 'baidu'
    # 允许访问的域名
    allowed_domains = ['www.baidu.com']
    # 起始的URL地址，指的是第一次要访问的域名
    # startURL是在allowed doamins前面加一个http：//
    start_urls = ['http://www.baidu.com/']

    def parse(self, response):
        print("Hello scrapy你好爬虫")
        pass
