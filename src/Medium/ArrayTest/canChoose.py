import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def canChoose(self, groups: List[List[int]], nums: List[int]) -> bool:

        m, n = len(groups), len(nums)
        idx = 0
        i = 0
        while idx < n:

            if idx + len(groups[i]) <= n and nums[idx:idx + len(groups[i])] == groups[i]:
                idx = idx + len(groups[i])
                i += 1
            else:
                idx += 1
            if i == m:
                break
        return i == m


if __name__ == '__main__':
    s = Solution()
