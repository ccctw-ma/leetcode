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
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        n, m = len(nums), len(multipliers)

        @cache
        def f(l, r, idx):
            if idx == m:
                return 0
            left = nums[l] * multipliers[idx]
            right = nums[r] * multipliers[idx]
            return max(left + f(l + 1, r, idx + 1), right + f(l, r - 1, idx + 1))

        return f(0, n - 1, 0)


if __name__ == '__main__':
    s = Solution()
    print(s.maximumScore(nums=[1, 2, 3], multipliers=[3, 2, 1]))
    print(s.maximumScore(nums=[-5, -3, -3, -2, 7, 1], multipliers=[-10, -5, 3, 4, 6]))
