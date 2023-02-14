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
    def numSimilarGroups(self, strs: List[str]) -> int:
        n = len(strs)
        f = list(range(n))

        def find(x):
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return x

        def union(x, y):
            px, py = find(x), find(y)
            if px != py:
                if px > py:
                    px, py = py, px
                f[py] = px

        def check(a: str, b: str) -> bool:
            num = 0
            for ac, bc in zip(a, b):
                if ac != bc:
                    num += 1
                    if num > 2:
                        return False
            return True

        for i, s in enumerate(strs):
            for j in range(i + 1, n):
                if check(s, strs[j]):
                    union(i, j)
        res = set([find(i) for i in range(n)])
        return len(res)

    def numSimilarGroups2(self, strs: List[str]) -> int:
        n = len(strs)
        f = list(range(n))

        def find(x: int) -> int:
            if f[x] == x:
                return x
            f[x] = find(f[x])
            return f[x]

        def check(a: str, b: str) -> bool:
            num = 0
            for ac, bc in zip(a, b):
                if ac != bc:
                    num += 1
                    if num > 2:
                        return False
            return True

        for i in range(n):
            for j in range(i + 1, n):
                fi, fj = find(i), find(j)
                if fi == fj:
                    continue
                if check(strs[i], strs[j]):
                    f[fi] = fj

        ret = sum(1 for i in range(n) if f[i] == i)
        return ret
if __name__ == '__main__':
    s = Solution()
    # print(s.numSimilarGroups(strs=["tars", "rats", "arts", "star"]))
    # print(s.numSimilarGroups(strs=["omv", "ovm"]
    #                          ))
    print(s.numSimilarGroups(["jmijc", "imjjc", "jcijm", "cmijj", "mijjc"]))
    print(s.numSimilarGroups(["nmiwx", "mniwx", "wminx", "mnixw", "xnmwi"]))
