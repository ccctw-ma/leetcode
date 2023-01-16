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


class Allocator:

    def __init__(self, n: int):
        self.size = n
        self.empty = n
        self.full = 0
        self.buc = defaultdict(list)
        self.arr = [0] * n

    def allocate(self, size: int, mID: int) -> int:

        i = 0
        while i < self.size:
            if self.arr[i]:
                i += 1
                continue
            j = i
            while j < self.size and self.arr[j] == 0:
                j += 1
            if (j - i) >= size:
                self.buc[mID].append((i, size))
                self.fill(i, size, mID)
                return i
        return -1

    def free(self, mID: int) -> int:
        if mID in self.buc:
            ll = self.buc[mID]
            res = 0
            for item in ll:
                start, size = item
                self.clear(start, size)
                res += size
            del self.buc[mID]
            return res
        return 0

    def fill(self, start, size, id) -> None:
        for i in range(start, start + size):
            self.arr[i] = id

    def clear(self, start, size):
        for i in range(start, start + size):
            self.arr[i] = 0


class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        res = 0
        n = len(grid)
        m = len(grid[0])
        for i in range(n):
            grid[i].sort(reverse=True)
        for j in range(m):
            temp = 0
            for i in range(n):
                temp = max(temp, grid[i][j])
            res += temp
        return res

    def longestSquareStreak(self, nums: List[int]) -> int:
        nums.sort()
        res = -1
        buc = set(nums)
        for num in nums:
            ans = 0
            while num in buc:
                ans += 1
                num = num ** 2
            if ans > 1:
                res = max(res, ans)
        return res

    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        max_val, min_val = 0, 10 ** 6 + 5
        for row in grid:
            for col in row:
                max_val = max(max_val, col)
                min_val = min(min_val, col)

        def dfs(i, j, x, vis):
            if 0 <= i < m and 0 <= j < n and x > grid[i][j] and not vis[i][j]:
                vis[i][j] = True
                return 1 + dfs(i - 1, j, x, vis) + dfs(i + 1, j, x, vis) + dfs(i, j - 1, x, vis) + dfs(i, j + 1, x, vis)
            return 0

        res = [0] * len(queries)
        buc = defaultdict(int)
        for i, q in enumerate(queries):
            if q > max_val:
                res[i] = m * n
                continue
            if q <= grid[0][0]:
                continue
            if q in buc:
                res[i] = buc[q]
                continue

            vis = [[False] * n for _ in range(m)]
            res[i] = dfs(0, 0, q, vis)
            buc[q] = res[i]

        return res


if __name__ == '__main__':
    s = Solution()
