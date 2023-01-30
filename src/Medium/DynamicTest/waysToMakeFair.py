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
    def waysToMakeFair(self, nums: List[int]) -> int:
        # n = len(nums)
        # dp = [[0, 0] for _ in range(n)]
        # dp[0][0] = nums[0]
        # dp[0][1] = 0
        # for i in range(1, n):
        #     if i % 2 == 0:
        #         dp[i][0] = dp[i - 1][0] + nums[i]
        #         dp[i][1] = dp[i - 1][1]
        #     else:
        #         dp[i][0] = dp[i - 1][0]
        #         dp[i][1] = dp[i - 1][1] + nums[i]
        #
        # cnt = 0
        # if dp[-1][0] - nums[0] == dp[-1][1]:
        #     cnt += 1

        n = len(nums)

        @cache
        def ls(i):
            if i < 0:
                return 0, 0
            else:
                a, b = ls(i - 1)
                return (a + nums[i], b) if i % 2 == 0 else (a, b + nums[i])

        @cache
        def rs(j):
            if j == n:
                return 0, 0
            else:
                a, b = rs(j + 1)
                return (a + nums[j], b) if j % 2 == 0 else (a, b + nums[j])

        res = 0
        for i in range(n):
            le, lo = ls(i - 1)
            re, ro = rs(i + 1)
            if le + ro == lo + re:
                res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.waysToMakeFair(nums=[2, 1, 6, 4]))
    print(s.waysToMakeFair(nums=[1, 1, 1]))
