import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def findMaxCI(self, nums: List[int]) -> int:
        res = 1
        cnt = 0
        n = len(nums)
        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                cnt += 1
                res = max(res, cnt)
            else:
                cnt = 1

        return res

    def minRemainingSpace(self, N: List[int], V: int) -> int:
        # 经典的0 - 1背包问题
        # 写复杂了
        # N.Template()
        # if N[0] > V:
        #     return V
        # n = len(N)
        # res = V
        #
        # def tb(pre, index):
        #     nonlocal res
        #     if index == n:
        #         return
        #     for i in range(index, n):
        #         pre += N[i]
        #         if pre <= V:
        #             res = min(res, V - pre)
        #             tb(pre, i + 1)
        #         else:
        #             break
        #         pre -= N[i]
        #
        # tb(0, 0)
        # return res
        n = len(N)
        dp = [0] * (V + 1)
        for i in range(n):
            for j in range(V, N[i] - 1, -1):
                dp[j] = max(dp[j], dp[j - N[i]] + N[i])
        return V - dp[V]

    def hasCycle(self, graph: str) -> bool:
        edges = graph.split(",")
        graph = defaultdict(list)
        degrees = defaultdict(int)
        nodes = set()
        for e in edges:
            a, b = e.split("->")
            a, b = int(a), int(b)
            graph[a].append(b)
            degrees[b] += 1
            nodes.add(a)
            nodes.add(b)
        q = deque()
        for node in nodes:
            if degrees[node] == 0:
                q.append(node)
        vis = set()
        while q:
            n = len(q)
            for i in range(n):
                node = q.popleft()
                vis.add(node)
                for next in graph[node]:
                    degrees[next] -= 1
                    if degrees[next] == 0:
                        q.append(next)
        return len(vis) != len(nodes)

    def isCompliance(self, distance: List[List[int]], n: int) -> bool:
        m = len(distance)

        f = list(range(m))

        def find(x):
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return x

        def merge(x, y):
            px, py = find(x), find(y)
            if px != py:
                if px > py:
                    px, py = py, px
                f[py] = px

        for i in range(m):
            for j in range(i + 1):
                if distance[i][j] <= 2:
                    merge(i, j)
        s = set(f)
        return len(s) <= n

    def isPointInPolygon(self, x: float, y: float, coords: List[float]) -> bool:
        def isRayIntersectsSegment(poi, s_poi, e_poi):  # [x,y] [lng,lat]
            # 输入：判断点，边起点，边终点，都是[lng,lat]格式数组
            if s_poi[1] == e_poi[1]:  # 排除与射线平行、重合，线段首尾端点重合的情况
                return False
            if s_poi[1] > poi[1] and e_poi[1] > poi[1]:  # 线段在射线上边
                return False
            if s_poi[1] < poi[1] and e_poi[1] < poi[1]:  # 线段在射线下边
                return False
            if s_poi[1] == poi[1] and e_poi[1] > poi[1]:  # 交点为下端点，对应spoint
                return False
            if e_poi[1] == poi[1] and s_poi[1] > poi[1]:  # 交点为下端点，对应epoint
                return False
            if s_poi[0] < poi[0] and e_poi[1] < poi[1]:  # 线段在射线左边
                return False

            xseg = e_poi[0] - (e_poi[0] - s_poi[0]) * (e_poi[1] - poi[1]) / (e_poi[1] - s_poi[1])  # 求交
            if xseg < poi[0]:  # 交点在射线起点的左侧
                return False
            return True  # 排除上述情况之后

        cnt = 0
        for i in range(0, len(coords) - 3, 2):
            a, b, c, d = coords[i], coords[i + 1], coords[i + 2], coords[i + 3]
            if isRayIntersectsSegment([x, y], [a, b], [c, d]):
                cnt += 1
        return cnt % 2 == 1


if __name__ == '__main__':
    s = Solution()
    # print(s.minRemainingSpace(N=[8, 2, 12, 7, 9, 7], V=11))
    # print(s.minRemainingSpace(N=[8, 2, 12, 7, 9, 7], V=100))
    # print(s.minRemainingSpace(N=[8, 19, 18, 23, 16, 20], V=5))
    # print(s.hasCycle("1->2,2->3,3->1"))
    # print(
    #     s.hasCycle("1->4,2->5,3->6,3->7,4->8,5->8,5->9,6->9,6->11,7->11,8->12,9->12,9->13,10->13,10->14,11->10,11->14"))
    # print(s.hasCycle(
    #     "1->4,2->5,3->6,3->7,4->8,5->8,5->9,6->9,6->11,7->11,8->12,9->12,9->13,10->6,10->13,10->14,11->10,11->14"))
    # print(s.isCompliance(distance=[[0, 1, 3], [1, 0, 3], [3, 3, 0]], n=2))
    # print(s.isCompliance(distance=[[0, 3, 3], [3, 0, 3], [3, 3, 0]], n=2))
    # print(s.isCompliance([[0, 5, 1, 1, 4],
    #                       [5, 0, 1, 4, 1],
    #                       [1, 1, 0, 1, 2],
    #                       [1, 4, 1, 0, 4],
    #                       [4, 1, 2, 4, 0]]
    #                      , 2))
    # print(s.isPointInPolygon(x=1, y=3,
    #                          coords=[0, 0, 0, 4, 4, 4, 2, 2, 4, 0, 0, 0]))
    print(s.isPointInPolygon(-100,
                             250,
                             [100, 300, -200, 200, 200, 0, 60, 200, 100, 300]))
