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
    def getSubarrayBeauty(self, nums: List[int], k: int, x: int) -> List[int]:
        tmp = SortedList(nums[:k])
        ans = [0 if tmp[x - 1] >= 0 else tmp[x - 1]]
        for i in range(k, len(nums)):
            tmp.pop(nums[i - k])
            tmp.add(nums[i])
            ans.append(0 if tmp[x - 1] >= 0 else tmp[x - 1])
        return ans

    def minOperations(self, nums: List[int]) -> int:

        tot = reduce(lambda x, y: gcd(x, y), nums)
        if tot != 1:
            return -1
        n = len(nums)
        if 1 in nums:
            return n - nums.count(1)
        res = inf
        for i in range(n):
            p = nums[i]
            for j in range(i+ 1, n):
                p = gcd(p, nums[j])
                if p == 1:
                    res = min(res,  n + j - i - 1)
        return res

if __name__ == '__main__':
    s = Solution()
    # print(s.getSubarrayBeauty(nums=[1, -1, -3, -2, 3], k=3, x=2))
    # print(s.minOperations(nums=[2, 10, 6, 14]))
    # print(s.minOperations(nums=[2, 6, 3, 4]
    #                       ))
    print(s.minOperations([6, 10, 15]))
    # print(s.minOperations([4, 2, 6, 3]))
    # print(s.minOperations([715086, 420033, 320095, 230175, 359910, 99010, 261428, 561978, 495675, 817898]))
    # print(gcd(gcd(715086, 420033),320095))
