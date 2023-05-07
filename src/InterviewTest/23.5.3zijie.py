import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right

# n = int(input())
# arr = list(map(int, input().split(" ")))
# arr.sort()
# res = n
# for i in range(n):
#     idx = bisect_left(arr, arr[i] + n)
#     cur = idx - i
#     res = min(res, n - cur)
# print(res)
# 5
# 2 0 -1 3 6

# n = int(input())
# preOrder = list(map(int, input().split(" ")))
# inOrder = list(map(int, input().split(" ")))
# buc = dict()
# for i, c in inOrder:
#     buc[c] = i
# class Node:
#     def __init__(self, val):
#         self.val = val
#         self.left = None
#         self.right= None
#
# def buildTree(preOrd, inOrd, pl, pr, il, ir):
#     if pl > pr or il > ir:
#         return None
#     root = Node(preOrd[pl])


from math import inf
from bisect import insort

n = int(input())
arr = list(map(int, input().split(" ")))
dp = [-inf] * n
dp[0] = 0
dp[2] = arr[2]
stl = [(0, 0), (dp[2], 2)]
for i in range(4, n):
    for j in range(len(stl) - 1, -1, -1):
        v, idx = stl[j]
        dis = i - idx
        if dis != 3 and dis != 1:
            dp[i] = v + arr[i]
            break
    insort(stl, (dp[i], i))
print(max(dp[-5:]))
