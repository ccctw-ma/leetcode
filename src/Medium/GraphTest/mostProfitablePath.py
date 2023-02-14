import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def mostProfitablePath(self, edges: List[List[int]], bob: int, amount: List[int]) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        # find bob to 0
        bobPath = {}

        def f(x, p):
            if x == bob:
                bobPath[x] = 0
                return
            for y in g[x]:
                if y == p:
                    continue
                f(y, x)
                if y in bobPath:
                    bobPath[x] = bobPath[y] + 1

        f(0, -1)

        q = deque([(0, amount[0])])
        step = 1
        vis = set()

        res = -inf
        while q:
            n = len(q)
            for _ in range(n):
                x, c = q.popleft()
                vis.add(x)
                for y in g[x]:
                    if y in vis:
                        continue
                    nc = c
                    if y not in bobPath or (y in bobPath and step < bobPath[y]):
                        nc += amount[y]
                    if y in bobPath and step == bobPath[y]:
                        nc += amount[y] // 2

                    if len(g[y]) == 1:
                        res = max(res, nc)
                    q.append((y, nc))

            step += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.mostProfitablePath(edges=[[0, 1], [1, 2], [1, 3], [3, 4]], bob=3, amount=[-2, 4, 2, -4, 6]))
    print(s.mostProfitablePath(edges=[[0, 1]], bob=1, amount=[-7280, 2350]))
