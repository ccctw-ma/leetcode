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


# 很难， 难在推出状态转移方程
class Solution:
    def numPermsDISequence(self, s: str) -> int:
        mod = 10 ** 9 + 7
        n = len(s)
        dp = [[0] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 1
        for i in range(1, n + 1):
            if s[i - 1] == 'D':
                dp[i][i] = 0
                for j in range(i - 1, -1, - 1):
                    dp[i][j] = (dp[i][j + 1] + dp[i - 1][j]) % mod
            else:
                dp[i][0] = 0
                for j in range(1, i + 1):
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j - 1]) % mod
        res = 0
        for j in range(n + 1):
            res = (res + dp[n][j]) % mod
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.numPermsDISequence("DID"))
