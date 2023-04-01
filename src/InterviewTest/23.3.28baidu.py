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

# 战斗
# n, m, k = list(map(int, input().split(" ")))
# arr = list(map(int, input().split(" ")))
# arr.sort(reverse=True)
# t = k + 1
# ts = k * arr[0] + arr[1]
# res = (m // t) * ts + arr[0] * (m % t)
# print(res)

n = int(input())
arr = input()
g = [[] for _ in range(n)]
edges = []
for _ in range(n - 1):
    a, b = list(map(int, input().split(" ")))
    edges.append((a, b))
    g[a].append(b)
    g[b].append(a)


@cache
def f(x, p):

    for y in g[x]:
        if y == p:
            continue
        if arr[x - 1] == arr[y - 1]:
            pass


