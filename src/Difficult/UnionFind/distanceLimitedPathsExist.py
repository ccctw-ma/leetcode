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


class Solution:
    def distanceLimitedPathsExist(self, n: int, edgeList: List[List[int]], queries: List[List[int]]) -> List[bool]:
        g = sorted(edgeList, key=lambda x: x[2])
        res = [False] * len(queries)

        f = list(range(n))

        def find(x: int) -> int:
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return f[x]

        def merge(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px != py:
                if py < px:
                    px, py = py, px
                f[py] = px

        idx = 0
        for i, query in sorted(enumerate(queries), key=lambda x: x[1][2]):
            p, q, limit = query
            while idx < len(edgeList) and g[idx][2] < limit:
                a, b, d = g[idx]
                idx += 1
                merge(a, b)
            res[i] = (find(p) == find(q))
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.distanceLimitedPathsExist(n=3, edgeList=[[0, 1, 2], [1, 2, 4], [2, 0, 8], [1, 0, 16]],
                                      queries=[[0, 1, 2], [0, 2, 5]]
                                      ))
