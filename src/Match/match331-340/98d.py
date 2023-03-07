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
    def minImpossibleOR(self, nums: List[int]) -> int:
        ss = set(nums)
        nums = list(ss)
        nums.sort()
        if nums[0] > 1:
            return 1
        dp = [0] * 32
        for c in nums:
            for i in range(32):
                if c & (1 << i):
                    dp[i] += 1

        for i in range(32):
            if (1 << i) not in ss:
                return 1 << i
            elif dp[i] == 0:
                return 1 << i

    def handleQuery(self, nums1: List[int], nums2: List[int], queries: List[List[int]]) -> List[int]:
        s2 = sum(nums2)


if __name__ == '__main__':
    s = Solution()
    # print(s.minImpossibleOR(nums=[2, 1]))
    print(s.minImpossibleOR(
        [8388608, 131072, 128, 2097152, 65536, 2048, 438, 1048576, 8192, 32, 8, 64, 1024, 2244, 512, 262144, 4096,
         16384, 4, 256, 2, 4194304, 2203, 16, 32768, 410, 524288, 765, 1]))
    # 16777216
    print(s.minImpossibleOR([4, 32, 16, 8, 8, 75, 1, 2]))
    print(bin(75))