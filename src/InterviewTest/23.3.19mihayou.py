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


# m, n = list(map(int, input().split(" ")))
# arr = [[0] * n for _ in range(m)]
# base = 0
# for j in range(n):
#     for i in range(m):
#         arr[i][j] = base
#         base = (base + 1) % 10
# for i in range(m):
#     print(' '.join([str(x) for x in arr[i]]))

# m, n = list(map(int, input().split(" ")))
# g = [[0] * n for _ in range(m)]
# for i in range(m):
#     for j, c in enumerate(input()):
#         g[i][j] = c
#
#
# class DSU:
#     def __init__(self, dataList):
#         self.parent = {}
#         self.size = {}
#         self.setsCount = len(dataList)
#
#         for x in dataList:
#             self.parent[x] = x
#             self.size[x] = 1
#
#     def isExit(self, x):
#         return x in self.parent
#
#     def add(self, x):
#         self.parent[x] = x
#         self.size[x] = 1
#         self.setsCount += 1
#
#     def find(self, x):
#         while x != self.parent[x]:
#             self.parent[x] = self.parent[self.parent[x]]
#             x = self.parent[x]
#         return x
#
#     def union(self, x, y):
#         px = self.find(x)
#         py = self.find(y)
#         if px != py:
#             sx = self.size[px]
#             sy = self.size[py]
#             if sx < sy:
#                 px, py = py, px
#             self.parent[py] = px
#             self.size[px] += self.size[py]
#             self.setsCount -= 1
#
#     def isConnected(self, x, y):
#         return self.find(x) == self.find(y)
#
#
# def find(ff, x):
#     while x != ff[x]:
#         ff[x] = ff[ff[x]]
#         x = ff[x]
#     return ff[x]
#
#
# def union(ff, x, y):
#     px, py = find(ff, x), find(ff, y)
#     if px != py:
#         ff[py] = px
#
#
# # f = list(range(m * n))
# # cnt1 = m * n
# dsu1 = DSU(list(range(m * n)))
# for i in range(m):
#     for j in range(n):
#         if i < m - 1 and g[i + 1][j] == g[i][j]:
#             dsu1.union(i * n + j, (i + 1) * n + j)
#         if j < n - 1 and g[i][j] == g[i][j + 1]:
#             dsu1.union(i * n + j, i * n + j + 1)
#
#
# # cnt1 = len(set([find(f, x) for x in range(m * n)]))
# # print(cnt1)
# # print(dsu1.setsCount)
# # print(cnt1)
# def check(a, b):
#     if a == b:
#         return True
#     if a == 'G' and b == 'B':
#         return True
#     return a == 'B' and b == 'G'
#
#
# # f2 = list(range(m * n))
# # cnt2 = m * n
#
# dsu2 = DSU(list(range(m * n)))
# for i in range(m):
#     for j in range(n):
#         if i < m - 1 and check(g[i + 1][j], g[i][j]):
#             # union(f2, i * n + j, (i + 1) * n + j)
#             # cnt2 -= 1
#             dsu2.union(i * n + j, (i + 1) * n + j)
#         if j < n - 1 and check(g[i][j], g[i][j + 1]):
#             dsu2.union(i * n + j, i * n + j + 1)
#             # union(f2, i * n + j, i * n + j + 1)
#             # cnt2 -= 1
# # print(cnt2)
# # cnt2 = len(set([find(f2, x) for x in range(m * n)]))
# print(dsu1.setsCount - dsu2.setsCount)



def f(s):
    a, b = '', ''
    for c in s:
        if c in 'mhy':
            a += c
        else:
            b += c
    return a, b

q = int(input())
for _ in range(q):
    s, t = input(), input()
    m, n = len(s), len(t)
    a1, b1 = f(s)
    a2, b2 = f(t)
    if b1 != b2:
        print("No")
    buc = Counter()
    for c in a1:
        buc[c] += 1
    for c in a2:
        buc[c] -= 1
    if buc['m'] == buc['h'] == buc['y']:
        print('Yes')
    else:
        print('No')