import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right

from operator import or_
from typing import List

# 1、求字符差值
import sortedcontainers


def find_character_difference():
    print(ord('a') - ord('c'))


# 2、字符串反转
def string_reverse():
    s = "leetcode"
    print(s[::-1])


# 3、数组元素计数
def array_element_count():
    arr = [1, 2, 2, 4, 5, 5]
    print(Counter(arr))


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
    c = Counter(colors)
    colors2 = ['red', 'red']
    c2 = Counter(colors2)
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
    print(bisect.bisect_left(a, 8))
    # 1
    print(bisect.bisect_right(a, 8))
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


def zip_longest_function():
    arr1 = ['a', 'b', 'c']
    arr2 = ['1', '2', '3', '4']
    for letter, number in zip_longest(arr1, arr2, fillvalue='*'):
        print(letter, number)


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


# 19 sortedcontainers
def sorted_container_function():
    from sortedcontainers import SortedList
    arr = sortedcontainers.SortedList(key=lambda x: -x)

    arr.add(4)
    arr.add(6)
    arr.add(1)
    arr.add(2)
    for i in arr:
        print(i)

    valueSortedDict = sortedcontainers.SortedValuesView()


# 20、usageofsort
def usage_of_sortfunc():
    arr = [(1, 2), (1, 1), (1, 3), (4, 2), (4, 1), (5, 2), (1, 10), (1, -2)]
    arr.sort(key=lambda x: (x[0] ** 2, -x[1]))

    print(arr)
    ss = ['a', 'aa', 'ab', 'cc', 'abc']
    print(sorted(ss, key=lambda x: (-len(x), ord(x[-1]))))


# 21、usageofitertools
def usage_of_itertools():
    arr = [1, 2, 3, 4, 5]

    def fn(a, b):
        return a * b

    # 求前缀和
    print(list(accumulate(arr)))
    print(list(accumulate(arr, fn)))

    # 返回迭代表中元素的连续r长度组合。
    print(list(combinations(arr, 2)))
    for i, j, k, l in combinations(arr, 4):
        print(i, j, k, l)

    # 指针不用从+1开始
    print(list(combinations_with_replacement(arr, 2)))

    # 排列组合
    print(list(permutations(arr, 2)))

    #  product(A, B) returns the same as:  ((x,y) for x in A for y in B).
    print(list(product([1, 2, 3], [4, 5, 6])))

    print(perm(4, 2))
    print(comb(4, 2))


def countSpecialNumbers(self, n: int) -> int:  # 小于等于n的  没有重复digit的num 的 个数
    digits = []
    while n > 0:
        digits.append(n % 10)
        n //= 10
    digits = digits[::-1]  # 从左至右 存储n的每一位上的数字
    k = len(digits)
    visited = [0 for _ in range(10)]  # 记录从左至右 n用了几次了！！！！！！！！
    res = 0
    # 前面是0的情况 0XXXXX  00XXXXX  000XXXX 00000XXX 有效数字的位数自然就是k-1 ~ 1位
    for i in range(1, k):
        res += 9 * self.A(i - 1, 9)  # 有效的i位中，首位有1~9 共9种选择  后面的i-1位是从9中挑选（i-1）种  有序排列
    # 前面没有0
    for i in range(0, k):  # n从左至右遍历
        num = digits[i]
        min_digit = 1 if i == 0 else 0
        for x in range(min_digit, num):  # 4567   选择第0位  第0位可选的数在1~3中选择  选择第1位  可选的数为0~4（4）
            if visited[x] == 0:  # n没用过！！！！！！！！！！！
                res += self.A(k - i - 1, 10 - i - 1)
        visited[num] += 1
        if visited[num] > 1:  # 4456  整理完44以后，就不用整理了  4400~4456一定是有重复的
            break
        if i == k - 1:  # 我们都是让x比num小，如果一直到最后一位了，n本身也算一个的
            res += 1
    return res


def is_in_poly(p, poly):  # 判断一个点是否在一个多边形内部
    """
    :param p: [x, y]
    :param poly: [[], [], [], [], ...]
    :return:
    """
    px, py = p
    is_in = False
    for i, corner in enumerate(poly):
        next_i = i + 1 if i + 1 < len(poly) else 0
        x1, y1 = corner
        x2, y2 = poly[next_i]
        if (x1 == px and y1 == py) or (x2 == px and y2 == py):  # if point is on vertex
            is_in = True
            break
        if min(y1, y2) < py <= max(y1, y2):  # find horizontal edges of polygon
            x = x1 + (py - y1) * (x2 - x1) / (y2 - y1)
            if x == px:  # if point is on edge
                is_in = True
                break
            elif x > px:  # if point is on left-side of line
                is_in = not is_in
    return is_in


# o(n ^ 3)求得每个点到另外所有点的最短距离
def floyd(n: int, g: List[List[int]]):
    for t in range(n):
        for a in range(n):
            for b in range(n):
                if t != a and t != b:
                    g[a][b] = min(g[a][b], g[a][t] + g[t][b])


def dijkstra(start: int, g: List[List[int]], n: int) -> List[int]:
    MAX = 10 ** 9 + 7
    dis = [MAX] * n
    h = [(0, start)]
    dis[start] = 0
    vis = set()
    while h:
        cost, x = heappop(h)
        vis.add(x)
        # 访问x的邻接点
        for y, c in g[x]:
            if y in vis:
                continue
            ncost = cost + c
            if ncost < dis[y]:
                dis[y] = ncost
                heappush(h, (ncost, y))
    return dis


if __name__ == '__main__':
    # t1 = time.time()
    # print(fibonacci(200))
    # t2 = time.time()
    # print(t2 - t1)
    # reduce_function()
    # dictionary_traversal()
    # usage_of_bisect()
    # PriorityQueue_structure()
    # print(usage_of_bisect())
    # heapq_function()
    # sorted_container_function()
    # zip_longest_function()
    usage_of_itertools()
    # 2 ** 14284
    num = 10
    arr = bin(num)[2:]
    print(arr)
    print(gcd(0, 2))