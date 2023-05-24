import time, re
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
    def frogPosition(self, n: int, edges: List[List[int]], t: int, target: int) -> float:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        g[1].append(0)

        buc = defaultdict(float)

        def dfs(x, step, tot, p):
            nn = len(g[x])
            if step == 0 or nn == 1:
                buc[x] += tot
                return

            for y in g[x]:
                if y == p:
                    continue
                dfs(y, step - 1, tot * (1 / (nn - 1)), x)

        dfs(1, t, 1, -1)
        return buc[target]


if __name__ == '__main__':
    s = Solution()
    print(s.frogPosition(n=7, edges=[[1, 2], [1, 3], [1, 7], [2, 4], [2, 6], [3, 5]], t=2, target=4))
    print(s.frogPosition(n=7, edges=[[1, 2], [1, 3], [1, 7], [2, 4], [2, 6], [3, 5]], t=1, target=7))
    print(s.frogPosition(3
                         , [[2, 1], [3, 2]]
                         , 1
                         , 2))
