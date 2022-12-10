from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def numSubarrayBoundedMax(self, nums: List[int], left: int, right: int) -> int:
        res = 0
        i = 0
        n = len(nums)
        while i < n:
            j = i
            while j < n and nums[j] > right:
                j += 1
            if j == n:
                break
            p = j
            pre = j - 1
            while j < n and nums[j] <= right:
                if left <= nums[j] <= right:
                    res += (j - p + 1)
                    pre = j
                else:
                    res += (pre - p + 1)
                j += 1
            i = j
        return res

    def numSubarrayBoundedMax2(self, nums: List[int], left: int, right: int) -> int:
        mn = tar = l = r = 0
        for x in nums:
            if x < left:
                mn += 1
                l += mn
            else:
                mn = 0
            if x <= right:
                tar += 1
                r += tar
            else:
                tar = 0
        return r - l


if __name__ == '__main__':
    s = Solution()
    print(s.numSubarrayBoundedMax(nums=[2, 9, 2, 5, 6], left=2, right=8))
    print(s.numSubarrayBoundedMax([16, 69, 88, 85, 79, 87, 37, 33, 39, 34]
                                  , 55
                                  , 57))
