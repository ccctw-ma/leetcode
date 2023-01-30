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
    def countGoodStrings(self, low: int, high: int, zero: int, one: int) -> int:
        mod = 10 ** 9 + 7
        dp = [0] * (high + 1)
        dp[one] += 1
        dp[zero] += 1
        for i in range(min(zero, one) + 1, high + 1):
            dp[i] = (dp[i] + dp[i - zero] + dp[i - one]) % mod
        return sum(dp[low: high + 1]) % mod


if __name__ == '__main__':
    s = Solution()
    print(s.countGoodStrings(low=3, high=3, zero=1, one=1))
    print(s.countGoodStrings(low=2, high=3, zero=1, one=2))
