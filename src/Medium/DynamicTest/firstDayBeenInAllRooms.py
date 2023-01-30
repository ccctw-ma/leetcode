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
    def firstDayBeenInAllRooms(self, nextVisit: List[int]) -> int:
        mod = 10 ** 9 + 7
        n = len(nextVisit)
        dp = [[0, 0] for _ in range(n)]
        dp[0][0] = 0
        dp[0][1] = 1
        for i in range(1, n):
            dp[i][0] = (dp[i - 1][1] + 1) % mod
            dp[i][1] = (dp[i][0] + (1 if nextVisit[i] == i else (dp[i][0] - dp[nextVisit[i]][0] + 1))) % mod
        # print(dp)
        return dp[n - 1][0] % mod


if __name__ == '__main__':
    s = Solution()
    print(s.firstDayBeenInAllRooms(nextVisit=[0, 0, 1, 2]))
