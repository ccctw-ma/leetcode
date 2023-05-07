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


def findDataLoactions(locations: List[int], movedFrom: List[int], movedTo: List[int]):
    locations = set(locations)
    for a, b in zip(movedFrom, movedTo):
        locations.remove(a)
        locations.add(b)
    return sorted(locations)

def getMaximumGreyness(pixels: List[str]):
    m, n = len(pixels), len(pixels[0])
    rowOne, rowZero = [0] * m ,[0] * m
    colOne, colZero = [0] * n, [0] * n
    for i in range(m):
        c = 0
        for j in range(n):
            c += int(pixels[i][j] == '1')
        rowOne[i], rowZero[i] = c, n - c
    for j in range(n):
        c = 0
        for i in range(m):
            c += int(pixels[i][j] == '1')
        colOne[j], colZero[j] = c, m - c
    res = -(10 ** 10)
    for i in range(m):
        for j in range(n):
            res = max(res, rowOne[i] + colOne[j] - rowZero[i] - colZero[j])
    return res

# print(findDataLoactions([1, 2, 3], [1, 2], [5, 6]))
# print(findDataLoactions([1, 5, 2, 6], [1, 4, 5, 7], [4, 7, 1, 3]))

print(getMaximumGreyness(['1011', '0011', '1101']))
print(getMaximumGreyness(['011', '101', '001']))
print(getMaximumGreyness(['000', '000', '000']))