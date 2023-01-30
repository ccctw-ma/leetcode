import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class DSU:
    def __init__(self, dataList):
        self.parent = {}
        self.size = {}
        self.setsCount = len(dataList)

        for x in dataList:
            self.parent[x] = x
            self.size[x] = 1

    def isExit(self, x):
        return x in self.parent

    def add(self, x):
        self.parent[x] = x
        self.size[x] = 1
        self.setsCount += 1

    def find(self, x):
        while x != self.parent[x]:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        return x

    def union(self, x, y):
        px = self.find(x)
        py = self.find(y)
        if px != py:
            sx = self.size[px]
            sy = self.size[py]
            if sx < sy:
                px, py = py, px
            self.parent[py] = px
            self.size[px] += self.size[py]
            self.setsCount -= 1

    def isConnected(self, x, y):
        return self.find(x) == self.find(y)

    def disConnected(self, x):
        self.parent[x] = x


class Solution:
    def findAllPeople(self, n: int, meetings: List[List[int]], firstPerson: int) -> List[int]:
        dsu = DSU(list(range(n)))
        dsu.union(0, firstPerson)
        meetings.sort(key=lambda x: x[2])

        i = 0
        while i < len(meetings):
            j = i
            while j < len(meetings) and meetings[i][2] == meetings[j][2]:
                dsu.union(meetings[j][0], meetings[j][1])
                j += 1
            for k in range(i, j):
                if not dsu.isConnected(0, meetings[k][0]):
                    dsu.disConnected(meetings[k][0])
                    dsu.disConnected(meetings[k][1])
            i = j
        return [i for i in range(n) if dsu.isConnected(0, i)]


if __name__ == '__main__':
    s = Solution()
    print(s.findAllPeople(n=5, meetings=[[3, 4, 2], [1, 2, 1], [2, 3, 1]], firstPerson=1))
    print(s.findAllPeople(6
                          , [[0, 2, 1], [1, 3, 1], [4, 5, 1]]
                          , 1))
