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
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        g = defaultdict(list)
        for u, v, w in roads:
            g[u].append((v, w))
            g[v].append((u, w))

        # dijkstra 求 0 -> n - 1的最短路

        dis = defaultdict(lambda: inf)
        dis[0] = 0

        dp = [0] * n
        dp[0] = 1

        mod = 10 ** 9 + 7
        h = [(0, 0)]
        while h:
            c, x = heappop(h)
            for y, w in g[x]:
                nc = c + w
                if nc < dis[y]:
                    dis[y] = nc
                    heappush(h, (nc, y))
                    dp[y] = dp[x]
                elif nc == dis[y]:
                    dp[y] = (dp[y] + dp[x]) % mod

        return dp[n - 1]


if __name__ == '__main__':
    s = Solution()
    print(s.countPaths(n=7,
                       roads=[[0, 6, 7], [0, 1, 2], [1, 2, 3], [1, 3, 3], [6, 3, 3], [3, 5, 1], [6, 5, 1], [2, 5, 1],
                              [0, 4, 5], [4, 6, 2]]))
    # print(s.countPaths(n=2, roads=[[1, 0, 10]]))
