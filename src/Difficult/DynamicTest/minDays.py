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
    def minDays(self, n: int) -> int:

        @cache
        def f(x):
            if x == 0:
                return -1
            # if x == 1:
            #     return 1
            # if x % 2 and x % 3:
            #     return 1 + f(x - 1)

            return 1 + min(x % 2 + f(x // 2), x % 3 + f(x // 3))

        return f(n)


if __name__ == '__main__':
    s = Solution()
    print(s.minDays(820592))
    print(s.minDays(56))