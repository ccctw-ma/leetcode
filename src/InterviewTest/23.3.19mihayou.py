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
    buc = Counter()
    remain = ''
    f = True
    for c in s:
        if c in 'mhy':
            buc[c] += 1
            if buc['h'] > buc['m'] or buc['y'] > buc['h'] or buc['y'] > buc['m']:
                f = False
                break
        else:
            remain += c
    if f:
        return remain
    else:
        return '#'


def ff(s, t):
    c1 = Counter(s)
    c2 = Counter(t)
    c = c2 - c1
    for k in c:
        if k not in 'mhy':
            return False
    return c['m'] == c['h'] == c['y']


def check(s, t):
    if not ff(s, t):
        return False
    s = f(s)
    if s == '#':
        return False
    diff = ''
    i = j = 0
    m, n = len(s), len(t)
    while i < m and j < n:
        if s[i] == t[j]:
            i += 1
            j += 1
        else:
            diff += t[j]
            j += 1
    if i != m:
        return False
    diff += t[j:]
    cnt = Counter(diff)
    a, b, c = cnt['m'], cnt['h'], cnt['y']
    if a != b or b != c or a != c or a + b + c != len(diff):
        return False
    buc = Counter()
    for c in diff:
        buc[c] += 1
        if buc['h'] > buc['m'] or buc['y'] > buc['h'] or buc['y'] > buc['m']:
            return False
    return True


q = int(input())
for _ in range(q):
    s, t = input(), input()
    m, n = len(s), len(t)
    if m > n:
        s, t = t, s
    if check(s, t):
        print('Yes')
    else:
        print('No')
