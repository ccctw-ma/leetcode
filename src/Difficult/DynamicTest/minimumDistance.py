import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


def calc(a, b):
    ax, ay = divmod(a, 6)
    bx, by = divmod(b, 6)
    return abs(ax - bx) + abs(ay + by)


class Solution:
    def minimumDistance(self, word: str) -> int:
        def calc(now, nxt):
            if now == 30:
                return 0
            x, y = now // 6, now % 6
            nx, ny = nxt // 6, nxt % 6
            return abs(x - nx) + abs(y - ny)

        @cache
        def dfs(i, l, r):
            print(i, l, r)
            if i == len(word):
                return 0
            nxt = ord(word[i]) - ord('A')
            return min(dfs(i + 1, nxt, r) + calc(l, nxt), dfs(i + 1, l, nxt) + calc(r, nxt))

        return dfs(0, 30, 30)

    def minimumDistance2(self, word: str) -> int:
        MAX = 10 ** 5
        n = len(word)
        dp = [[[MAX] * 26 for _ in range(26)] for _ in range(n + 1)]
        for i in range(26):
            for j in range(26):
                dp[0][i][j] = 0

        def calc(a, b):
            ax, ay = divmod(a, 6)
            bx, by = divmod(b, 6)
            return abs(ax - bx) + abs(ay - by)

        for i in range(1, n + 1):
            c = ord(word[i - 1]) - ord('A')
            for l in range(26):
                for r in range(26):
                    # 上一个状态时存在的
                    if dp[i - 1][l][r] != MAX:
                        # 左手动
                        dp[i][c][r] = min(dp[i][c][r], dp[i - 1][l][r] + calc(c, l))
                        # 右手动
                        dp[i][l][c] = min(dp[i][l][c], dp[i - 1][l][r] + calc(c, r))

        res = MAX
        for i in range(26):
            for j in range(26):
                res = min(res, dp[n][i][j])
        return res

    def minimumDistance3(self, word: str) -> int:
        n = len(word)
        BIG = 2 ** 30
        dp = [[0] * 26] + [[BIG] * 26 for _ in range(n - 1)]

        def getDistance(p, q):
            x1, y1 = p // 6, p % 6
            x2, y2 = q // 6, q % 6
            return abs(x1 - x2) + abs(y1 - y2)

        for i in range(1, n):
            cur, prev = ord(word[i]) - 65, ord(word[i - 1]) - 65
            d = getDistance(prev, cur)
            for j in range(26):
                dp[i][j] = min(dp[i][j], dp[i - 1][j] + d)
                if prev == j:
                    for k in range(26):
                        d0 = getDistance(k, cur)
                        dp[i][j] = min(dp[i][j], dp[i - 1][k] + d0)

        ans = min(dp[n - 1])
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.minimumDistance2(word="HAPPY"))
