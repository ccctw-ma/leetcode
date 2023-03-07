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
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        q = deque([(0, 0, 0)])
        vis = {}
        res = 10 ** 9
        while q:
            for _ in range(len(q)):
                x, y, c = q.popleft()
                if x == m - 1 and y == n - 1:
                    res = min(res, c)
                for i, (a, b) in enumerate(dirs):
                    nx, ny = x + a, y + b
                    nc = c + (0 if grid[x][y] == (i + 1) else 1)
                    if 0 <= nx < m and 0 <= ny < n and ((nx, ny) not in vis or nc < vis[(nx, ny)]):
                        vis[(nx, ny)] = nc
                        q.append((nx, ny, nc))
        return res


    """
    0, 1 bfs 常规的bfs一般边与边之间的权重都是一， 队列里权重是递增的形式。 
    但是现在有0, 1的权重， 所有需要进行对应的处理才能让队列重新恢复递增的形式
    这里的处理方式是， 对于w = 0路径放到队首, w > 0的路径放到队尾， 
    这样就可以恢复队列递增的关系了。
    
    """
    def minCost2(self, grid: List[List[int]]) -> int:
        ds = ([0, 1, 1], [1, 0, 3], [0, -1, 2], [-1, 0, 4])
        m, n = len(grid), len(grid[0])
        q = deque([[0, 0, 0]])
        vis = [[m * n] * n for i in range(m)]
        while q:
            x, y, c = q.pop()
            if c > vis[x][y]: continue
            if x == m - 1 and y == n - 1: return c
            for dx, dy, d in ds:
                nx = x + dx
                ny = y + dy
                if 0 <= nx < m and 0 <= ny < n and (nc := c + (d != grid[x][y])) < vis[nx][ny]:
                    vis[nx][ny] = nc
                    if d == grid[x][y]:
                        q.append([nx, ny, nc])
                    else:
                        q.appendleft([nx, ny, nc])


if __name__ == '__main__':
    s = Solution()
    print(s.minCost(grid=[[1, 1, 1, 1], [2, 2, 2, 2], [1, 1, 1, 1], [2, 2, 2, 2]]))
    print(s.minCost(grid=[[1, 1, 3], [3, 2, 2], [1, 1, 4]]))
    print(s.minCost(grid=[[1, 2], [4, 3]]))
    print(s.minCost(grid=[[2, 2, 2], [2, 2, 2]]))
    print(s.minCost(grid=[[4]]))
