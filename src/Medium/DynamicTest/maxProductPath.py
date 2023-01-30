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
    def maxProductPath(self, grid: List[List[int]]) -> int:
        if grid[0][0] == 0:
            return 0

        mod = 10 ** 9 + 7

        m, n = len(grid), len(grid[0])
        dp = [[[0, 0] for _ in range(n)] for _ in range(m)]
        dp[0][0][0] = grid[0][0]
        dp[0][0][1] = grid[0][0]

        for i in range(1, m):
            grid[i][0] *= grid[i - 1][0]
            dp[i][0][0] = grid[i][0]
            dp[i][0][1] = grid[i][0]
        for j in range(1, n):
            grid[0][j] *= grid[0][j - 1]
            dp[0][j][0] = grid[0][j]
            dp[0][j][1] = grid[0][j]

        for i in range(1, m):
            for j in range(1, n):
                c = grid[i][j]
                la, lb = dp[i][j - 1]
                ta, tb = dp[i - 1][j]
                arr = [c * la, c * lb, c * ta, c * tb]
                dp[i][j][0] = min(arr)
                dp[i][j][1] = max(arr)
        a, b = dp[m - 1][n - 1]
        return -1 if b < 0 else b % mod


if __name__ == '__main__':
    s = Solution()
    print(s.maxProductPath(grid=[[-1, -2, -3],
                                 [-2, -3, -3],
                                 [-3, -3, -2]]))
    print(s.maxProductPath(grid=[[1, -2, 1],
                                 [1, -2, 1],
                                 [3, -4, 1]]))
    print(s.maxProductPath(grid=[[1, 3],
                                 [0, -4]]))
    print(s.maxProductPath(grid=[[1, 4, 4, 0],
                                 [-2, 0, 0, 1],
                                 [1, -1, 1, 1]]))
    print(s.maxProductPath([[3], [-1], [3]]))
    print(s.maxProductPath([[3]]))
