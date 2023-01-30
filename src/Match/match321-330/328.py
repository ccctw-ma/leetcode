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
    # 二维差分数组
    def rangeAddQueries(self, n: int, queries: List[List[int]]) -> List[List[int]]:
        diff = [[0] * (n + 1) for _ in range(n + 1)]
        for r1, c1, r2, c2 in queries:
            diff[r2 + 1][c2 + 1] += 1
            diff[r2 + 1][c1] -= 1
            diff[r1][c2 + 1] -= 1
            diff[r1][c1] += 1
        mat = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n):
            for j in range(n):
                mat[i + 1][j + 1] = mat[i][j + 1] + mat[i + 1][j] - mat[i][j] + diff[i][j]

        del mat[0]
        for row in mat:
            del row[0]
        return mat

    def countGood(self, nums: List[int], k: int) -> int:
        res = 0
        n = len(nums)
        j = 0
        cnt = Counter()
        count = 0
        find = False
        for i in range(n):
            cnt[nums[i]] += 1
            cc = cnt[nums[i]]
            count += (comb(cc, 2) - comb(cc - 1, 2))
            if count >= k:
                find = True
            if find:
                while count >= k:
                    cnt[nums[j]] -= 1
                    cc = cnt[nums[j]]
                    count -= (comb(cc + 1, 2) - comb(cc, 2))
                    j += 1
                res += j
        return res

    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        ans = 0

        def dfs(x, pa):
            max_s1 = p = price[x]
            max_s2 = 0
            nonlocal ans
            for y in g[x]:
                if y == pa:
                    continue
                s1, s2 = dfs(y, x)
                ans = max(ans, max_s1 + s2, max_s2 + s1)
                max_s1 = max(max_s1, p + s1)
                max_s2 = max(max_s2, p + s2)
            return max_s1, max_s2

        dfs(0, - 1)
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.rangeAddQueries(n=3, queries=[[1, 1, 2, 2], [0, 0, 1, 1]]))
    # print(comb(1, 2))
    # print(comb(0, 2))
    print(s.countGood(nums=[1, 1, 1, 1, 1], k=10))
    print(s.countGood(nums=[3, 1, 4, 3, 2, 2, 4], k=2))
    print(s.countGood([2, 3, 1, 3, 2, 3, 3, 3, 1, 1, 3, 2, 2, 2]
                      , 18))
    print(Counter([1, 1, 1, 2, 2, 2]) - Counter([1, 2]))
