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
    def maxResult(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [-(10 ** 11)] * n
        dp[0] = nums[0]
        stk = deque()
        for i in range(1, n):
            while stk and dp[i - 1] > dp[stk[-1]]:
                stk.pop()
            stk.append(i - 1)
            if i - k - 1 >= 0 and dp[stk[0]] == dp[i - k - 1]:
                stk.popleft()
            # print(nums[stk[0]])
            dp[i] = max(dp[i], dp[stk[0]] + nums[i])
        # print(dp)
        return dp[-1]


if __name__ == '__main__':
    s = Solution()
    print(s.maxResult(nums=[1, -5, -20, 4, -1, 3, -6, -3], k=2))
