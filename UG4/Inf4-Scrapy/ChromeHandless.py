# @Time: 17/07/2022 00:48
# @Author: Jackson Zhou
from selenium import webdriver
from selenium.webdriver.chrome.options import Options

chrome_options = Options()
chrome_options.add_argument('--headerless')
chrome_options.add_argument('--disable-gpu')

path = '/Applications/Google Chrome.app'
chrome_options.binary_location = path

browser = webdriver.Chrome(chrome_options=chrome_options)

url = 'https://www.baidu.com'

browser.get(url)

browser.save_screenshot('baidu.png')
