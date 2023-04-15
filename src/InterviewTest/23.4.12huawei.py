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

# arr = list(map(int, input().split(" ")))
# cnt = int(input())
# if sum(arr) <= cnt:
#     print(-1)
# else:
#     l, r = 0, 10 ** 9
#     while l < r:
#         m = (l + r + 1) // 2
#         tmp = 0
#         for c in arr:
#             tmp += min(m, c)
#         if tmp > cnt:
#             r = m - 1
#         else:
#             l = m
#     print(l)

from functools import lru_cache

n = int(input())
weights = [0] * n
g = [[] for _ in range(n)]
for _ in range(n):
    id, p, v = list(map(int, input().split(" ")))
    weights[id] = v
    if p != -1:
        g[p].append(id)
def f(x, p):
    ans = weights[x]
    base = ans
    for y in g[x]:
        if y == p:
            continue
        tmp = f(y, x)
        ans = max(ans, base + tmp)
    return ans


res = -100
for i in range(n):
    res = max(res, f(i, - 1))
print(res)

# 7
# 0 1 8
# 1 -1 -2
# 2 1 9
# 4 0 -2
# 5 4 3
# 3 0 -3
# 6 2 -3