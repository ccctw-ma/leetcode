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


class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        words = sentence.split(" ")
        n = len(words)
        for i in range(1, n):
            a, b = words[i - 1], words[i]
            if a[-1] != b[0]:
                return False
        return words[0][0] == words[-1][-1]

    def dividePlayers(self, skill: List[int]) -> int:
        s = sum(skill)
        n = len(skill)
        ave = s // (n // 2)
        buc = Counter(skill)
        res = 0
        vis = set()
        for k in buc.keys():
            if k in vis:
                continue
            b = ave - k
            if b in buc and buc[k] == buc[b]:
                if k == b:
                    res += k * b * (buc[k] // 2)
                else:
                    res += (k * b) * buc[k]
                vis.add(k)
                vis.add(b)
            else:
                return -1

        return res

    def minScore(self, n: int, roads: List[List[int]]) -> int:
        graph = defaultdict(list)
        cost = [[0] * (n + 1) for _ in range(n + 1)]

        for a, b, c in roads:
            graph[a].append(b)
            graph[b].append(a)
            cost[a][b] = c
            cost[b][a] = c

        res = 100001
        vis = set()

        def dfs(x: int) -> None:
            nonlocal res, vis
            vis.add(x)
            for y in graph[x]:
                res = min(res, cost[x][y])
                if y not in vis:
                    dfs(y)

        dfs(1)
        return res

    def minScore2(self, n: int, roads: List[List[int]]) -> int:

        d = list(range(n + 1))

        def find(a):
            nonlocal d
            if d[a] == a:
                return a
            d[a] = find(d[a])
            return d[a]

        r = 10000000
        for a, b, c in roads:
            d[find(a)] = find(b)

        for a, b, c in roads:
            if find(a) == find(1):
                r = min(r, c)
        return r

    def magnificentSets(self, n: int, edges: List[List[int]]) -> int:
        pass


if __name__ == '__main__':
    s = Solution()
    # print(s.dividePlayers(skill=[3, 2, 5, 1, 3, 4]))
    # print(s.minScore(4,
    #                  [[1, 2, 9], [2, 3, 6], [2, 4, 5], [1, 4, 7]]))
    # print(s.minScore(6,
    #                  [[4, 5, 7468], [6, 2, 7173], [6, 3, 8365], [2, 3, 7674], [5, 6, 7852], [1, 2, 8547], [2, 4, 1885],
    #                   [2, 5, 5192], [1, 3, 4065], [1, 4, 7357]]))
    # print(s.minScore(7,
    #                  [[1, 3, 1484], [3, 2, 3876], [2, 4, 6823], [6, 7, 579], [5, 6, 4436], [4, 5, 8830]]))
    with open('test.txt') as f:
        n = int(f.readline())
        arr = eval(f.readline())

    a = time.time()
    print(s.minScore2(n, arr))
    print(time.time() - a)
    # a = time.time()
    # print(s.minScore2(n, arr))
    # print(time.time() - a)
