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
    def minHeightShelves(self, books: List[List[int]], shelfWidth: int) -> int:
        n = len(books)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i - 1] + books[i - 1][0]
        dp = [10**7] * (n + 1)
        dp[0] = 0
        for i in range(1, n + 1):
            h = books[i - 1][1]
            dp[i] = dp[i - 1] + h
            for j in range(i - 1, 0, -1):
                h = max(h, books[j - 1][1])
                if f[i] - f[j - 1] <= shelfWidth:
                    dp[i] = min(dp[i], dp[j - 1] + h)
        return dp[n]


if __name__ == '__main__':
    s = Solution()
    print(s.minHeightShelves(books=[[1, 1], [2, 3], [2, 3], [1, 1], [1, 1], [1, 1], [1, 2]], shelfWidth=4))
