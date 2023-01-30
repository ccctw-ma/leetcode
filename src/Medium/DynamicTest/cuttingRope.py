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
    def cuttingRope(self, n: int) -> int:

        @cache
        def f(x):
            if x == 1:
                return 1
            res = x
            for i in range(1, x):
                res = max(res, f(i) * f(x - i))
            return res

        if n == 2:
            return 1
        if n == 3:
            return 2

        return f(n)


if __name__ == '__main__':
    s = Solution()
    print(s.cuttingRope(2))
