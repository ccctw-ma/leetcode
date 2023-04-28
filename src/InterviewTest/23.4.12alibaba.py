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

from functools import cache
n = int(input())
rb = input()
g = [[] for _ in range(n + 1)]
edges = []
for _ in range(n - 1):
    a, b = list(map(int, input().split(" ")))
    g[a].append(b)
    g[b].append(a)
    edges.append((a, b))
@cache
def f(x, p):
    r, b = 0, 0
    if rb[x - 1] == 'R':
        r += 1
    else:
        b += 1
    for y in g[x]:
        if y == p:
            continue
        else:
            tr, tb = f(y, x)
            r += tr
            b += tb
    return r, b
res = 0
for x, y in edges:
    r1, b1 = f(x, y)
    r2, b2 = f(y, x)
    if r1 > b1 and r2 > b2:
        res += 1
print(res)
