from functools import reduce, lru_cache, cache
from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class DSU:
    def __init__(self, dataList=None):
        if dataList is None:
            dataList = []
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
    def largestIsland(self, grid: List[List[int]]) -> int:
        visited = set()
        n = len(grid)
        dsu = DSU(range(n * n))
        directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]

        res = 0
        zeros = []
        for i in range(n):
            for j in range(n):
                index = i * n + j
                if grid[i][j] == 1:
                    for x, y in directions:
                        nx = i + x
                        ny = j + y
                        if 0 <= nx < n and 0 <= ny < n and grid[nx][ny] == 1:
                            dsu.union(index, nx * n + ny)
                    res = max(res, dsu.size[dsu.find(index)])
                else:
                    count = 0
                    for x, y in directions:
                        nx = i + x
                        ny = j + y
                        if 0 <= nx < n and 0 <= ny < n and grid[nx][ny] == 1:
                            count += 1
                    if count > 0:
                        zeros.append([i, j])
        for i, j in zeros:
            neighbors = set()
            for x, y in directions:
                nx = i + x
                ny = j + y
                if 0 <= nx < n and 0 <= ny < n and grid[nx][ny] == 1:
                    neighbors.add(dsu.find(nx * n + ny))
            count = 1
            for neighbor in neighbors:
                count += dsu.size[neighbor]
            res = max(res, count)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.largestIsland(grid=[[1, 0], [0, 1]]))
