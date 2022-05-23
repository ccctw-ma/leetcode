# -*- codeing: utf-8 -*-
from elasticsearch_dsl import Document, Keyword, Text, Double
from elasticsearch_dsl.connections import connections

es = connections.create_connection(host="127.0.0.1")


class MusicType(Document):
    # 设置index名称和document名称
    class Index:
        name = "music_index"
        settings = {
            "number_of_shards": 1,
            "number_of_replicas": 0,
        }
        mappings = {
            "properties": {
                "song_name": {
                    "type": "keyword"
                },
            }
        }

    # TODO:fileds定义
    song_name = Keyword()  # 不分词，默认保留256个字符

    def __init__(self, item):
        super(MusicType, self).__init__()  # 调一下父类的init，避免init重写导致一些init操作没执行
        self.assignment(item)

    # TODO:将item转换为es的数据
    def assignment(self, item):
        # TODO：给没爬到的字段赋默认值：空串
        keys = ['song_name']
        for key in keys:
            try:
                item[key]
            except:
                item[key] = ''

        self.song_name = item['song_name']
