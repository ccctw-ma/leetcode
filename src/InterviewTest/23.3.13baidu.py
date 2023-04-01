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
# arr = []
# for _ in range(n):
#     arr.append(input())
# baidu = "Baidu"
# for s in arr:
#     cnt = Counter(s)
#     flag = all(cnt[c] > 0 for c in baidu) and len(s) == 5
#     print("Yes" if flag else "False")


# t = int(input())
# for _ in range(t):
#     n, k = list(map(int, input().split(" ")))
#     arr = list(map(int, input().split(" ")))
#     cnt = Counter(arr)
#     kvs = sorted(cnt.items())
#     # print(kvs)
#     dict = {}
#     s = 0
#     res = "-1"
#     for key, v in kvs:
#         dict[s] = key
#         s += v
#         if (s - k) in dict:
#             res = f'{dict[s - k]} {key}'
#             break
#     print(res)


# x = int(input())
# def f(x):
#     n = int(sqrt(2 * x + 0.25) - 0.5)
#     c = (n * (n + 1)) // 2
#     return n, x - c
#
#
# s = 'red'
# i = 0
# res = ""
# while x != 0:
#     n, x = f(x)
#     res += (s[i] * n)
#     i = (i + 1) % 3
# print(res)


n, m = list(map(int, input().split(" ")))
arr1 = list(map(int, input().split(" ")))
arr2 = list(map(int, input().split(" ")))
buc = Counter()
pre = - 10 ** 10
arr = []
shops = []
for x, t in zip(arr1, arr2):
    if t == 0:
        arr.append(x)
    else:
        cur = x
        shops.append(x)
        for e in arr:
            a, b = e - pre, cur - e
            if a <= b:
                buc[pre] += 1
            else:
                buc[cur] += 1
        arr = []
        pre = cur
cur = 10 ** 10
for e in arr:
    a, b = e - pre, cur - e
    if a <= b:
        buc[pre] += 1
    else:
        buc[cur] += 1
res = [str(buc[x]) for x in shops]
print(" ".join(res))
