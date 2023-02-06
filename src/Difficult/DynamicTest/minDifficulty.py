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
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        if n < d:
            return -1
        dp = [0] * n
        curMax = jobDifficulty[0]
        for i in range(n):
            curMax = max(curMax, jobDifficulty[i])
            dp[i] = curMax
        for t in range(1, d):
            tmp = [inf] * n
            for j in range(t, n):
                curMax = jobDifficulty[j]
                for k in range(j, t - 1, -1):
                    curMax = max(curMax, jobDifficulty[k])
                    tmp[j] = min(tmp[j], dp[k - 1] + curMax)

            dp = tmp
        return dp[-1]


if __name__ == '__main__':
    s = Solution()
    print(s.minDifficulty(jobDifficulty=[7, 1, 7, 1, 7, 1], d=3))
    print(s.minDifficulty(jobDifficulty=[11, 111, 22, 222, 33, 333, 44, 444], d=6))
