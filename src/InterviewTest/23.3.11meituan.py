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


# s = input()
# i = 0
# res = 0
# n = len(s)
# while i < n:
#     j = i + 1
#     while j < n and s[j] == s[j - 1]:
#         j += 1
#     l = j - i
#     res += (l // 2)
#     i = j
# print(res)

# n = int(input())
# starts = list(map(int, input().split(" ")))
# ends = list(map(int, input().split(" ")))
# arr = []
# for s, e in zip(starts, ends):
#     arr.append((s, 1))
#     arr.append((e, -1))
# arr.sort(key=lambda x: (x[0], -x[1]))
# maxC = 0
# cur, res = 0, 0
# l = 0
# # print(arr)
# pre = 0
# for t, d in arr:
#     cur += d
#     if cur > maxC:
#         maxC = cur
#         l = t
#         res = 0
#     elif cur == maxC - 1:
#         res += (t - l + 1)
#     elif cur == maxC:
#         l = t
# print(f'{maxC} {res}')


# n = int(input())
# starts = list(map(int, input().split(" ")))
# ends = list(map(int, input().split(" ")))

def f(n, starts, ends):
    d = defaultdict(int)
    for i in range(n):
        d[starts[i]] += 1
        d[ends[i] + 1] -= 1
    l = sorted(d.items())
    a = x = y = 0
    for i in range(len(l) - 1):
        a += l[i][1]
        if a == x:
            y += l[i + 1][0] - l[i][0]
        elif a > x:
            x, y = a, l[i + 1][0] - l[i][0]
    print(x, y)


f(3, [1, 2, 3], [4, 5, 6])
