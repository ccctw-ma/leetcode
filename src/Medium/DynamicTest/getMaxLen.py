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
    def getMaxLen(self, nums: List[int]) -> int:

        def f(arr: List[int]) -> int:
            res = 0
            flag = True
            for i, c in enumerate(arr):
                if c < 0:
                    flag = not flag
                if flag:
                    res = max(res, i + 1)
            n = len(arr)
            flag = True
            for i in range(n - 1, -1, -1):
                if arr[i] < 0:
                    flag = not flag
                if flag:
                    res = max(res, n - i)
            return res

        res = 0
        i, n = 0, len(nums)
        while i < n:
            j = i
            while j < n and nums[j] != 0:
                j += 1
            res = max(res, f(nums[i: j]))
            while j < n and nums[j] == 0:
                j += 1
            i = j
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.getMaxLen(nums=[1, -2, -3, 4]))
