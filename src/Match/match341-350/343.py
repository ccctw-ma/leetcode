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


class Solution:
    def isWinner(self, player1: List[int], player2: List[int]) -> int:
        def fn(arr):
            res = 0
            for i in range(len(arr)):
                if (i >= 1 and arr[i - 1] == 10) or (i >= 2 and arr[i - 2] == 10):
                    res += arr[i] * 2
                else:
                    res += arr[i]
            return res

        a, b = fn(player1), fn(player2)
        if a > b:
            return 1
        elif a < b:
            return 2
        return 0

    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        buc = defaultdict(tuple)
        for i in range(m):
            for j in range(n):
                buc[mat[i][j]] = (i, j)
        row, col = defaultdict(int), defaultdict(int)
        for i, c in enumerate(arr):
            x, y = buc[c]
            row[x] += 1
            col[y] += 1
            if row[x] == n or col[y] == m:
                return i

    def minimumCost(self, start: List[int], target: List[int], specialRoads: List[List[int]]) -> int:
        def distance(x1, y1, x2, y2):
            return abs(x1 - x2) + abs(y1 - y2)

        specialRoads = list(filter(lambda x: distance(x[0], x[1], x[2], x[3]) > x[4], specialRoads))
        # print(specialRoads)
        if not specialRoads:
            return distance(start[0], start[1], target[0], target[1])

        # 建图
        nodes = set()
        nodes.add((start[0], start[1]))
        nodes.add((target[0], target[1]))
        for x1, y1, x2, y2, cost in specialRoads:
            nodes.add((x1, y1))
            nodes.add((x2, y2))
        idx2xy = defaultdict(list)
        xy2idx = defaultdict(int)
        n = len(nodes)
        s, e = 0, 0
        for i, (x, y) in enumerate(nodes):
            if x == start[0] and y == start[1]:
                s = i
            if x == target[0] and y == target[1]:
                e = i
            idx2xy[i] = [x, y]
            xy2idx[(x, y)] = i
        g = [[-1] * n for _ in range(n)]
        for x1, y1, x2, y2, cost in specialRoads:
            a, b = xy2idx[(x1, y1)], xy2idx[(x2, y2)]
            g[a][b] = cost if g[a][b] == -1 else min(cost, g[a][b])
            # g[b][a] = cost
        for i in range(n):
            for j in range(n):
                if i == j:
                    continue
                x1, y1 = idx2xy[i]
                x2, y2 = idx2xy[j]
                dis = distance(x1, y1, x2, y2)
                if g[i][j] == -1:
                    g[i][j] = dis
                if g[j][i] == -1:
                    g[j][i] = dis
        # 在g上求 s, e的最短距离
        # for row in g:
        #     print(row)
        # print(idx2xy)
        # print(s, e)
        MAX = 10 ** 9 + 7
        dis = [MAX] * n
        h = [(0, s)]
        dis[s] = 0
        vis = set()
        while h:
            cost, x = heappop(h)
            vis.add(x)
            # 访问x的邻接点
            for y, c in enumerate(g[x]):
                if y in vis or y == x:
                    continue
                ncost = cost + c
                if ncost < dis[y]:
                    dis[y] = ncost
                    heappush(h, (ncost, y))
        # print(dis)
        return dis[e]

    def minimumCost(self, start: List[int], target: List[int], specialRoads: List[List[int]]) -> int:
        t = tuple(target)
        dis = defaultdict(lambda: inf)
        dis[tuple(start)] = 0
        vis = set()
        while True:
            v, dv = None, -1
            for p, d in dis.items():
                if p not in vis and (dv < 0 or d < dv):
                    v, dv = p, d
            if v == t: return dv  # 到终点的最短路已确定
            vis.add(v)
            vx, vy = v
            dis[t] = min(dis[t], dv + t[0] - vx + t[1] - vy)  # 更新到终点的最短路
            for x1, y1, x2, y2, cost in specialRoads:
                w = (x2, y2)
                dis[w] = min(dis[w], dv + abs(x1 - vx) + abs(y1 - vy) + cost)

    def smallestBeautifulString(self, s: str, k: int) -> str:
        bak = ascii_lowercase[:k]
        s = list(s)
        for i in range(len(s) - 1, -1, -1):
            p = ord(s[i]) - ord('a')
            for j in range(p + 1, k):
                if i and bak[j] == s[i - 1]:
                    continue
                if i > 1 and bak[j] == s[i - 2]:
                    continue
                s[i] = bak[j]
                for nj in range(i + 1, len(s)):
                    for p in range(k):
                        if nj and bak[p] == s[nj - 1]:
                            continue
                        if nj > 1 and bak[p] == s[nj - 2]:
                            continue
                        s[nj] = bak[p]
                        break

                return "".join(s)
        return ""


# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题
# 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题 认真读题


if __name__ == '__main__':
    s = Solution()
    # print(s.minimumCost(start=[1, 1], target=[4, 5], specialRoads=[[1, 2, 3, 3, 2], [3, 4, 4, 5, 1]]))
    # print(s.minimumCost(start=[3, 2], target=[5, 7], specialRoads=[[3, 2, 3, 4, 4], [3, 3, 5, 5, 5], [3, 4, 5, 6, 6]]))
    # print(s.minimumCost([1, 1]
    #                     , [10, 4]
    #                     , [[4, 2, 1, 1, 3], [1, 2, 7, 4, 4], [10, 3, 6, 1, 2], [6, 1, 1, 2, 3]]))
    print(s.minimumCost([2, 1],
                        [6, 1],
                        [[3, 1, 5, 1, 6], [2, 1, 6, 1, 2], [2, 1, 6, 1, 3], [6, 1, 6, 1, 2], [5, 1, 6, 1, 7],
                         [4, 1, 6, 1, 6], [4, 1, 4, 1, 6], [2, 1, 2, 1, 1]]))
    # print(s.smallestBeautifulString(s="abcz", k=26))
    # print(s.smallestBeautifulString(s="dc", k=4
    #                                 ))
