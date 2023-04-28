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

from collections import defaultdict
from functools import cache

n = int(input())
g = defaultdict(dict)
edges = []
for _ in range(n - 1):
    u, v, w = list(map(int, input().split(" ")))
    g[u][v] = w
    g[v][u] = w
    edges.append((u, v))


@cache
def dfs(x, p, f):
    neibors = g[x].keys()
    arr = []
    for y in neibors:
        if y == p:
            continue
        arr.append((dfs(y, x, True), g[x][y] + dfs(y, x, False)))
    # f为true表示可以染成红色， false表示不可以
    if not arr:
        return 0
    base = sum(x for x, _ in arr)
    if f:
        return max(base + arr[i][1] - arr[i][0] for i in range(len(arr)))
    else:
        return base


res = 0
for u, v in edges:
    a = g[u][v] + dfs(u, v, False) + dfs(v, u, False)
    b = dfs(u, v, True) + dfs(v, u, True)
    res = max(res, a, b)
print(res)
# 5
# 1 2 2
# 2 3 5
# 3 4 4
# 3 5 3

#
# n = int(input())
#
# arr = [0]
# pre = 1
# for x in range(1, 20):
#     pre *= x
#     if pre <= n:
#         arr.append(pre)
#     else:
#         break
# x, y, minD = 1, 1, n
# for i in range(3, len(arr)):
#     a = arr[i] - 1
#     b = n // a
#     tmp = []
#     for tb in range(max(b - 1, 1), b + 5):
#         if tb == 2:
#             continue
#         tmp.append((abs(a * tb - n), i, tb))
#     tmp.sort()
#     t, tx, ty = tmp[0]
#     if t < minD:
#         x, y, minD = tx, ty, t
# print(x, y)
