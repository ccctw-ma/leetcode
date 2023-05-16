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
grid = []
for _ in range(m):
    grid.append([x for x in input()])


def fn(g):
    f = list(range(m * n))

    def find(x):
        while x != f[x]:
            f[x] = f[f[x]]
            x = f[x]
        return f[x]

    def union(x, y):
        px, py = find(x), find(y)
        if px != py:
            f[py] = px

    for i in range(m):
        for j in range(n):
            if i + 1 < m and g[i][j] == g[i + 1][j] == 'R':
                union(i * n + j, (i + 1) * n + j)
            if j + 1 < n and g[i][j] == g[i][j + 1] == 'R':
                union(i * n + j, i * n + j + 1)
    res = set()
    for i in range(m):
        for j in range(n):
            if g[i][j] == 'R':
                res.add(find(i * n + j))
    return len(res)


for i in range(m):
    tmp = []
    for j in range(n):
        pre = grid[i][j]
        grid[i][j] = 'W'
        tmp.append(str(fn(grid)))
        grid[i][j] = pre
    print(" ".join(tmp))


def fn2():
    n, k = map(int, input().split())
    nums = list(map(int, input().split()))
    dp = [-1] * k
    dp[0] = 0
    res = -1
    for i, c in enumerate(nums):
        m = c % k
        tar = (k - m) % k
        if [tar] != -1:
            res = max(res, c + dp[tar])
        tmp = dp[:]
        for i, e in enumerate(dp):
            if e != -1:
                next = (i + m) % k
                tmp[next] = max(tmp[next], dp[i] + c)
        dp = tmp
    print(res)

# 3 3
# WRW
# RRW
# RWR
