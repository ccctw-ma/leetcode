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
    def minTrioDegree(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        degrees = [0] * (n + 1)
        res = inf
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
            degrees[a] += 1
            degrees[b] += 1
        for a in range(1, n + 1):
            g[a].sort(key=lambda x: len(g[x]))

        for a in range(n + 1):
            if len(g[a]) < 2:
                continue
            for b in range(a + 1, n + 1):
                for c in range(b + 1, n + 1):
                    if b in g[a] and c in g[a] and c in g[b]:
                        res = min(res, degrees[a] + degrees[b] + degrees[c] - 6)
        return res if res != inf else -1

    def minTrioDegree2(self, n: int, edges: List[List[int]]) -> int:

        m = defaultdict(list)
        low = max_num = n * (n - 1) / 2
        for u, v in edges:
            m[u].append(v)
            m[v].append(u)

        # 这里做sort的原因是下面for循环中有个break，
        # 这要求第一个找到的满足条件的nb对应的节点list长度是最短的，
        # 这样才能保证每次找到的都是当前考虑的(u, v)对可构成的triplet中总degree最小的
        for elem in m.keys():
            m[elem].sort(key=lambda k: len(m[k]))

        for u, v in edges:
            for nb in m[u]:
                if nb in m[v]:
                    low = min(low, len(m[nb]) + len(m[u]) + len(m[v]) - 6)
                    break
        return low if low != max_num else -1


if __name__ == '__main__':
    s = Solution()
    print(s.minTrioDegree(n=6, edges=[[1, 2], [1, 3], [3, 2], [4, 1], [5, 2], [3, 6]]))



