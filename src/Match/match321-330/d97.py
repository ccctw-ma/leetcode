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
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        res = 0
        buc = set(banned)
        s = 0
        for i in range(1, n + 1):
            if i in buc:
                continue
            s += i
            if s > maxSum:
                return res
            res += 1

    def maximizeWin(self, prizePositions: List[int], k: int) -> int:
        buc = defaultdict(int)
        for p in prizePositions:
            buc[p] += 1
        arr = []
        for key in sorted(buc.keys()):
            arr.append((key, buc[key]))

        res = 0
        j = 0
        curS = 0
        n = len(arr)
        dp = [0] * (n + 1)
        for i, (p, v) in enumerate(arr):
            curS += v
            while (p - arr[j][0]) > k:
                curS -= arr[j][1]
                j += 1
            res = max(res, dp[j] + curS)
            dp[i + 1] = max(curS, dp[i])
        return res

    def isPossibleToCutPath(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])

        @cache
        def f(x, y):
            if x < 0 or y < 0:
                # 不使用翻转的情况下就不能抵达
                return False, 0
            if grid[x][y] == 0:
                return False, 0
            if x == 0 and y == 0:
                return True, 0

            ta, tb = f(x - 1, y)
            la, lb = f(x, y - 1)
            if x == m - 1 and y == n - 1:
                return (ta or la) and (tb + lb) < 2, False
            if (ta or la) and (tb + lb) == 0:
                return False, 1

            return False, 0

        return not f(m - 1, n - 1)[0]


if __name__ == '__main__':
    s = Solution()
    # print(s.maxCount(banned=[1, 6, 5], n=5, maxSum=6))
    # print(s.maximizeWin(prizePositions=[1, 1, 2, 2, 3, 3, 5], k=2))
    # print(s.maximizeWin(prizePositions=[1, 2, 3, 4], k=0))
    print(s.isPossibleToCutPath(grid=[[1, 1, 1], [1, 0, 0], [1, 1, 1]]
                                ))
    print(s.isPossibleToCutPath(grid=[[1, 1, 1], [1, 0, 1], [1, 1, 1]]))
