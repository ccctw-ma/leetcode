import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if grid[0][0] == 1:
            return -1
        q = deque([(0, 0)])
        vis = {(0, 0)}
        step = 1
        while q:
            for _ in range(len(q)):
                x, y = q.popleft()
                if x == n - 1 and y == n - 1:
                    return step
                for dx, dy in [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]:
                    nx, ny = x + dx, y + dy
                    if n > nx >= 0 <= ny < n and grid[nx][ny] == 0 and (nx, ny) not in vis:
                        vis.add((nx, ny))
                        q.append((nx, ny))

            step += 1
        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.shortestPathBinaryMatrix([[0, 1, 1, 1, 1, 1, 1, 1],
                                      [0, 1, 1, 0, 0, 0, 0, 0],
                                      [0, 1, 0, 1, 1, 1, 1, 0],
                                      [0, 1, 0, 1, 1, 1, 1, 0],
                                      [0, 1, 1, 0, 0, 1, 1, 0],
                                      [0, 1, 1, 1, 1, 0, 1, 0],
                                      [0, 0, 0, 0, 0, 1, 1, 0],
                                      [1, 1, 1, 1, 1, 1, 1, 0]]))
