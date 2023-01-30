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
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n, t = len(s1), len(s2), len(s3)
        if m + n != t:
            return False
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i > 0:
                    dp[i][j] |= (dp[i - 1][j] and s1[i - 1] == s3[k])
                if j > 0:
                    dp[i][j] |= (dp[i][j - 1] and s2[j - 1] == s3[k])

        return dp[m][n]


if __name__ == '__main__':
    s = Solution()
    # print(s.isInterleave(s1="aabcc", s2="dbbca", s3="aadbbcbcac"))
    print(s.isInterleave("aa", "ab", "abaa"))
