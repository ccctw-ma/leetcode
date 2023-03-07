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
#
# res = 1
# i = 0
# while i < n:
#     j = i + 1
#     while j < n and abs(arr[j] - arr[j - 1]) <= 1:
#         j += 1
#     res = max(res, j - i)
#     i = j
# print(res)


# n, q = list(map(int, input().split(" ")))
# s = input()
# for _ in range(q):
#     l, r = list(map(int, input().split(" ")))
#     tmp = s[l - 1: r]
#     ll = s[:l - 1]
#     rr = s[r:]
#     tt = ''
#     for c in tmp:
#         tt += (c + c)
#     s = ll + tt + rr
# print(s)

# list(map(int, input().split(" ")))

# v, x, y = list(map(int, input().split(" ")))
# if x * y <= v:
#     print(y / v)
# else:
#     t = (sqrt(x * y) - v) / x
#     res = t + y / (t * x + v)
#     print(res)


n, x = list(map(int, input().split(" ")))
prices = list(map(int, input().split(" ")))
loves = list(map(int, input().split(" ")))

# print(n, x)
# print(prices)
# print(loves)

# @cache
# def f(i, remain):
#     if i == n:
#         return 0
#     if prices[i] > remain:
#         return f(i + 1, remain)
#     # 不买
#     a = f(i + 1, remain)
#     # 买， 但右边那个我不半折买
#     b = f(i + 1, remain - prices[i]) + loves[i]
#     # 买，同时半价买右边那个
#     c = 0
#     if i < n - 1:
#         cost = prices[i] + prices[i + 1] // 2
#         if remain >= cost:
#             c = f(i + 2, remain - (prices[i] + prices[i + 1] // 2)) + loves[i] + loves[i + 1]
#     return max(a, b, c)
#
#
# print(f(0, x))
res = 0
dp = [[0] * (x + 1) for _ in range(n + 1)]

for i in range(n - 1, -1, -1):
    for j in range(x, -1, -1):
        # 不买
        a = dp[i + 1][j]
        # 买, 但是不买右边那个
        if j < prices[i]:
            dp[i][j] = a
            continue
        b = dp[i + 1][j - prices[i]] + loves[i] if j >= prices[i] else 0
        c = 0
        if i < n - 1:
            cost = prices[i] + (prices[i + 1] // 2)
            if j >= cost:
                c = dp[i + 2][j - cost] + loves[i] + loves[i + 1]
        dp[i][j] = max(a, b, c)
        res = max(res, dp[i][j])
print(res)
