from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right

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

class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        g = [[] for _ in range(n)]
        for x, y in dislikes:
            g[x - 1].append(y - 1)
            g[y - 1].append(x - 1)
        color = [0] * n  # color[x] = 0 表示未访问节点 x

        def dfs(x: int, c: int) -> bool:
            color[x] = c
            return all(color[y] != c and (color[y] or dfs(y, -c)) for y in g[x])

        return all(c or dfs(i, 1) for i, c in enumerate(color))


if __name__ == '__main__':
    s = Solution()
