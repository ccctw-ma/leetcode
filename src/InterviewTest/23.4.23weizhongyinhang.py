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

n = int(input())
a = list(map(int, input().split(" ")))
b = list(map(int, input().split(" ")))
la, ra, lb, rb = list(map(int, input().split(" ")))
preA = list(accumulate(a, initial=0))
preB = list(accumulate(b, initial=0))
print(preA, preB)
res = 0
for i in range(1, n + 1):
    curA, curB = preA[i], preB[i]
    l1, r1 = bisect_left(preA, curA - ra), bisect_right(preA, curA - la) - 1
    l2, r2 = bisect_left(preB, curB - rb), bisect_right(preB, curB - lb) - 1
    l = max(l1, l2)
    r = min(r1, r2)
    res += max(r - l + 1, 0)
    # print(r1, l1)
    # print(r2, l2)
    # print()
print(res)

# 4
# 1 4 2 3
# 2 4 1 1
# 3 7 4 6


from bisect import insort
n, m = list(map(int, input().split(" ")))
a = list(map(int, input().split(" ")))
b = list(map(int, input().split(" ")))
res = {}
arr = [(x, y) for x, y in zip(a, b)]
arr.sort()
h = []
s = 0

for x, y in arr:
    res[x] = s
    insort(h, y)
    s += y
    if len(h) > m:
        s -= h.pop(0)
ans = []
for x in a:
    ans.append(str(res[x]))
print(" ".join(ans))

# 5 3
# 1 3 5 2 4
# 1 2 3 4 5

#
n, k = list(map(int, input().split(" ")))
arr = []
pre = -1
for _ in range(n):
    a, b = list(map(int, input().split(" ")))
    if a == pre:
        arr[-1][1] += b
    else:
        arr.append([a, b])
        pre = a
res = defaultdict(int)
for i, (a, b) in enumerate(arr):
    c, m = divmod(b, k)
    cnt = 2 * c * k + m if m else (2 * c - 1) * k
    res[a] += cnt
print(len(res))
for k in sorted(res.keys()):
    print(f'{k} {res[k]}')

# 6 4
# 1 3
# 1 3
# 2 1
# 1 9
# 2 2
# 2 10
