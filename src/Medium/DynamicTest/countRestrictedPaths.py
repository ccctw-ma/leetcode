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
    def countRestrictedPaths(self, n: int, edges: List[List[int]]) -> int:
        MOD = 10 ** 9 + 7
        MAX = 10 ** 12
        g = defaultdict(list)
        for a, b, w in edges:
            g[a].append((b, w))
            g[b].append((a, w))

        # dijkstra
        dis = defaultdict(lambda: MAX)
        dis[n] = 0
        vis = set()
        h = [(0, n)]
        while h:
            cost, x = heappop(h)
            vis.add(x)
            for y, c in g[x]:
                if y in vis:
                    continue
                nc = cost + c
                if nc < dis[y]:
                    dis[y] = nc
                    heappush(h, (nc, y))

        @cache
        def fn(x):
            if x == n:
                return 1
            res = 0
            for y, _ in g[x]:
                if dis[x] > dis[y]:
                    res += fn(y)
            return res % MOD

        print(dis)
        return fn(1)


if __name__ == '__main__':
    s = Solution()
    print(s.countRestrictedPaths(n=5,
                                 edges=[[1, 2, 3], [1, 3, 3], [2, 3, 1], [1, 4, 2], [5, 2, 2], [3, 5, 1], [5, 4, 10]]))
    print(s.countRestrictedPaths(5, [[1, 2, 1], [2, 3, 1], [3, 4, 1], [4, 5, 1]]))
    print("123".isdigit())