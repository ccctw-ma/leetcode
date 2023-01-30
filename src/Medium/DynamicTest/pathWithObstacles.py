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
    def pathWithObstacles(self, obstacleGrid: List[List[int]]) -> List[List[int]]:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        flag = False
        if obstacleGrid[0][0]:
            return []

        res = []
        vis = set()
        vis.add((0, 0))

        def tb(x, y, path):
            nonlocal flag, res
            if flag:
                return
            if x == m - 1 and y == n - 1:
                res = path[:] + [x * n + y]
                flag = True
                return

            if x + 1 < m and obstacleGrid[x + 1][y] != 1 and (x + 1, y) not in vis:
                tb(x + 1, y, path[:] + [x * n + y])
                vis.add((x + 1, y))
            if y + 1 < n and obstacleGrid[x][y + 1] != 1 and (x, y + 1) not in vis:
                vis.add((x, y + 1))
                tb(x, y + 1, path[:] + [x * n + y])

        tb(0, 0, [])
        ans = []
        for e in res:
            ans.append([e // n, e % n])
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.pathWithObstacles([
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0]
    ]))
    # print([1] + [2])
