# @Time: 16/07/2022 19:35
# @Author: Jackson Zhou
# 导入selenium包

from selenium import webdriver

# 创建浏览器操作对象
# path = "chromedriver.exe"
driver = webdriver.Chrome(executable_path="/Users/jackson-zhou/PycharmProjects/pythonProject/chromedriver")
# browser = webdriver.Chrome(path)

url = 'https://www.jd.com'

# 访问网站
driver.get(url)

content = driver.page_source
print(content)
