# @Time: 14/07/2022 11:48
# @Author: Jackson Zhou
from lxml import etree

# xpath解析
# 1. 解析本地文件
# 2. 解析服务器响应的数据，也就是解析网页

tree = etree.parse('atguigu.html')

# 查找ul下面的li
li_list1 = tree.xpath('//body/ul/li')

# 查找所以有id的属性的li标签
# text（）获取标签中的内容
li_list2 = tree.xpath('//ul/li[@id]/text()')

# 找到id为11的li标签， 注意引号问题
li_list3 = tree.xpath('//ul/li[@id="l1"]/text()')

# 查到id为l1的li标签的class的属性值
li = tree.xpath('//ul/li[@id="l1"]/@class')

# 查询id中包含l的li标签
li_list4 = tree.xpath('//ul/li[contains(@id,"l")]/text()')

# 查询id的值以l开头的li标签
li_list5 = tree.xpath('//ul/li[starts-with(@id,"c")]/text()')

# 查询id为l1和class为c1的
li_list6 = tree.xpath('//ul/li[@id="l1"]/text() | //ul/li[@id="l2"]/text()')

# 判断列表长度用于检查
print(li_list1)
print(len(li_list1))
print(li_list2)
print(len(li_list2))
print(li_list3)
print(len(li_list3))
print(li)
print(len(li))
print(li_list4)
print(len(li_list4))
print(li_list5)
print(len(li_list5))
print(li_list6)
print(len(li_list6))
