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
    def canCross(self, stones: List[int]) -> bool:

        if stones[1] != 1:
            return False
        stoneSet = set(stones)

        @cache
        def f(p, k):
            if k == 0:
                return False
            if p == stones[-1]:
                return True
            if p > stones[-1]:
                return False
            if p not in stoneSet:
                return False
            return f(p + k - 1, k - 1) or f(p + k, k) or f(p + k + 1, k + 1)

        return f(1, 1)


if __name__ == '__main__':
    s = Solution()
    print(s.canCross(stones=[0, 1, 3, 5, 6, 8, 12, 17]))
    print(s.canCross(stones=[0, 1, 2, 3, 4, 8, 9, 11]))
