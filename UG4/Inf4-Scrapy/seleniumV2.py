# @Time: 17/07/2022 00:16
# @Author: Jackson Zhou
from selenium import webdriver

driver = webdriver.Chrome(executable_path="/Users/jackson-zhou/PycharmProjects/pythonProject/chromedriver")

url = "https://www.baidu.com"
driver.get(url)

import time

time.sleep(2)

# 获取文本框的对象
Textinput = driver.find_element_by_id('kw')
# 在文本框输入周杰伦
Textinput.send_keys('周杰伦')
time.sleep(2)

# 获取百度一下的按钮
button = driver.find_element_by_id('su')
# 点击按钮
button.click()
time.sleep(2)

# 滑到底部
js_button = 'document.documentElement.scrollTop=100000'
driver.execute_script(js_button)
time.sleep(2)
# 下一页
nextPage = driver.find_element_by_xpath('//a[@class="n"]')
nextPage.click()
time.sleep(2)

# 返回上一页
driver.back()
time.sleep()

# 回去
driver.forward()
time.sleep(3)
