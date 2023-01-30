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
    # gready and dp all can solve this problem
    def maxAlternatingSum(self, nums: List[int]) -> int:
        arr = []
        for c in nums:
            if arr and arr[-1] == c:
                continue
            arr.append(c)

        nums = arr

        n = len(nums)
        if n == 1:
            return nums[0]
        if n == 2:
            return max(nums)

        def check(i):
            if i == 0:
                return 1 if nums[i] > nums[i + 1] else 0
            if i == n - 1:
                return 1 if nums[i - 1] < nums[i] else 0
            if nums[i - 1] < nums[i] > nums[i + 1]:
                return 1
            if nums[i - 1] > nums[i] < nums[i + 1]:
                return -1
            return 0

        res = 0
        for i, c in enumerate(nums):
            f = check(i)
            if f == 1:
                res += c
            elif f == -1:
                res -= c
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxAlternatingSum(nums=[4, 2, 5, 3]))
    print(s.maxAlternatingSum([5, 6, 7, 8]))
    print(s.maxAlternatingSum(nums=[6, 2, 1, 2, 4, 5]))
    print(s.maxAlternatingSum(nums=[1, 1, 1, 1]))
