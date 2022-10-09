from string import ascii_lowercase
from typing import List, Optional, Union, Set
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from functools import lru_cache, cache, reduce


class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        arr = []
        pre = 0
        for id, t in logs:
            arr.append((t - pre, id))
            pre = t
        arr.sort(key=lambda x: (-x[0], x[1]))
        return arr[0][1]

    def findArray(self, pref: List[int]) -> List[int]:
        pre = 0
        res = []
        for p in pref:
            res.append(p ^ pre)
            pre = p
        return res

    def robotWithString(self, s: str) -> str:
        n = len(s)
        f = [''] * (n + 1)
        f[n] = chr(ord('z') + 10)
        for i in range(n - 1, -1, -1):
            f[i] = min(f[i + 1], s[i])
        res = ''
        stk = []
        for i, c in enumerate(s):
            stk.append(c)
            while stk and stk[-1] <= f[i + 1]:
                res += stk[-1]
                stk.pop()
        return res
    def numberOfPaths(self, g: List[List[int]], k: int) -> int:
        n, m = len(g), len(g[0])
        f = [[[0] * k for _ in range(m + 1)] for _ in range(n + 1)]
        f[1][1][g[0][0] % k] = 1
        for i in range(n):
            for j in range(m):
                if i == 0 and j == 0:
                    continue
                for t in range(k):
                    tt = (t + g[i][j]) % k
                    f[i + 1][j + 1][tt] += f[i][j + 1][t]
                    f[i + 1][j + 1][tt] += f[i + 1][j][t]
                    f[i + 1][j + 1][tt] %= (10 ** 9 + 7)
        return f[n][m][0]


if __name__ == '__main__':
    s = Solution()
    print('a' < chr(ord('z') + 10))

    ss = "bydizfve"
    ss = "vzhofnpo"
    print(sorted(list(ss)))
    print(s.robotWithString(ss))
    print(ascii_lowercase[25])