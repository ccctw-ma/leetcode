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
    def countRoutes(self, locations: List[int], start: int, finish: int, fuel: int) -> int:

        mod = 10 ** 9 + 7

        @cache
        def fn(p, f):
            if f < 0:
                return 0
            res = 0
            if p == finish:
                res += 1

            for i, l in enumerate(locations):
                if i == p:
                    continue
                res += fn(i, f - (abs(locations[p] - l)))
            return res % mod

        return fn(start, fuel)


if __name__ == '__main__':
    s = Solution()
    print(s.countRoutes(locations=[2, 3, 6, 8, 4], start=1, finish=3, fuel=5
                        ))
    print(s.countRoutes(locations=[4, 3, 1], start=1, finish=0, fuel=6))
    print(s.countRoutes([1, 2, 3]
                        , 0
                        , 2
                        , 40))
