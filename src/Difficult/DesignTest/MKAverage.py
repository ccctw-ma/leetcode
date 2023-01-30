import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class MKAverage:

    def __init__(self, m: int, k: int):
        self.m = m
        self.k = k
        self.size = 0
        self.q = deque([])
        # 最小的k个数
        self.s1 = SortedList()
        # 中间的m-2k个数
        self.s2 = SortedList()
        # 最大的k个数
        self.s3 = SortedList()

        self.s2sum = 0

    def addElement(self, num: int) -> None:
        self.size += 1
        # 先直接塞入队列中
        if self.size <= self.m:
            self.q.append(num)
            self.s2.add(num)
            # 已经塞满满足计算条件，把这些数据都放入队列中
            if self.size == self.m:
                self.s1 = SortedList(self.s2[:self.k])
                self.s3 = SortedList(self.s2[self.m - self.k:])
                self.s2 = SortedList(self.s2[self.k:self.m - self.k])
                self.s2sum = sum(self.s2)
        # 插入的时候同时删除
        else:
            self.q.append(num)
            add = num
            remove = self.q.popleft()
            # 放入队列
            if add >= self.s3[0]:
                self.s3.add(add)
                self.s2.add(self.s3[0])
                self.s2sum += self.s3[0]
                self.s3.pop(0)
            elif add <= self.s1[-1]:
                self.s1.add(add)
                self.s2.add(self.s1[-1])
                self.s2sum += self.s1[-1]
                self.s1.pop(-1)
            else:
                self.s2.add(add)
                self.s2sum += add

            # 删除元素
            if remove >= self.s3[0]:
                self.s3.remove(remove)
                self.s3.add(self.s2[-1])
                self.s2sum -= self.s2[-1]
                self.s2.pop(-1)
            elif remove <= self.s1[-1]:
                self.s1.remove(remove)
                self.s1.add(self.s2[0])
                self.s2sum -= self.s2[0]
                self.s2.pop(0)
            else:
                self.s2.remove(remove)
                self.s2sum -= remove

    def calculateMKAverage(self) -> int:
        if self.size < self.m:
            return -1
        else:
            return self.s2sum // (self.m - self.k * 2)


# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()

if __name__ == '__main__':
    # s = Solution()
    o = MKAverage(3, 1)
    o.addElement(58916)
    o.addElement(61899)
    print(o.calculateMKAverage())
    o.addElement(85406)
    o.addElement(49757)
    print(o.calculateMKAverage())
    o.addElement(27520)
    o.addElement(12303)
    print(o.calculateMKAverage())
    o.addElement(63945)
