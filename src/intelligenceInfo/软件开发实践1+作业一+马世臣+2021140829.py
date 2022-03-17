import re

"""
代码作业：只用一种正则表达式把以下日期数字提取出来
xxx出生于1995年6月1日、xxx出生于1995/6/1、xxx出生于1995-6-1、xxx出生于1995-06-01、xxx出生于1995-06
"""
if __name__ == '__main__':
    arr = ["xxx出生于1995年6月1日他27岁了的分工了抗击圣诞快乐国际快递福建高考时代峻峰缸发动机和", "xxx出生于1995/6/1", "xxx出生于1995-6-1",
           "xxx出生于1995-06-01", "xxx出生于1995-06"]
    pattern = r'^\D+(\d+)\D+(\d+)\D*(\d*)\D*'
    for s in arr:
        groups = re.match(pattern, s).groups()
        print(groups)
    '''
    结果
    ('1995', '6', '1')
    ('1995', '6', '1')
    ('1995', '6', '1')
    ('1995', '06', '01')
    ('1995', '06', '')
    '''
