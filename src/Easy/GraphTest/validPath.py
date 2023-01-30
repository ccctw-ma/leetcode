import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:

        p = list(range(n))

        def find(x):
            while x != p[x]:
                p[x] = p[p[x]]
                x = p[x]
            return x

        def merge(x, y):
            px, py = find(x), find(y)
            if px != py:
                p[py] = px

        for a, b in edges:
            merge(a, b)
        return find(source) == find(destination)


if __name__ == '__main__':
    s = Solution()
