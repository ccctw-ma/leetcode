from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        pre_sum = [0] + list(accumulate(nums))
        n = len(nums)
        stk, rmin = [], [n] * n
        for i, e in enumerate(nums):
            while stk and e < nums[stk[-1]]:
                rmin[stk[-1]] = i
                stk.pop()
            stk.append(i)
        stk, lmin = [], [-1] * n
        for i in range(n - 1, -1, -1):
            e = nums[i]
            while stk and e < nums[stk[-1]]:
                lmin[stk[-1]] = i
                stk.pop()
            stk.append(i)
        res = 0
        for i, e in enumerate(nums):
            res = max(res, e * (pre_sum[rmin[i]] - pre_sum[lmin[i] + 1]))
        return res % (10 ** 9 + 7)


if __name__ == '__main__':
    s = Solution()
    print(s.maxSumMinProduct(nums=[1, 2, 3, 2]))
    print(s.maxSumMinProduct([1, 1, 3, 2, 2, 2, 1, 5, 1, 5]))
    print(s.maxSumMinProduct([2, 5, 4, 2, 4, 5, 3, 1, 2, 4]))
