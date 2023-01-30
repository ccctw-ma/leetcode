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
    def minSpaceWastedKResizing(self, nums: List[int], k: int) -> int:
        # 超时
        preSum = [0] + list(accumulate(nums))
        n = len(nums)
        dp = [[10 ** 12] * (k + 1) for _ in range(n)]
        curMax = nums[0]
        for i in range(n):
            curMax = max(curMax, nums[i])
            dp[i][0] = curMax * (i + 1) - preSum[i + 1]
        for kk in range(1, k + 1):
            for i in range(n):
                curMax = nums[i]
                for j in range(i, kk - 1, -1):
                    curMax = max(curMax, nums[j])
                    dp[i][kk] = min(dp[i][kk], curMax * (i - j + 1) - (preSum[i + 1] - preSum[j]) + dp[j - 1][kk - 1])
        return dp[n - 1][k]

    def minSpaceWastedKResizing2(self, nums: List[int], k: int) -> int:
        @cache
        def f(i, j, k):
            if j - i <= k: return sum(nums[i:j])
            if k == 1: return max(nums[i:j]) * (j - i)
            return min(
                f(i, m, k - 1) + f(m, j, 1) for m in range(i + k - 1, j)
            )

        return f(0, len(nums), k + 1) - sum(nums)


if __name__ == '__main__':
    s = Solution()
    print(s.minSpaceWastedKResizing(nums=[10, 20, 15, 30, 20], k=2))
    print(s.minSpaceWastedKResizing(nums=[10, 20], k=0))
    print(s.minSpaceWastedKResizing(nums=[10, 20, 30], k=1))
    print(s.minSpaceWastedKResizing([2, 48, 18, 16, 15, 9, 48, 7, 44, 48]
                                    , 1))
