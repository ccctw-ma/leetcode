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
    def maxProduct(self, s: str) -> int:
        n = len(s)

        @cache
        def check(a, b):
            la = maxPalindromes(a)
            lb = maxPalindromes(b)
            return la * lb

        @cache
        def maxPalindromes(x):
            i = 0
            arr = []
            while x != 0:
                if x & 1:
                    arr.append(s[i])
                x >>= 1
                i += 1
            a = "".join(arr)
            b = a[::-1]
            m, n = len(a), len(b)
            dp = [[0] * (n + 1) for _ in range(m + 1)]
            for i in range(1, m + 1):
                for j in range(1, n + 1):
                    if a[i - 1] == b[j - 1]:
                        dp[i][j] = dp[i - 1][j - 1] + 1
                    else:
                        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            return dp[m][n]

        res = 0
        MAX = 1 << n
        for i in range(1, MAX):
            j = (MAX - 1) ^ i
            if i > j:
                i, j = j, i
            tmp = check(i, j)
            res = max(res, tmp)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxProduct(s="leetcodecom"))
    print(s.maxProduct("zzzzzzzzzzzz"))
