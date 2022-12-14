from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:

    # dsu
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        mn = m * n
        ans = [0] * len(queries)
        f = list(range(m * n))
        size = [1] * (m * n)

        def find(x: int) -> int:
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return x

        def merge(x: int, y: int) -> None:
            px = find(x)
            py = find(y)
            if px != py:
                if px > py:
                    px, py = py, px
                f[py] = px
                size[px] += size[py]

        arr = sorted((grid[i][j], i, j) for i in range(m) for j in range(n))
        idx = 0
        for i, q in sorted(enumerate(queries), key=lambda x: x[1]):
            while idx < mn and arr[idx][0] < q:
                v, x, y = arr[idx]
                for x2, y2 in (x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1):
                    if 0 <= x2 < m and 0 <= y2 < n and grid[x2][y2] < q:
                        merge(x * n + y, x2 * n + y2)
                idx += 1
            if grid[0][0] < q:
                ans[i] = size[find(0)]
        return ans

    # heap
    def maxPoints2(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        h = [(grid[0][0], 0, 0)]
        grid[0][0] = 0
        res = [0] * len(queries)
        cnt = 0
        for i, q in sorted(enumerate(queries), key=lambda x: x[1]):
            while h and h[0][0] < q:
                cnt += 1
                _, x, y = heappop(h)
                for nx, ny in (x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1):
                    if 0 <= nx < m and 0 <= ny < n and grid[nx][ny]:
                        heappush(h, (grid[nx][ny], nx, ny))
                        grid[nx][ny] = 0
            res[i] = cnt
        return res

if __name__ == '__main__':
    s = Solution()
    print(s.maxPoints2(grid=[[1, 2, 3], [2, 5, 7], [3, 5, 1]], queries=[5, 6, 2]))
