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

# n = int(input())
# s = input()
# res = ''
# for c in s:
#     if c == 'a':
#         res += 'x'
#     elif c == 'b':
#         res += 'y'
#     elif c == 'c':
#         res += 'z'
#     else:
#         res += chr(ord(c) - 3)
# print(res)

# from math import ceil
# T = int(input())
# for _ in range(T):
#     n, k = list(map(int, input().split(" ")))
#     arr = list(map(int, input().split(" ")))
#     base = sorted(arr)
#     i, j = 0, 0
#     c = 0
#     while i < n and j < n:
#         if base[i] == arr[j]:
#             i += 1
#             j += 1
#         else:
#             while j < n and base[i] != arr[j]:
#                 j += 1
#                 c += 1
#
#     print(ceil(c / k))

N = int(input())
arr = list(map(int, input().split(" ")))
T = int(input())
L = list(map(int, input().split(" ")))
R = list(map(int, input().split(" ")))
O = input()
X = list(map(int, input().split(" ")))
for l, r, o, x in zip(L, R, O, X):
    for i in range(l- 1, r):
        if o == '|':
            arr[i] |= x
        elif o == '&':
            arr[i] &= x
        else:
            arr[i] = x
print(" ".join(map(lambda x:str(x), arr)))
