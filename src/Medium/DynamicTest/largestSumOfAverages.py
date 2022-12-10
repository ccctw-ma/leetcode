from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def largestSumOfAverages2(self, nums: List[int], K: int) -> float:
        pre_sum = [0] + list(accumulate(nums))
        n = len(nums)
        dp = [[0] * (K + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            dp[i][1] = pre_sum[i] / i
        # print(pre_sum)
        # print(dp)
        for k in range(2, K + 1):
            for i in range(1, n + 1):
                for j in range(i + 1, n + 1):
                    dp[j][k] = max(dp[j][k], dp[i][k - 1] + ((pre_sum[j] - pre_sum[i]) / (j - i)))

        return dp[n][K]

    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        n = len(nums)
        s = list(accumulate(nums, initial=0))

        @cache
        def f(i, k):
            if k == 1:
                return s[i] / i
            return max([f(j, k - 1) + (s[i] - s[j]) / (i - j) for j in range(k - 1, i)])

        return f(n, k)


if __name__ == '__main__':
    s = Solution()
    print(s.largestSumOfAverages([9, 1, 2, 3, 9], 3))
    print(s.largestSumOfAverages(nums=[1, 2, 3, 4, 5, 6, 7], k=4))
