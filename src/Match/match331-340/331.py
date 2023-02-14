import math
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
    def pickGifts(self, gifts: List[int], k: int) -> int:
        h = []
        for g in gifts:
            heappush(h, -g)

        for _ in range(k):
            t = -heappop(h)
            if t == 1:
                continue
            heappush(h, -int(math.sqrt(t)))

        return -sum(h)

    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        arr = []
        vows = 'aeiou'
        for w in words:
            if w[0] in vows and w[-1] in vows:
                arr.append(1)
            else:
                arr.append(0)
        preSum = [0] + list(accumulate(arr))
        res = []
        for l, r in queries:
            res.append(preSum[r + 1] - preSum[l])

        return res

    def minCapability(self, nums: List[int], k: int) -> int:
        l, r = 10 ** 9 + 1, 0
        for num in nums:
            l = min(l, num)
            r = max(r, num)

        def check(x):
            arr = []

            for i, c in enumerate(nums):
                if c <= x:
                    arr.append(i)
            if len(arr) < k:
                return False
            n = len(arr)
            dp = [0] * (n + 1)
            for i in range(n):
                if i == 0:
                    dp[i + 1] = 1
                    continue
                if arr[i] - arr[i - 1] > 1:
                    dp[i + 1] = dp[i] + 1
                else:
                    dp[i + 1] = dp[i - 1] + 1
            # print(x, dp)
            return dp[-1] >= k

        while l < r:
            m = (l + r) // 2
            if check(m):
                r = m
            else:
                l = m + 1

        return l

    def minCost(self, basket1: List[int], basket2: List[int]) -> int:

        basket1.sort()
        basket2.sort()
        # print(basket1)
        # print(basket2)
        b1 = Counter(basket1)
        b2 = Counter(basket2)
        # print(b1)
        # print(b2)
        b = b1 + b2
        # print(b)
        arr = []
        minK = 10 ** 9 + 1
        for k, v in b.items():
            minK = min(k, minK)
            if v & 1:
                return -1
            # 不需要交换
            if b1[k] == b2[k]:
                continue
            else:
                arr += [k] * (abs(b1[k] - b2[k]) // 2)
        arr.sort()
        # print(arr)
        n = len(arr)
        if n & 1:
            return -1
        res = 0
        for i in range(n // 2):
            if arr[i] <= 2 * minK:
                res += arr[i]
            else:
                res += 2 * minK
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.minCapability(nums=[2, 3, 5, 9], k=2))
    # print(s.minCapability(nums=[2, 7, 9, 3, 1], k=2))
    # print(s.minCost(basket1=[2, 3, 4, 1], basket2=[3, 2, 5, 1]))
    # print(s.minCost(basket1=[4, 2, 2, 2], basket2=[1, 4, 1, 2]))
    print(s.minCost([84, 80, 43, 8, 80, 88, 43, 14, 100, 88],
                    [32, 32, 42, 68, 68, 100, 42, 84, 14, 8]))
