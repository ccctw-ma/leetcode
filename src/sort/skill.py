import collections
import queue
import time
from functools import lru_cache, reduce

from operator import or_


# 1、求字符差值
def find_character_difference():
    print(ord('a') - ord('c'))


# 2、字符串反转
def string_reverse():
    s = "leetcode"
    print(s[::-1])


# 3、数组元素计数
def array_element_count():
    arr = [1, 2, 2, 4, 5, 5]
    print(collections.Counter(arr))


# 4、字典遍历
def dictionary_traversal():
    cnt = {1: 4, 2: 3}
    for item in cnt.items():
        print(item)
    for item in cnt.keys():
        print(item)
    for item in cnt.values():
        print(item)


# 5、初始化全0数组
def initialize_an_array_of_all_0():
    length = 10
    li = [0] * length
    print(li)
    li = [0 for _ in range(length)]
    print(li)
    li = [[0] * 3 for _ in range(4)]
    print(li)


# 6、Counter计数
def counter_count():
    colors = ['red', 'blue', 'red', 'green', 'blue', 'blue']
    c = collections.Counter(colors)
    colors2 = ['red', 'red']
    c2 = collections.Counter(colors2)
    print(c)
    print(dict(c))
    print(dict(c2))
    print(dict(c + c2))


# 7、bisect的用法 bisect是python内置模块，用于有序序列的插入和查找。
def usage_of_bisect():
    import bisect
    """
    查找： bisect(array, item)
    插入： insort(array, item)
    """
    a = [1, 2, 2, 5, 8]
    position = bisect.bisect(a, 7)  # 找到插入位置
    # print(position)
    # 4
    bisect.insort(a, 4)  # 找到位置插入
    # print(a)
    # [1, 2, 2, 4, 5, 8]
    print(bisect.bisect_left(a, 2))
    # 1
    print(bisect.bisect_right(a, 2))
    print(bisect.bisect(a, 2))
    # 3


# 8、列表去重
def list_deduplication():
    """
    sorted方法对所有的可迭代序列都有效。 key参数来指定一个函数key参数的值为一个函数，
    此函数只有一个参数且返回一个值用来进行比较。这个技术是快速的因为key指定的函数将准确地对每个元素调用。
    :return:
    """
    l1 = [3, 4, 5, 6, 2, 1, 4, 2, 4]
    l1 = sorted(list(set(l1)))
    l1 = sorted(l1, key=lambda a: a)
    print(l1)


# 9、列表转化成字符串
def convert_list_to_string():
    li = ['a', 'b', 'c']
    print(" ".join(str(i) for i in li))


# 10、map函数
def map_function():
    a = list(map(lambda x: x ** 2, [1, 2, 3, 4, 5]))
    print(a)


# 11、双端队列
def deque_structure():
    from collections import deque
    a = deque([1, 2, 3])
    a.pop()
    print(a)
    a.append(4)
    print(a)
    a.popleft()
    print(a)
    a.appendleft(0)
    print(a)
    print(list(a))


# 12、优先队列
def PriorityQueue_structure():
    from queue import PriorityQueue
    q = PriorityQueue()
    q.put(3)
    q.put(2)
    q.put(1)
    q.put(10)
    print(q.qsize())


# 13、f-string的用法
def f_string():
    name = 'Runoob'
    print(f'Hello {name}')


# 14、zip函数的用法
def zip_function():
    teams = ['Barcelona', 'Bayern Munich', 'Chelsea']
    leagues = ['La Liga', 'Bundesliga', 'Premiere League']
    countries = ['Spain', 'Germany', 'UK']
    for team, league, country in zip(teams, leagues, countries):
        print(f'{team} plays in {league}. Country: {country}')


# 15、collections.OrderedDict有序字典
def OrderedDict_structure():
    """
    遍历得到的结果和建立字典时的顺序是一样的。
    :return: none
    """
    from collections import OrderedDict
    my_dict = OrderedDict({'a': 2, "c": 0, "b": 3, })
    for key, value in my_dict.items():
        print(key, value)


# 16、lru_cache以缓存的方式优化递归函数
@lru_cache(None, False)
def fibonacci(n):
    if n < 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


# 17、reduce reduce() 函数会对参数序列中元素进行累积。
def reduce_function():
    arr = [1, 2, 3, 4, 5, 6, 7]
    ans = reduce(lambda x, y: x + y, arr)
    print(ans)


# 18、heapq
def heapq_function():
    import heapq
    heap = []
    for i in range(11, 0, -1):
        heapq.heappush(heap, i)
    print(heap)
    # print(heapq.heappop(heap))
    print(heapq.nlargest(5, heap))
    print(heapq.nsmallest(5, heap))
    print(heapq.heappushpop(heap, 20))


if __name__ == '__main__':
    # t1 = time.time()
    # print(fibonacci(200))
    # t2 = time.time()
    # print(t2 - t1)
    # reduce_function()
    # dictionary_traversal()
    # PriorityQueue_structure()
    print(usage_of_bisect())
    # heapq_function()
