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



m, n = list(map(int, input().split(" ")))
arr = [[0] * n for _ in range(m)]
buc = defaultdict(list)
for i in range(m):
    for j, c in enumerate(list(map(int, input().split(" ")))):
        arr[i][j] = c
        buc[c].append((i, j))

vis = defaultdict(int)
vis[(0, 0)] = 0
q = deque([(0, 0, 0, False)])
res = inf
while q:
    x, y, pc, f = q.popleft()
    print(x, y, pc, f)
    if x == m - 1 and y == n - 1:
        res = min(res, pc)
        continue
    # 上下左右运动
    for dx, dy in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
        nx, ny = x + dx, y + dy
        if 0 <= nx < m and 0 <= ny < n:
            c = abs(arr[x][y] - arr[nx][ny])
            if ((nx, ny) not in vis) or (pc + c) < vis[(nx, ny)]:
                vis[(nx, ny)] = pc + c
                q.append((nx, ny, pc + c, False))
    # 跳跃运动
    if not f:
        for nx, ny in buc[arr[x][y]]:
            if x != nx and y != ny:
                if ((nx, ny) not in vis) or pc < vis[(nx, ny)]:
                    vis[(nx, ny)] = pc
                    q.append((nx, ny, pc, True))

print(res)


# n, x = list(map(int, input().split(" ")))
# buc = defaultdict(list)
# for i in range(n):
#     m = int(input())
#     prices = list(map(int, input().split(" ")))
#     vs = list(map(int, input().split(" ")))
#     for p, v in zip(prices, vs):
#         buc[i].append((p, v))
# tmp = defaultdict(lambda :-1)
# for p, v in buc[0]:
#     tmp[p]= max(tmp[p], v)
# print(tmp.items())
# for i in range(1, n):
#     t = defaultdict(lambda : -1)
#     for p, v in buc[i]:
#         print(p, v)
#         for k in tmp:
#             t[k + p] = max(t[k + p], tmp[k] + v)
#     tmp = t
#     print(tmp.items())
# res = -1
# for k, v in tmp.items():
#     if k <= x:
#         res = max(res, v)
# print(res)



