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


class Solution:
    def addMinimum(self, word: str) -> int:
        res = 0
        n = len(word)
        i = 0
        while i < n:
            cur = word[i]
            if i == n - 2:
                if word[i:] in ['ab', 'ac', 'bc']:
                    res += 1
                    i += 2
                else:
                    res += 4
                    i += 2
            elif i == n - 1:
                res += 2
                i += 1
            else:
                if word[i:i + 3] == 'abc':
                    i += 3
                elif word[i:i + 2] in ['ab', 'ac', 'bc']:
                    res += 1
                    i += 2
                else:
                    res += 2
                    i += 1
        return res

    def minimumTotalPrice(self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]) -> int:
        # 先找出所有点之间的路径
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        buc = defaultdict(list)

        tmp = []

        def dfs(x, t, p, arr):
            nonlocal tmp
            if x == t:
                tmp = arr.copy()
                return
            for y in g[x]:
                if y == p:
                    continue
                arr.append(y)
                dfs(y, t, x, arr)
                arr.pop(-1)

        for a, b in trips:
            if (a, b) in buc:
                continue
            dfs(a, b, -1, [a])
            buc[(a, b)] = tmp
        cnt = Counter()
        for a, b in trips:
            path = buc[(a, b)]
            for e in path:
                cnt[e] += 1
        for i, p in enumerate(price):
            price[i] = p * cnt[i]

        # print(price)

        # print(sum(price))
        # print(buc)
        def f1(x, p, flag):
            if (x, p, flag) in dp:
                return dp[(x, p, flag)]
            tmp = p1[x] // 2 if flag else p1[x]
            for y in g[x]:
                if y == p:
                    continue
                if flag:
                    tmp += f1(y, x, not flag)
                else:
                    tmp += min(f1(y, x, flag), f1(y, x, not flag))
            dp[(x, p, flag)] = tmp
            return tmp

        ans = 10 ** 10
        for x in range(n):
            dp = {}
            p1 = price.copy()
            ans = min(ans, f1(x, -1, True))
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.addMinimum('aaa'))
    # print(s.addMinimum('abc'))
    # print(s.addMinimum('b'))
    # print(s.addMinimum('cba'))
    # print(s.minimumTotalPrice(n=4, edges=[[0, 1], [1, 2], [1, 3]], price=[2, 2, 10, 6], trips=[[0, 3], [2, 1], [2, 3]]))
    # print(s.minimumTotalPrice(n=2, edges=[[0, 1]], price=[2, 2], trips=[[0, 0]]))
    # print(s.minimumTotalPrice(5
    #                           , [[1, 2], [2, 0], [0, 3], [3, 4]]
    #                           , [12, 26, 22, 12, 2]
    #                           ,
    #                           [[3, 3], [3, 2], [3, 0], [3, 4], [1, 1], [2, 2], [4, 0], [0, 2], [2, 3], [2, 1], [4, 2], [
    #                               0, 1], [4, 2], [0, 4], [0, 3], [4, 0], [4, 0], [3, 3], [4, 3], [2, 2], [4, 2], [1, 4],
    #                            [3, 2], [4, 4], [4,
    #                                             2], [
    #                                2, 3], [4, 3], [4, 4], [4, 2], [0, 4], [4, 2], [3, 4], [4, 0], [3, 2], [3, 1],
    #                            [2, 0], [0, 4], [3, 4], [2,
    #                                                     0], [
    #                                1, 4], [4, 2], [4, 4], [2, 1], [0, 1], [4, 1], [3, 4], [0, 4], [2, 0], [2, 0],
    #                            [3, 3], [4, 4], [0, 1], [0,
    #                                                     1], [
    #                                0, 1], [2, 0], [0, 1], [3, 1], [3, 4], [3, 4], [4, 2], [0, 4], [4, 4], [3, 2],
    #                            [2, 1], [3, 2], [1, 4], [1,
    #                                                     0], [
    #                                4, 2], [4, 3], [3, 1], [4, 4], [3, 1], [1, 0], [0, 0], [0, 0], [3, 0], [0, 2],
    #                            [2, 2], [3, 3], [0, 3]]))
    print(s.minimumTotalPrice(9
                              , [[2, 5], [3, 4], [4, 1], [1, 7], [6, 7], [7, 0], [0, 5], [5, 8]]
                              , [4, 4, 6, 4, 2, 4, 2, 14, 8]
                              , [[1, 5], [2, 7], [4, 3], [1, 8], [2, 8], [4, 3], [1, 5], [1, 4], [2, 1], [6, 0], [0, 7],
                                 [8, 6], [4, 0],
                                 [7, 5], [7, 5], [6, 0], [5, 1], [1, 1], [7, 5], [1, 7], [8, 7], [2, 3], [4, 1], [3, 5],
                                 [2, 5], [3, 7],
                                 [0, 1], [5, 8], [5, 3], [5, 2]]))
