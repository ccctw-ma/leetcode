import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def findTheCity(self, n: int, edges: List[List[int]], distanceThreshold: int) -> int:
        MAX = 10 ** 9
        g = [[MAX] * n for _ in range(n)]
        for a, b, c in edges:
            g[a][b] = c
            g[b][a] = c

        # @cache
        # def dfs(a, b):
        #     if a > b:
        #         return dfs(b, a)
        #     if a == b:
        #         return 0
        #     if g[a][b]:
        #         return g[a][b]
        #     res = MAX
        #     for next in range(n):
        #         if next != b and next != a and g[a][next]:
        #             res = min(res, g[a][next] + dfs(next, b))
        #     return res

        for t in range(n):
            for a in range(n):
                for b in range(n):
                    if t != a and t != b:
                        g[a][b] = min(g[a][b], g[a][t] + g[t][b])
        # print(g)

        arr = []
        for a in range(n):
            cnt = 0
            for b in range(n):
                if a != b and g[a][b] <= distanceThreshold:
                    cnt += 1
            arr.append((a, cnt))
        # print(arr)
        arr.sort(key=lambda x: (x[1], -x[0]))
        return arr[0][0]


if __name__ == '__main__':
    s = Solution()
    print(s.findTheCity(n=4, edges=[[0, 1, 3], [1, 2, 1], [1, 3, 4], [2, 3, 1]], distanceThreshold=4))
    print(s.findTheCity(n=5, edges=[[0, 1, 2], [0, 4, 8], [1, 2, 3], [1, 4, 2], [2, 3, 1], [3, 4, 1]],
                        distanceThreshold=2))
