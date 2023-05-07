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
# dp = [1] * n
# for i in range(1, n):
#     if arr[i] != arr[i - 1]:
#         if i == 1:
#             dp[i] = 2
#         elif i == 2:
#             if arr[i] == arr[i - 2]:
#                 dp[i] = 3
#             else:
#                 dp[i] = 2
#         else:
#             if arr[i] == arr[i - 2]:
#                 if arr[i - 1] == arr[i - 3]:
#                     dp[i] = dp[i - 1] + 1
#                 else:
#                     dp[i] = 3
#             else:
#                 dp[i] = 2
#
# print(sum(dp))
# 5
# 1 2 1 3 1

from collections import defaultdict
n, m, k = list(map(int, input().split(" ")))
g = defaultdict(dict)
degrees = defaultdict(int)
edges = []
for _ in range(m):
    u, v, w = list(map(int, input().split(" ")))
    edges.append([u, v, w])
    g[u][v] = w
    g[v][u] = w
    degrees[u] += 1
    degrees[v] += 1
edges.sort(key=lambda x: -x[2])
k = min(k, m - (n - 1))
def fn():
    def tb(idx, t, pre):
        nonlocal ans
        if t == 0:
            ans = max(ans, pre)
            return
        for i in range(idx, n):
            u, v, w = edges[i]
            if degrees[u] > 1 and degrees[v] > 1:
                degrees[u] -= 1
                degrees[v] -= 1
                pre += w
                tb(i + 1, t - 1, pre)
                pre -= w
                degrees[v] += 1
                degrees[u] += 1
    ans = 0
    tb(0, k, 0)
    print(ans)
fn()

# 3 3 1
# 1 2 1
# 2 3 2
# 1 3 3
