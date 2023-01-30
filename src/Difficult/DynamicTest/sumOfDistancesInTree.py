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
    def sumOfDistancesInTree2(self, n: int, edges: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        res = [0] * n
        dp = [0] * n
        sz = [0] * n

        def dfs(x, p):
            dp[x] = 0
            sz[x] = 1
            for y in g[x]:
                if y == p:
                    continue
                dfs(y, x)
                dp[x] += dp[y] + sz[y]
                sz[x] += sz[y]

        def dfs2(x, p):
            res[x] = dp[x]
            for y in g[x]:
                if y == p:
                    continue
                px, py = dp[x], dp[y]
                sx, sy = sz[x], sz[y]
                dp[x] -= (dp[y] + sz[y])
                sz[x] -= sz[y]
                dp[y] += dp[x] + sz[x]
                sz[y] += sz[x]
                dfs2(y, x)
                dp[x] = px
                dp[y] = py
                sz[x] = sx
                sz[y] = sy

        dfs(0, -1)
        dfs2(0, -1)
        return res

    # 核心思路就是换跟
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        graph = defaultdict(list)
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)

        def dfs(u: int, parent: int) -> None:
            for v in graph[u]:
                if v != parent:
                    dfs(v, u)
                    cnt[u] += cnt[v]
                    ans[u] += ans[v] + cnt[v]

        def dfs2(u: int, parent: int) -> None:
            for v in graph[u]:
                if v != parent:
                    ans[v] = ans[u] + (n - cnt[v]) - cnt[v]
                    dfs2(v, u)

        ans, cnt = [0] * n, [1] * n
        # 1.求出 ans[0] 并统计出每个子树的节点数目
        dfs(0, -1)
        # 2.根据公式和 ans[0] 计算其余结果
        dfs2(0, -1)

        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.sumOfDistancesInTree(n=5, edges=[[0, 1], [0, 2], [0, 3], [0, 4]]))
    print(s.sumOfDistancesInTree(n=6, edges=[[0, 1], [0, 2], [2, 3], [2, 4], [2, 5]]))
    # print(s.sumOfDistancesInTree(n=1, edges=[]))
    # print(s.sumOfDistancesInTree(n=2, edges=[[1, 0]]))
