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


class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        arr = sorted(zip(ages, scores))
        print(arr)
        n = len(scores)
        dp = [0] * n
        res = 0
        for i in range(n):
            a, s = arr[i]
            dp[i] = s
            for j in range(i - 1, -1, -1):
                if arr[j][0] == a or arr[j][1] <= s:
                    dp[i] = max(dp[i], dp[j] + s)
        return max(dp)


if __name__ == '__main__':
    s = Solution()
    print(s.bestTeamScore(scores=[1, 3, 5, 10, 15], ages=[1, 2, 3, 4, 5]))
    print(s.bestTeamScore(scores=[4, 5, 6, 5], ages=[2, 1, 2, 1]))
    print(s.bestTeamScore([1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                          [811, 364, 124, 873, 790, 656, 581, 446, 885, 134]))
