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
    def minOperations(self, nums: List[int], x: int) -> int:
        n = len(nums)
        res = -1
        sum_nums = sum(nums)
        target = sum_nums - x
        if target < 0:
            return -1
        if target == 0:
            return n

        left = 0
        subsum = 0
        for right in range(n):
            subsum += nums[right]
            while left <= right and subsum > target:
                subsum -= nums[left]
                left += 1
            if subsum == target:
                res = max(res, right - left + 1)
        return n - res if res >= 0 else -1


if __name__ == '__main__':
    s = Solution()
    print(s.minOperations(nums=[1, 1, 4, 2, 3], x=5))
    print(s.minOperations(nums=[3, 2, 20, 1, 1, 3], x=10))
