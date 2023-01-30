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
    def numSquares(self, n: int) -> int:
        arr = []
        for i in range(1, n + 1):
            if i ** 2 <= n:
                arr.append(i ** 2)
        dp = [10 ** 9 + 7] * (n + 1)
        dp[0] = 0
        for i in range(1, n + 1):
            for c in arr:
                if c <= i:
                    dp[i] = min(dp[i], 1 + dp[i - c])
                else:
                    break
        return dp[n]


if __name__ == '__main__':
    s = Solution()
    print(s.numSquares(12))
