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

# n = int(input())
# arr = list(map(int, input().split(" ")))
# q = int(input())
# buc = {x: i for i, x in enumerate(arr)}
# for _ in range(q):
#     x = int(input())
#     if x in buc:
#         print("YES")
#         idx = buc[x]
#         if idx * 2 + 1 < (2 ** n - 1):
#             print(f'{arr[idx * 2 + 1]} {arr[idx * 2 + 2]}')
#         else:
#             print("LEAF")
#     else:
#         print("NO")


mod = 10 ** 9 + 7
m, n = list(map(int, input().split(" ")))
g = []
for i in range(m):
    g.append(input())

dp = [[0] * n for _ in range(m)]
dp[0][0] = 1
for i in range(m):
    for j in range(n):
        if g[i][j] == '#':
            dp[i][j] = 0
        for x in range(i + 1, m):
            if g[x][j] == '#':
                break
            dp[x][j] += dp[i][j]
        for y in range(j + 1, n):
            if g[i][y] == '#':
                break
            dp[i][y] += dp[i][j]
print(dp[m - 1][n - 1] % mod)
