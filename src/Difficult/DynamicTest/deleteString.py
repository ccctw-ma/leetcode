from typing import List, Optional, Union, Set
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from functools import lru_cache, cache, reduce


class Solution:
    def deleteString(self, s: str) -> int:
        @cache
        def fn(s, index):
            if index == len(s):
                return 0
            span = 1
            res = 1
            while index + span * 2 <= len(s):
                if s[index: index + span] == s[index + span: index + 2 * span]:
                    res = max(res, 1 + fn(s, index + span))
                span += 1
            return res

        return fn(s, 0)

    def deleteString2(self, s: str) -> int:
        n = len(s)
        dp = [1 for _ in range(n + 1)]
        for l in range(n - 1, -1, -1):
            for m in range(l + 1, n):
                r = m + (m - l)
                if r <= n and s[l:m] == s[m:r]:
                    dp[l] = max(dp[l], dp[m] + 1)
        return dp[0]

    def deleteString3(self, s: str) -> int:
        n = len(s)
        if len(set(s)) == 1: return n  # 特判全部相同的情况
        lcp = [[0] * (n + 1) for _ in range(n + 1)]  # lcp[i][j] 表示 s[i:] 和 s[j:] 的最长公共前缀
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, i, -1):
                if s[i] == s[j]:
                    lcp[i][j] = lcp[i + 1][j + 1] + 1
        f = [0] * n
        for i in range(n - 1, -1, -1):
            for j in range(1, (n - i) // 2 + 1):
                if lcp[i][i + j] >= j:  # 说明 s[i:i+j] == s[i+j:i+2*j]
                    f[i] = max(f[i], f[i + j])
            f[i] += 1
        return f[0]


if __name__ == '__main__':
    s = Solution()
    print(s.deleteString("aaaaa"))
    print(s.deleteString("abcabcdabc"))
    num = 22
    print(bin(num))
    print(num.bit_count())
