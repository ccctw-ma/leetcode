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
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        n = len(arr)

        @cache
        def m(i, j):
            if i == j:
                return arr[i]
            if i == j - 1:
                return max(arr[i], arr[j])
            return max(arr[i], arr[j], m(i + 1, j - 1))

        dp = [0] * n
        for i in range(k):
            dp[i] = m(0, i) * (i + 1)

        for i in range(k, n):
            for j in range(i - k + 1, i + 1):
                dp[i] = max(dp[i], dp[j - 1] + m(j, i) * (i - j + 1))

        return dp[n - 1]


if __name__ == '__main__':
    s = Solution()
    print(s.maxSumAfterPartitioning(arr=[1, 15, 7, 9, 2, 5, 10], k=3))
