import scrapy
from scrapy_dangdangwang.items import ScrapyDangdangwangItem


class DangSpider(scrapy.Spider):
    name = 'dang'
    allowed_domains = ['http://category.dangdang.com']
    start_urls = ['http://category.dangdang.com/cp01.01.02.00.00.00.html']

    base_url = 'http://category.dangdang.com/pg'
    page = 1

    def parse(self, response):
        #         pipelines 下载数据
        #         items 定义数据结构
        #         src = // ul[@id="component_59"]/li//img/@src
        #         alt = // ul[@id="component_59"]/li//img/@alt
        #         price = // ul[@id="component_59"]/li//p[@class="price"]/span[1]/text()
        li_list = response.xpath('//ul[@id="component_59"]/li')
        for li in li_list:
            src = li.xpath('.//img/@data-original').extract_first()
            if src:
                src = src
            else:
                src = li.xpath('.//img/@src').extract_first()
            name = li.xpath('.//img/@alt')
            price = li.xpath('.//p[@class="price"]/span[1]/text()')
            # print(src,name,price)
            book = ScrapyDangdangwangItem(src=src, name=name, price=price)
            yield book

            if self.page < 100:
                self.page = self.page + 1
                url = self.base_url + str(self.page) + '-cp01.01.02.00.00.00.html'
                yield scrapy.Request(url=url, callback=self.parse)
