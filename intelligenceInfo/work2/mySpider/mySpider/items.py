# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class MyspiderItem(scrapy.Item):
    # define the fields for your item here like:
    song_name = scrapy.Field()
    pass


class MusicItem(scrapy.item):
    song_name = scrapy.Field()
