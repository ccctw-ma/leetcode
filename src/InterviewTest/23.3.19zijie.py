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


def dis(a, b, c, d):
    return abs(a - c) + abs(b - d)


def findNext(x, y, nodes, vis):
    arr = []
    for nx, ny in nodes:
        if (nx, ny) not in vis:
            arr.append((dis(x, y, nx, ny), nx, ny))
    arr.sort()
    return arr[0][1], arr[0][2]


def goTo(x, y, nx, ny):
    # 先横再纵
    if x >= nx:
        while x != nx:
            x -= 1
            print(x, y)
        while y != ny:
            y += 1 if y < ny else -1
            print(x, y)
    else:
        while y != ny:
            y += 1 if y < ny else -1
            print(x, y)
        while x != nx:
            x += 1
            print(x, y)


T = int(input())
for _ in range(T):
    vis = set()
    n, m, sx, sy = list(map(int, input().split(" ")))
    nodes = [[1, 1], [n, m], [1, m], [n, 1]]
    cx, cy = sx, sy
    arr = [(cx, cy)]
    cnt = 0
    for i in range(4):
        nextNode = findNext(cx, cy, nodes, vis)
        vis.add(nextNode)
        arr.append(nextNode)
        cnt += dis(cx, cy, nextNode[0], nextNode[1])
        # print(nextNode)
        cx, cy = nextNode
    print(cnt)
    print(sx, sy)
    for i in range(4):
        goTo(arr[i][0], arr[i][1], arr[i + 1][0], arr[i + 1][1])

# n = int(input())
# s = input()
# arr = []
# i = 0
# while i < n:
#     j = i + 1
#     while j < n and s[j] == s[i]:
#         j += 1
#     arr.append((s[i], j - i))
#     i = j
# res = 0
# n = len(arr)
# for i in range(n):
#     res = max(res, arr[i][1])
#     if arr[i][0] == 'A' and i + 1 < n and arr[i + 1][0] == 'B':
#         res = max(res, arr[i][1] + arr[i + 1][1])
#     if arr[i][0] == 'B':
#         if i < n - 1 and arr[i + 1][0] == 'A':
#             l = 0 if i == 0 else arr[i - 1][1]
#             r = 0 if i == n - 2 else arr[i + 2][1]
#             res = max(res, l + arr[i][1] + arr[i + 1][1] + r)
# print(res)
#
# from collections import  Counter
# n, m = list(map(int, input().split(" ")))
# a = list(map(int, input().split(" ")))
# b = list(map(int, input().split(" ")))
# def f(count, arr):
#     cnt = Counter([0])
#     for i in range(count):
#         tmp = Counter()
#         for j in range(1, arr[i] + 1):
#             for k in cnt:
#                 tmp[k + j] += cnt[k]
#         cnt = tmp
#     return cnt
# cnt1 = f(n, a)
# cnt2 = f(m, b)
# win = 0
# for k1 in cnt1:
#     for k2 in cnt2:
#         if k1 > k2:
#             win += cnt1[k1] * cnt2[k2]
# tot = sum(cnt1.values()) * sum(cnt2.values())
# print(round(win / tot, 3))
