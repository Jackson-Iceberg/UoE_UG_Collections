# @Time: 02/07/2022 15:32
# @Author: Jackson Zhou
import urllib.request

# 下载网页
url_page = 'https://www.google.com/'

# url代表的是下载的路径，filename文件的名字
# 在python中，可以是 变量=值，也可以直接写值
urllib.request.urlretrieve(url_page,'google.html')

# 下载图片
url_image = 'https://m.media-amazon.com/images/M/MV5BNGQ2MjJiZTAtOWE1OS00MjRiLTg4ZTgtN2UxN2E1ZmQ2NzZhXkEyXkFqcGdeQXVyOTc3NzM3NTE@._V1_.jpg'
urllib.request.urlretrieve(url=url_image,filename='pengyuyan.jpg')

# 下载视频
url_video = 'https://www.ixigua.com/e06ee95c-70e9-4b97-bfd3-d716ecc5191d'
urllib.request.urlretrieve(url=url_video,filename='xigua.mp4')
# <video class="" autoplay="" playsinline="true" x5-playsinline="true" webkit-playsinline="true" mediatype="video" src="blob:https://www.ixigua.com/e06ee95c-70e9-4b97-bfd3-d716ecc5191d"></video><video class="" autoplay="" playsinline="true" x5-playsinline="true" webkit-playsinline="true" mediatype="video" src="blob:https://www.ixigua.com/e06ee95c-70e9-4b97-bfd3-d716ecc5191d"></video>