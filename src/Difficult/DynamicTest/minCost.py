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
    def minCost(self, houses: List[int], cost: List[List[int]], m: int, n: int, target: int) -> int:
        MAX = 10 ** 9

        @cache
        def f(i, j, k):
            """
            位置i 颜色j 和其之前所有房子 构成k个街区的最小代价
            """
            #  边界条件
            if i + 1 < k or i < 0 or j < 0 or k == 0:
                return MAX
            # 这个位置已经涂过颜色了， 那么就不能涂别的颜色了
            if houses[i] != 0 and j != houses[i] - 1:
                return MAX
            # 初始条件
            curCost = cost[i][j] if j != houses[i] - 1 else 0
            if i == 0 and k == 1:
                return curCost
            res = f(i - 1, j, k)
            for jj in range(n):
                if j == jj:
                    continue
                res = min(res, f(i - 1, jj, k - 1))
            return res + curCost

        ans = min(f(m - 1, j, target) for j in range(n))
        return -1 if ans >= MAX else ans


if __name__ == '__main__':
    s = Solution()
    print(s.minCost(houses=[0, 0, 0, 0, 0], cost=[[1, 10], [10, 1], [10, 1], [1, 10], [5, 1]], m=5, n=2, target=3))
    print(s.minCost(houses=[0, 2, 1, 2, 0], cost=[[1, 10], [10, 1], [10, 1], [1, 10], [5, 1]], m=5, n=2, target=3))
    print(s.minCost(houses=[0, 0, 0, 0, 0], cost=[[1, 10], [10, 1], [1, 10], [10, 1], [1, 10]], m=5, n=2, target=5))
    print(s.minCost(houses=[3, 1, 2, 3], cost=[[1, 1, 1], [1, 1, 1], [1, 1, 1], [1, 1, 1]], m=4, n=3, target=3))
    print(s.minCost([2, 3, 0], [[5, 2, 3], [3, 4, 1], [1, 2, 1]], 3, 3, 3))
