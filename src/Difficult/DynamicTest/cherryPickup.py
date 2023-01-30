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
    def cherryPickup(self, grid: List[List[int]]) -> int:
        n = len(grid)
        k = 2 * n - 1
        MAX = 10 ** 9
        dp = [[[-MAX] * n for _ in range(n)] for _ in range(k)]
        dp[0][0][0] = grid[0][0]
        for k in range(1, 2 * n - 1):
            for x1 in range(max(0, k - n + 1), min(n, k + 1)):
                y1 = k - x1
                if grid[x1][y1] == -1:
                    continue
                for x2 in range(x1, min(n, k + 1)):
                    y2 = k - x2
                    if grid[x2][y2] == -1:
                        continue
                    res = dp[k - 1][x1][x2]
                    if x1:
                        res = max(res, dp[k - 1][x1 - 1][x2])
                    if x2:
                        res = max(res, dp[k - 1][x1][x2 - 1])
                    if x1 and x2:
                        res = max(res, dp[k - 1][x1 - 1][x2 - 1])
                    res += grid[x1][y1]
                    if x1 != x2:
                        res += grid[x2][y2]
                    dp[k][x1][x2] = res
        return max(0, dp[-1][-1][-1])


if __name__ == '__main__':
    s = Solution()
    print(s.cherryPickup(grid=
                         [[0, 1, -1],
                          [1, 0, -1],
                          [1, 1, 1]]))
