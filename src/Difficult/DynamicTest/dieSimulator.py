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
    def dieSimulator2(self, n: int, rollMax: List[int]) -> int:
        mod = 10 ** 9 + 7

        @cache
        def f(idx: int, pre: int, c: int) -> int:
            if idx == n:
                return 1
            res = 0
            for i in range(6):
                count = 1 if pre != i else c + 1
                if count <= rollMax[i]:
                    res += f(idx + 1, i, count)
            return res % mod

        return sum(f(1, i, 1) for i in range(6)) % mod

    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        f = [[0] * 7 for _ in range(n)]
        for j in range(1, 7):
            f[0][j] = 1
        cnt, mod = [6] + [0] * (n - 1), 10 ** 9 + 7
        for i in range(1, n):
            for j in range(1, 7):
                f[i][j] = cnt[i - 1]
                f[i][j] -= (cnt[k] - f[k][j]) if (k := i - rollMax[j - 1] - 1) >= 0 else (k == -1)
                cnt[i] = (cnt[i] + f[i][j]) % mod
        return cnt[n - 1]

    def dieSimulator3(self, n: int, rollMax: List[int]) -> int:
        MOD = 10 ** 9 + 7
        f = [deque([0] * (x - 1) + [1]) for x in rollMax]
        c, tot = [1] * 6, 6
        for _ in range(1, n):
            nxt = tot
            for i, q in enumerate(f):
                u = tot - c[i]
                v = q.popleft()
                q.append(u)
                c[i] = (c[i] + u - v) % MOD
                nxt += u - v

            tot = nxt % MOD
        return tot


if __name__ == '__main__':
    s = Solution()
    print(s.dieSimulator(n=2, rollMax=[1, 1, 2, 2, 2, 3]))
    print(s.dieSimulator(n=3, rollMax=[1, 1, 1, 2, 2, 3]))
