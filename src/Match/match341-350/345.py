import time, re
from operator import xor
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def circularGameLosers(self, n: int, k: int) -> List[int]:
        base = 0
        vis = {base}
        step = 1
        while True:
            next = (base + step * k) % n
            if next in vis:
                break
            vis.add(next)
            step += 1
            base = next
        res = []
        for i in range(n):
            if i not in vis:
                res.append(i + 1)
        return res

    def doesValidArrayExist(self, derived: List[int]) -> bool:
        if reduce(xor, derived) == 1:
            return False

    def maxMoves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])

        @cache
        def dfs(x, y):
            res = 0
            for dx, dy in [[-1, 1], [0, 1], [1, 1]]:
                nx, ny = x + dx, y + dy
                if 0 <= nx < m and 0 <= ny < n and grid[nx][ny] > grid[x][y]:
                    res = max(res, dfs(nx, ny))
            return 1 + res

        return max(dfs(i, 0) for i in range(m)) - 1

    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:

        f = list(range(n))

        def find(x):
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return f[x]

        def union(x, y):
            px, py = find(x), find(y)
            if px != py:
                f[py] = px

        g = defaultdict(list)
        edgeset = set()
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
            union(a, b)
            edgeset.add((a, b))
        buc = defaultdict(list)
        for x in range(n):
            px = find(x)
            buc[px].append(x)
        # print(buc)
        res = 0
        for k, v in buc.items():
            size = len(v)
            f = True
            # for a, b in combinations(v, 2):
            #     # print(a, b)
            #     if (a, b) not in edgeset and (b, a) not in edgeset:
            #         f = False
            #         break
            for x in v:
                if len(g[x]) != size - 1:
                    f = False
                    break
            if f:
                res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.circularGameLosers(n=5, k=2))
    # print(s.doesValidArrayExist(derived=[1, 1, 0]))
    # print(s.maxMoves(grid=[[2, 4, 3, 5], [5, 4, 9, 3], [3, 4, 2, 11], [10, 9, 13, 15]]))
    # print(s.maxMoves(grid=[[3, 2, 4], [2, 1, 9], [1, 1, 7]]))
    print(s.countCompleteComponents(n=6, edges=[[0, 1], [0, 2], [1, 2], [3, 4]]))
    print(s.countCompleteComponents(n=6, edges=[[0, 1], [0, 2], [1, 2], [3, 4], [3, 5]]))
