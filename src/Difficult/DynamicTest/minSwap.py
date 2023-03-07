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
    def minSwap(self, nums1: List[int], nums2: List[int]) -> int:

        n = len(nums1)
        dp = [[n, n] for _ in range(n)]
        dp[0][0] = 0
        dp[0][1] = 1
        for i in range(1, n):
            a, b = nums1[i], nums2[i]
            pa, pb = nums1[i - 1], nums2[i - 1]
            if pa < a and pb < b:
                dp[i][0] = dp[i - 1][0]
                dp[i][1] = dp[i - 1][1] + 1
            if pa < b and pb < a:
                dp[i][0] = min(dp[i][0], dp[i - 1][1])
                dp[i][1] = min(dp[i][1], dp[i - 1][0] + 1)
        return min(dp[-1])


if __name__ == '__main__':
    s = Solution()
    print(s.minSwap(nums1=[1, 3, 5, 4], nums2=[1, 2, 3, 7]))
    print(s.minSwap(nums1=[0, 3, 5, 8, 9], nums2=[2, 1, 4, 6, 9]))
    print(s.minSwap([0, 4, 4, 5, 9],
                    [0, 1, 6, 8, 10]))
