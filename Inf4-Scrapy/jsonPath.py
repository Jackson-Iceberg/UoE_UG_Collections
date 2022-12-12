# @Time: 15/07/2022 11:14
# @Author: Jackson Zhou

import json
import jsonpath

obj = json.load(open('jsonExample.json', 'r', encoding='utf-8'))

# 书店所有书的作者
author_list = jsonpath.jsonpath(obj, "$.store.book[*].author")
print(author_list)

# 所有的作者
authors_list = jsonpath.jsonpath(obj, "$..author")
print(authors_list)

# sotre下面所有的元素
tag_list = jsonpath.jsonpath(obj, "$.store.*")
print(tag_list)

# sotre里面所有东西的price
price_list = jsonpath.jsonpath(obj, "$.store..price")
print(price_list)

# 第三个书
book = jsonpath.jsonpath(obj, "$..book[2]")
print(book)

# 最后一本书
finalBook = jsonpath.jsonpath(obj, '$..book[(@.length-1)]')
print(finalBook)

# 前两本书
FirsttwoBook = jsonpath.jsonpath(obj, "$..book[0,1]")
# FirsttwoBook = jsonpath.jsonpath(obj,"$..book[:2]")
print(FirsttwoBook)

# 条件过滤，需要在（）的前面添加一个？
# 过滤初所有的包含isdn的书
book_list_containsISDN = jsonpath.jsonpath(obj, '$..book[?(@.isbn)]')
print(book_list_containsISDN)

# 过滤出超过10元的书
bookOverTen = jsonpath.jsonpath(obj, '$..book[?(@.price>10)]')
print(bookOverTen)
