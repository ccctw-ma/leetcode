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

N, a, b = list(map(int, input().split(" ")))
gird = [[0] * 1000 for _ in range(1000)]
m = n = 1000
for _ in range(N):
    x, y = list(map(int, input().split(" ")))
    gird[x - 1][y - 1] = 1

## 二维前缀和

preSums = [[0] * (n + 1) for _ in range(m + 1)]
for i in range(1, m + 1):
    for j in range(1, n + 1):
        preSums[i][j] = preSums[i - 1][j] + preSums[i][j - 1] - preSums[i - 1][j - 1] + gird[i - 1][j - 1]

res = 0
for i in range(1, m + 1):
    for j in range(1, n + 1):
        x = max(0, i - a - 1)
        y = max(0, j - b - 1)
        tmp = preSums[i][j] - preSums[x][j] - preSums[i][y] + preSums[x][y]
        res = max(res, tmp)
print(res)
