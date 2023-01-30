import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:

        n = len(grid)
        q = deque([(0, 0, 0, 1, 0)])
        vis = set()
        step = 0

        def check(a, b, c, d):
            return (a, b, c, d) == (n - 1, n - 2, n - 1, n - 1)

        while q:
            nn = len(q)
            step += 1
            for i in range(nn):
                rx, ry, cx, cy, status = q.popleft()

                # 水平状
                if status == 0:
                    # 水平向右移动一格
                    if cy + 1 < n and grid[cx][cy + 1] == 0 and (rx, ry + 1, cx, cy + 1) not in vis:
                        if check(rx, ry + 1, cx, cy + 1):
                            return step
                        vis.add((rx, ry + 1, cx, cy + 1))
                        q.append((rx, ry + 1, cx, cy + 1, 0))

                    # 向下平移或者旋转
                    if rx + 1 < n and grid[rx + 1][ry] == 0 and grid[cx + 1][cy] == 0:
                        if (rx + 1, ry, cx + 1, cy) not in vis:
                            if check(rx + 1, ry, cx + 1, cy):
                                return step
                            vis.add((rx + 1, ry, cx + 1, cy))
                            q.append((rx + 1, ry, cx + 1, cy, 0))
                        if (rx, ry, rx + 1, ry) not in vis:
                            vis.add((rx, ry, rx + 1, ry))
                            q.append((rx, ry, rx + 1, ry, 1))

                # 垂直状
                else:
                    # 垂直向下移动一格
                    if cx + 1 < n and grid[cx + 1][cy] == 0:
                        if (rx + 1, ry, cx + 1, cy) not in vis:
                            vis.add((rx + 1, ry, cx + 1, cy))
                            q.append((rx + 1, ry, cx + 1, cy, 1))
                    # 向右平移或者旋转
                    if ry + 1 < n and grid[rx][ry + 1] == 0 and grid[cx][cy + 1] == 0:
                        if (rx, ry + 1, cx, cy + 1) not in vis:
                            vis.add((rx, ry + 1, cx, cy + 1))
                            q.append((rx, ry + 1, cx, cy + 1, 1))
                        if (rx, ry, rx, ry + 1) not in vis:
                            if check(rx, ry, rx, ry + 1):
                                return step
                            vis.add((rx, ry, rx, ry + 1))
                            q.append((rx, ry, rx, ry + 1, 0))

        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.minimumMoves(grid=[[0, 0, 0, 0, 0, 1],
                               [1, 1, 0, 0, 1, 0],
                               [0, 0, 0, 0, 1, 1],
                               [0, 0, 1, 0, 1, 0],
                               [0, 1, 1, 0, 0, 0],
                               [0, 1, 1, 0, 0, 0]]
                         ))
    print(s.minimumMoves([[0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0],
                          [0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0],
                          [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1],
                          [0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1],
                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0],
                          [1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1],
                          [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0],
                          [0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0],
                          [0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0],
                          [1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0],
                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0],
                          [0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]]))
