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

from math import ceil
from bisect import insort

# def round(x):
#     if x - int(x) < 0.5:
#         return int(x)
#     else:
#         return ceil(x)
#
# n = int(input())
# arr = list(map(int, input().split(" ")))
# s = 0
# avers = [""] * n
# mediums = [""] * n
# preSum = 0
# preArr = []
# for i, c in enumerate(arr):
#     preSum += c
#     insort(preArr, c)
#     avers[i] = str(round(preSum / (i + 1)))
#     t = len(preArr)
#     mid = preArr[t // 2] if t & 1 else round((preArr[(t - 1) // 2] + preArr[t // 2]) / 2)
#     mediums[i] = str(mid)
# print(" ".join(avers))
# print(" ".join(mediums))


n, m = list(map(int, input().split(" ")))
arr = list(map(int, input().split(" ")))
buc = Counter(arr)


def check(x):
    if x == 0:
        return True
    t = n
    for v in buc.values():
        t -= (v // x)
    return t <= 0


for i in range(100, -1, -1):
    if check(i):
        print(i)
        break

# 42 85
# 60 2 81 10 89 3 40 40 32 57 31 49 100 49 77 72 45 5 7 81 40 4 8 22 35 22 46 73 4 11 54 66 25 89 100 82 94 82 72 49 28 32 99 27 79 31 33 63 45 35 76 15 30 92 10 74 35 35 99 41 85 34 100 90 19 19 79 7 57 65 90 50 83 14 19 26 60 26 78 83 72 9 13 97 67
