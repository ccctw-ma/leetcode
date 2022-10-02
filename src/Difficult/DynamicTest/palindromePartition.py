from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional
from functools import lru_cache, cache

class Solution:
    def palindromePartition(self, s: str, k: int) -> int:
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        for i in range(n):
            dp[i][i] = 0

        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if j - i > 1:
                    if s[i] == s[j]:
                        dp[i][j] = dp[i + 1][j - 1]
                    else:
                        dp[i][j] = dp[i + 1][j - 1] + 1
                else:
                    dp[i][j] = 1 if s[i] != s[j] else 0
        f = [[2 * n] * k for _ in range(n)]
        for i in range(n):
            f[i][0] = dp[0][i]
        for t in range(1, k):
            for e in range(t, n):
                for a in range(e):
                    f[e][t] = min(f[e][t], f[a][t - 1] + dp[a + 1][e])

        return f[n - 1][k - 1]

    def palindromePartition2(self, s: str, k: int) -> int:
        # 使用lru_cache还是更加形象一些
        @lru_cache(None)
        def count(start: int, end: int) -> int:
            return 0 if start >= end else (s[start] != s[end]) + count(start + 1, end - 1)

        @lru_cache(None)
        def partition(i: int, k: int) -> int:
            return count(0, i) if k == 1 else min(partition(j, k - 1) + count(j + 1, i) for j in range(k - 2, i))

        return partition(len(s) - 1, k)



if __name__ == '__main__':
    s = Solution()
    print(s.palindromePartition(s="abc", k=2))
    print(s.palindromePartition("aabbc", 3))
