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
    def findRotateSteps(self, ring: str, key: str) -> int:

        buc = defaultdict(list)
        for i, c in enumerate(ring):
            buc[c].append(i)
        n = len(ring)

        @cache
        def f(idx: int, k: str) -> int:
            if len(k) == 0:
                return 0
            if ring[idx] == k[0]:
                return 1 + f(idx, k[1:])
            else:
                res = 10 ** 9
                for i in buc[k[0]]:
                    minDis = min((i - idx + n) % n, (idx - i + n) % n)
                    res = min(res, 1 + minDis + f(i, k[1:]))
                return res

        return f(0, key)


if __name__ == '__main__':
    s = Solution()
    print(s.findRotateSteps(ring="godding", key="gd"))
    print(s.findRotateSteps(ring="godding", key="godding"))
