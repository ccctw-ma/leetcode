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

# T = int(input())
# for _ in range(T):
#     n = int(input())
#     arr = list(map(int, input().split(" ")))
#     dp = [[0, 0] for _ in range(n)]
#     dp[0][0] = arr[0]
#     dp[0][1] = arr[0]
#     for i in range(1, n):
#         tmp = arr[i]
#         dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]) + tmp
#         dp[i][1] = dp[i - 1][0]
#     print(max(dp[-1]))

#
# n, k = list(map(int, input().split(" ")))
# arr = list(map(int, input().split(" ")))
#
# ps = [0] * n
# for i in range(n):
#     base = 1
#     for j in range(n):
#         if i == j:
#             continue
#         base *= (arr[i] / (arr[i] + arr[j]))
#     ps[i] = base
# res = sum(ps)
# if k == 0:
#     print(round(1 - res, 2))
# elif k == 1:
#     print(round(res, 2))
# else:
#     print(0)

# 5 1
# 2 3 4 5 1

A, B, C = list(map(int, input().split(" ")))
def fn(m):
    a = m // A
    b = m - a
    c = a * B + b
    return c >= C

l, r = 1, 10 ** 9
while l < r:
    m = (l + r - 1) // 2
    if fn(m):
        r = m
    else:
        l = m + 1

print(l)
