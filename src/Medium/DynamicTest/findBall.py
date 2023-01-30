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
    def findBall(self, grid: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])

        # status (1, 1)-> rt (1, -1)-> rb, (-1, 1)-> lt, (-1, -1)-> lb
        def dfs(i, j, status):
            if status == "rt" and j + 1 < n and grid[i][j + 1] == 1:
                return dfs(i, j + 1, "rb")
            if status == "lt" and j >= 1 and grid[i][j - 1] == -1:
                return dfs(i, j - 1, "lb")
            if status == "rb":
                if i + 1 == m:
                    return j
                elif grid[i + 1][j] == 1:
                    return dfs(i + 1, j, "rt")
                else:
                    return dfs(i + 1, j, "lt")
            if status == "lb":
                if i + 1 == m:
                    return j
                elif grid[i + 1][j] == -1:
                    return dfs(i + 1, j, "lt")
                else:
                    return dfs(i + 1, j, "rt")
            return -1

        return [dfs(0, j, "rt" if grid[0][j] == 1 else "lt") for j in range(n)]


if __name__ == '__main__':
    s = Solution()
    print(s.findBall(
        grid=[[1, 1, 1, -1, -1], [1, 1, 1, -1, -1], [-1, -1, -1, 1, 1], [1, 1, 1, 1, -1], [-1, -1, -1, -1, -1]]))
    print(s.findBall([[-1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1,
                       -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 1,
                       -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1,
                       -1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, -1, -1]]))
