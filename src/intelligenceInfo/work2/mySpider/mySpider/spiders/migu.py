import scrapy

from lxml import etree
# from mySpider.items import MusicItem

class MiguSpider(scrapy.Spider):
    name = 'migu'
    allowed_domains = ['music.migu.cn']
    start_urls = ['http://music.migu.cn/v3']

    def parse(self, response):
        content = response.body.decode('utf-8')
        tree = etree.HTML(content)
        name_list = tree.xpath('.//div[@class="wrapper-scroll"]/div/div/div[@class="item-info"]/p['
                               '@class="song-name"]/a')
        for song_name in name_list:
            print(song_name.text)
            item = {
                'song_name' : song_name.text
            }
            yield item

