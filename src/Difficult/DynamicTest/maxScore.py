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
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)
        MAX = 1 << n - 1

        @cache
        def GCD(a, b):
            if a < b:
                return GCD(b, a)
            elif a % b:
                return GCD(b, a % b)
            return b

        @cache
        def fn(index, status):
            if status == MAX:
                return 0
            res = 0
            for i in range(n):
                if status & 1 << i == 0:
                    x = nums[i]
                    for j in range(i + 1, n):
                        if status & 1 << j == 0:
                            y = nums[j]
                            res = max(res, index * GCD(x, y) + fn(index + 1, (status | 1 << i | 1 << j)))
            return res

        return fn(1, 0)


if __name__ == '__main__':
    s = Solution()
    print(s.maxScore(nums=[3, 4, 6, 8]))
    print(s.maxScore(nums=[1, 2, 3, 4, 5, 6]))
