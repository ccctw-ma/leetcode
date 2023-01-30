import math
import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


@cache
def check(p):
    k = int(math.sqrt(p)) + 1
    i = 2
    flag = True
    while i < k and flag == True:
        if p % i == 0:
            flag = False
        else:
            i += 1
    return flag


class Solution:
    def similarPairs(self, words: List[str]) -> int:
        arr = []
        for word in words:
            tmp = 0
            for c in word:
                tmp |= 1 << (ord(c) - ord('a'))
            arr.append(tmp)
        res = 0
        n = len(arr)
        for i in range(n):
            for j in range(i + 1, n):
                if arr[i] == arr[j]:
                    res += 1
        return res

    def smallestValue(self, n: int) -> int:
        while True:
            x, s, i = n, 0, 2
            while i * i <= x:
                while x % i == 0:
                    s += i
                    x //= i
                i += 1
            if x > 1: s += x
            if s == n: return n
            n = s

    def isPossible(self, n: int, edges: List[List[int]]) -> bool:
        degrees = [0] * (n + 1)
        g = defaultdict(set)
        for a, b in edges:
            g[a].add(b)
            g[b].add(a)
            degrees[a] += 1
            degrees[b] += 1
        arr = []
        even = set()
        for i in range(1, n + 1):
            if degrees[i] % 2 == 1:
                arr.append(i)
            else:
                even.add(i)

        if len(arr) == 0:
            return True
        if len(arr) == 2:
            a, b = arr[0], arr[1]
            if a not in g[b]:
                return True
            for e in even:
                if e not in g[a] and e not in g[b]:
                    return True

        def check(a, b):
            if a not in g[b]:
                return True
            for e in even:
                if e not in g[a] and e not in g[b]:
                    return True
            return False

        if len(arr) == 4:
            choice = [(0, 1, 2, 3), (0, 2, 1, 3), (0, 3, 1, 2)]
            for a, b, c, d in choice:
                a, b, c, d = arr[a], arr[b], arr[c], arr[d]

                if check(a, b) and check(c, d):
                    return True
        return False

    def cycleLengthQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        res = []
        for a, b in queries:
            cnt = 0
            while a != b:
                if a > b:
                    a //= 2
                else:
                    b //= 2
                cnt += 1

            res.append(cnt + 1)
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.isPossible(n=5, edges=[[1, 2], [2, 3], [3, 4], [4, 2], [1, 4], [2, 5]]))
    # print(s.isPossible(n=4, edges=[[1, 2], [3, 4]]))
    # print(s.isPossible(n=4, edges=[[1, 2], [1, 3], [1, 4]]))
    # print(s.isPossible(21,
    #                    [[2, 19], [16, 17], [8, 14], [2, 16], [12, 20], [12, 14], [16, 18], [15, 16], [10, 21], [3, 5], [
    #                        13, 18], [17, 20], [14, 17], [9, 12], [5, 15], [5, 6], [3, 7], [2, 21], [10, 13], [8, 16],
    #                     [7, 18], [4,
    #                               6], [9,
    #                                    1], [
    #                         13, 21], [18, 20], [7, 14], [4, 19], [5, 8], [3, 11], [11, 1], [7, 12], [4, 7], [3, 16], [
    #                         13, 17], [17, 19], [9, 13], [7, 19], [10, 16], [4, 13], [4, 5], [2, 15], [12, 19], [11,
    #                                                                                                             16], [2,
    #                                                                                                                   9],
    #                     [
    #                         11, 17], [17, 1], [16, 21], [4, 10], [10, 14], [14, 16], [4, 1], [13, 20], [5, 20], [4,
    #                                                                                                              14], [
    #                         4, 21], [10, 20], [2, 14], [8, 15], [4, 8], [6, 19], [15, 1], [19, 1], [8, 19], [15, 21], [
    #                         3, 12], [11, 18], [9, 17], [18, 19], [7, 21], [3, 21], [16, 19], [11, 15], [5, 1], [8,
    #                                                                                                             17], [3,
    #                                                                                                                   15],
    #                     [
    #                         8, 1], [10, 19], [3, 8], [6, 16], [2, 8], [5, 18], [11, 13], [11, 20], [14, 21], [6, 20], [
    #                         4, 20], [12, 13], [5, 12], [10, 11], [9, 15], [3, 19], [9, 20], [14, 18], [21, 1], [13,
    #                                                                                                             19], [8,
    #                                                                                                                   21],
    #                     [
    #                         2, 13], [3, 10], [9, 18], [19, 21], [6, 7], [3, 18], [2, 18], [6, 14], [3, 17], [5, 21], [
    #                         14, 20], [8, 9], [16, 1], [3, 4], [13, 1], [5, 9], [4, 15], [17, 21], [20, 21], [2, 17], [
    #                         13, 14], [11, 14], [9, 16], [10, 18], [6, 15], [6, 12], [3, 13], [5, 11], [6, 1], [12,
    #                                                                                                            17], [8,
    #                                                                                                                  10],
    #                     [
    #                         5, 10], [8, 18], [4, 12], [10, 1], [6, 13], [4, 18], [7, 20], [7, 16], [2, 6], [12, 21], [4,
    #                                                                                                                   17],
    #                     [
    #                         15, 18], [13, 16], [15, 20], [7, 10], [6, 10], [2, 20], [7, 15], [18, 1], [12, 1], [3,
    #                                                                                                             20], [7,
    #                                                                                                                   1],
    #                     [
    #                         14, 15], [4, 9], [11, 19], [7, 9], [5, 17], [18, 21], [6, 21], [8, 11], [6, 17], [3, 14], [
    #                         7, 11], [5, 7], [7, 13], [6, 8], [6, 9], [10, 12], [5, 16], [2, 4], [17, 18], [9, 11], [12,
    #                                                                                                                 16],
    #                     [
    #                         3, 6], [12, 18], [3, 9], [11, 12], [14, 19], [10, 15], [5, 13], [8, 13], [15, 17], [2,
    #                                                                                                             10], [
    #                         11, 21], [20, 1], [6, 18], [2, 12], [19, 20], [6, 11], [8, 12], [2, 3], [12, 15], [2, 11], [
    #                         9, 10], [7, 17], [9, 19], [13, 15], [7, 8], [4, 11], [2, 5], [5, 19], [16, 20], [15, 19], [
    #                         9, 14], [14, 1], [10, 17], [9, 21], [2, 7], [8, 20], [5, 14], [4, 16]]))
    # print(s.smallestValue(15))
    # print(s.smallestValue(4))
    # print(s.smallestValue(18))
    # print(s.smallestValue(30))
    print(s.cycleLengthQueries(n=3, queries=[[5, 3], [4, 7], [2, 3]]))
