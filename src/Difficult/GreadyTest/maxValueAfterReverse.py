import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution2:
    def maxValueAfterReverse(self, nums: List[int]) -> int:
        base = sum(abs(a - b) for a, b in pairwise(nums))
        n = len(nums)
        res = 0
        for i in range(1, n):
            for j in range(i - 1, -1, -1):
                pre = (0 if i == n - 1 else abs(nums[i] - nums[i + 1])) + (0 if j == 0 else abs(nums[j - 1] - nums[j]))
                cur = (0 if i == n - 1 else abs(nums[j] - nums[i + 1])) + (0 if j == 0 else abs(nums[j - 1] - nums[i]))
                res = max(res, cur - pre)
        return base + res
# https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/solution/bu-hui-hua-jian-qing-kan-zhe-pythonjavac-c2s6/
class Solution:
    def maxValueAfterReverse(self, nums: List[int]) -> int:
        base = d = 0
        mx, mn = -inf, inf
        for a, b in pairwise(nums):
            base += abs(a - b)
            mx = max(mx, min(a, b))
            mn = min(mn, max(a, b))
            d = max(d, abs(nums[0] - b) - abs(a - b),  # i=0
                       abs(nums[-1] - a) - abs(a - b))  # j=n-1
        return base + max(d, 2 * (mx - mn))


if __name__ == '__main__':
    s = Solution()
    print(s.maxValueAfterReverse(nums=[2, 4, 9, 24, 2, 1, 10]))
    print(s.maxValueAfterReverse(nums=[1]))
    print(s.maxValueAfterReverse(nums=[2, 3, 1, 5, 4]))
