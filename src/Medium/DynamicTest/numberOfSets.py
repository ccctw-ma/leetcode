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
    def numberOfSets(self, n: int, k: int) -> int:
        mod = 10 ** 9 + 7

        dp = [[[0, 0] for _ in range(k)] for _ in range(n)]
        dp[0][0][0] = 1
        for i in range(1, n):
            for j in range(k + 1):
                dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1]
                dp[i][j][1] = dp[i - 1][j][1]
                if j > 0:
                    dp[i][j][1] += dp[i - 1][j - 1][0]
                    dp[i][j][1] += dp[i - 1][j - 1][1]

        return (dp[n - 1][k][0] + dp[n - 1][k][1]) % mod


if __name__ == '__main__':
    s = Solution()
