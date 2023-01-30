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
    def getKth(self, lo: int, hi: int, k: int) -> int:

        @cache
        def f(x):
            if x == 1:
                return 0
            if x % 2:
                return 1 + f(x * 3 + 1)
            else:
                return 1 + f(x // 2)

        return sorted([(f(x), x) for x in range(lo, hi + 1)])[k - 1][1]


if __name__ == '__main__':
    s = Solution()
    print(s.getKth(lo=12, hi=15, k=2))
