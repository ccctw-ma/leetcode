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
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        tmp = sorted(zip(nums, cost))
        # print(tmp)
        sc = sum(cost)
        target = sc // 2
        base = 0
        chose = 0
        for num, cos in tmp:
            base += cos
            if base > target:
                chose = num
                break
        res = 0
        for num, cos in tmp:
            res += abs(num - chose) * cos
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.minCost(nums=[1, 3, 5, 2], cost=[2, 3, 1, 14]))
    print(s.minCost([7, 4]
                    , [7, 6]))
