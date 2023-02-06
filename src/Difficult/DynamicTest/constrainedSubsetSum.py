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
    def constrainedSubsetSum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [0] * n
        stk = []
        for i, c in enumerate(nums):
            preMax = max(0, stk[0]) if len(stk) else 0
            dp[i] = nums[i] + preMax
            while stk and stk[-1] < dp[i]:
                stk.pop()
            stk.append(dp[i])
            if i >= k:
                if stk and stk[0] == dp[i - k]:
                    stk.pop(0)

        return max(dp)


if __name__ == '__main__':
    s = Solution()
    # print([1, 2, 3].pop())

    print(s.constrainedSubsetSum(nums=[10, 2, -10, 5, 20], k=2))
    print(s.constrainedSubsetSum(nums=[-1, -2, -3], k=1))
    print(s.constrainedSubsetSum(nums=[10, -2, -10, -5, 20], k=2))
