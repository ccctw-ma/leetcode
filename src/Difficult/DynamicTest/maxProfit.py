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
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        dpl = [0] * n
        preMin = prices[0]
        for i in range(1, n):
            dpl[i] = max(dpl[i - 1], prices[i] - preMin)
            preMin = min(preMin, prices[i])

        dpr = [0] * n
        preMax = prices[-1]
        for i in range(n - 2, -1, -1):
            dpr[i] = max(dpr[i + 1], preMax - prices[i])
            preMax = max(preMax, prices[i])

        res = 0
        for i in range(n - 1):
            res = max(res, dpl[i] + dpr[i + 1])
        res = max(res, dpl[-1], dpr[0])
        return res

    def maxProfit2(self, k: int, prices: List[int]) -> int:
        # @cache
        # def single(i, j):
        #     if i > j:
        #         return 0
        #     preMin = prices[i]
        #     res = 0
        #     for k in range(i + 1, j + 1):
        #         res = max(res, prices[k] - preMin)
        #         preMin = min(prices[k], preMin)
        #     return res
        #
        # @cache
        # def f(e, k):
        #     if k == 1:
        #         return single(0, e)
        #     res = 0
        #     for i in range(e + 1):
        #         res = max(res, f(i, k - 1) + single(i + 1, e))
        #     return res
        #
        # return f(len(prices) - 1, k)
        dp = [[-inf, 0] for _ in range(k)]
        for p in prices:
            dp[0][0] = max(dp[0][0], -p)
            dp[0][1] = max(dp[0][1], dp[0][0] + p)
            for i in range(1, k):
                dp[i][0] = max(dp[i][0], dp[i - 1][1] - p)
                dp[i][1] = max(dp[i][1], dp[i][0] + p)

        return dp[k - 1][1]


if __name__ == '__main__':
    s = Solution()
    # print(s.maxProfit(prices=[3, 3, 5, 0, 0, 3, 1, 4]))
    # print(s.maxProfit(prices=[1, 2, 3, 4, 5]))
    print(s.maxProfit2(k=2, prices=[2, 4, 1]))
    print(s.maxProfit2(k=2, prices=[3, 2, 6, 5, 0, 3]))
