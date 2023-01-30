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
    def minSumOfLengths(self, arr: List[int], target: int) -> int:
        n = len(arr)
        MAX = 10 ** 6
        dp = [MAX] * n
        res = MAX

        j = 0
        cur = 0
        for i in range(n):

            if i:
                cur -= arr[i - 1]
            if i > 1:
                dp[i - 1] = min(dp[i - 1], dp[i - 2])
            while j < n and cur < target:
                cur += arr[j]
                j += 1
            # find target subarray
            if cur == target:
                # l = i, r = j - 1
                ll = j - i
                dp[j - 1] = min(dp[j - 1], ll)
                if i:
                    res = min(res, dp[i - 1] + ll)

        return res if res != MAX else - 1

    def minDistance(self, houses: List[int], K: int) -> int:
        houses.sort()
        n = len(houses)

        @cache
        def calc(i, j):
            if i >= j:
                return 0
            return houses[j] - houses[i] + calc(i + 1, j - 1)

        @cache
        def fn(j, k):
            if j + 1 <= k:
                return 0
            if k == 1:
                return calc(0, j)

            res = inf
            for i in range(0, j):
                res = min(res, fn(i, k - 1) + calc(i + 1, j))
            return res

        return fn(n - 1, K)


if __name__ == '__main__':
    s = Solution()
    # print(s.minSumOfLengths(arr=[3, 2, 2, 4, 3], target=3))
    # print(s.minSumOfLengths(arr=[7, 3, 4, 7], target=7))
    # print(s.minSumOfLengths(arr=[3, 1, 1, 1, 5, 1, 2, 1], target=3))
    # print(s.minSumOfLengths(arr=[5, 5, 4, 4, 5], target=3))
    # print(s.minSumOfLengths(arr=[3, 1, 1, 1, 5, 1, 2, 1], target=3))
    print(s.minDistance(houses=[1, 4, 8, 10, 20], K=3))
    print(s.minDistance(houses=[2, 3, 5, 12, 18], K=2))
    print(s.minDistance(houses=[7, 4, 6, 1], K=1))
    print(s.minDistance(houses=[3, 6, 14, 10], K=4))
    print(s.minDistance([15, 8, 9, 6], 1))
    print(s.minDistance(
        [97, 273, 319, 191, 435, 326, 223, 272, 224, 389, 299, 439, 333, 94, 410, 245, 180, 283, 480, 43, 477, 392, 500,
         483, 457, 238, 134, 311, 294, 444, 329, 376, 258, 54, 437, 471, 346, 125, 203, 403, 170, 497]
        , 25))
    print(s.minDistance([1, 4, 8, 10, 20]
                        , 3))
