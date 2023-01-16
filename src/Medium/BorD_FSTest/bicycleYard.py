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
    def bicycleYard(self, position: List[int], terrain: List[List[int]], obstacle: List[List[int]]) -> List[List[int]]:
        m, n = len(terrain), len(terrain[0])
        speedDiff = [[[0] * 4 for _ in range(n)] for _ in range(m)]
        directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        for x in range(m):
            for y in range(n):
                for i, (dx, dy) in enumerate(directions):
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < m and 0 <= ny < n:
                        speedDiff[x][y][i] = (terrain[x][y] - terrain[nx][ny] - obstacle[nx][ny])
        # print(speedDiff)

        vis = [[set() for _ in range(n)] for _ in range(m)]

        res = set()

        def dfs(x, y, v):
            # 越界 或者是速度不大于0
            if x < 0 or x >= m or y < 0 or y >= n or v <= 0:
                return
            if v in vis[x][y]:
                return
            vis[x][y].add(v)

            if v == 1 and [x, y] != position:
                res.add((x, y))
            for i, (dx, dy) in enumerate(directions):
                nx, ny, nv = x + dx, y + dy, v + speedDiff[x][y][i]
                dfs(nx, ny, nv)

        dfs(position[0], position[1], 1)
        # print(res)
        return sorted(list(res))


if __name__ == '__main__':
    s = Solution()
    print(s.bicycleYard(position=[0, 0], terrain=[[0, 0], [0, 0]], obstacle=[[0, 0], [0, 0]]))
    print(s.bicycleYard(position=[1, 1], terrain=[[5, 0], [0, 6]], obstacle=[[0, 6], [7, 0]]))
