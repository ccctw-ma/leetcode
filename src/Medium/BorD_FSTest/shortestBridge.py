from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def shortestBridge(self, grid: List[List[int]]) -> int:
        n = len(grid)

        vis = [[0] * n for _ in range(n)]
        directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        s = []

        def dfs(x, y):
            if 0 <= x < n and 0 <= y < n and grid[x][y] and vis[x][y] == 0:
                vis[x][y] = 1
                s.append((x, y))
                for a, b in directions:
                    nx = x + a
                    ny = y + b
                    dfs(nx, ny)
        flag = True
        for i in range(n):
            if flag:

                for j in range(n):
                    if grid[i][j]:
                        dfs(i, j)
                        flag = False
                        break
        res = 0
        while s:
            temp = []
            for x, y in s:
                for a, b in directions:
                    nx = x + a
                    ny = y + b
                    if 0 <= nx < n and 0 <= ny < n:
                        if grid[nx][ny] and vis[nx][ny] == 0:
                            return res
                        elif grid[nx][ny] == 0:
                            temp.append((nx, ny))
                            grid[nx][ny] = 1
                            vis[nx][ny] = 1
            s = temp
            res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.shortestBridge(grid=[[1, 1, 1, 1, 1], [1, 0, 0, 0, 1], [1, 0, 1, 0, 1], [1, 0, 0, 0, 1], [1, 1, 1, 1, 1]]))
    print(s.shortestBridge(grid=[[0, 1, 0], [0, 0, 0], [0, 0, 1]]))
