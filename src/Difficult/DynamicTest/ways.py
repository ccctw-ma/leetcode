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
    def ways(self, pizza: List[str], k: int) -> int:
        mod = 10 ** 9 + 7

        m, n = len(pizza), len(pizza[0])
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + (1 if pizza[i - 1][j - 1] == 'A' else 0) - dp[i - 1][j - 1]

        # print(dp)

        @cache
        def calc(a, b, c, d):
            return dp[c + 1][d + 1] - dp[a][d + 1] - dp[c + 1][b] + dp[a][b]

        @cache
        def f(i, t, l):
            # 最后一刀
            if i == k - 1:
                remain = calc(t, l, m - 1, n - 1)
                return 1 if remain > 0 else 0


            res = 0
            for top in range(t + 1, m):
                count = calc(t, l, top - 1, n - 1)
                if count == 0:
                    continue
                res = (res + f(i + 1, top, l)) % mod
            for left in range(l + 1, n):
                count = calc(t, l, m - 1, left - 1)
                if count == 0:
                    continue
                res = (res + f(i + 1, t, left)) % mod

            return res

        # return calc(1, 1, 1, 1)
        return f(0, 0, 0)


if __name__ == '__main__':
    s = Solution()
    print(s.ways(pizza=["A..", "AAA", "..."], k=3))
    print(s.ways(pizza=["A..", "AA.", "..."], k=3))
    print(s.ways(pizza=["A..", "A..", "..."], k=1))
    print(s.ways([".A", "AA", "A."]
                 , 3))
