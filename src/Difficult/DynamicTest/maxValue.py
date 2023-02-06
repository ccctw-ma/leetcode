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
    def maxValue(self, events: List[List[int]], k: int) -> int:
        n = len(events)
        events.sort(key=lambda x: x[1])
        ends = [e for s, e, v in events]
        # print(events)
        dp = [0] * n
        dp[0] = events[0][2]
        for i in range(1, n):
            dp[i] = max(dp[i - 1], events[i][2])

        for t in range(1, k):
            tmp = [0] * n
            tmp[0] = events[0][2]
            for i in range(1, n):
                s, e, v = events[i]
                l, r = 0, n - 1
                while l < r:
                    m = (l + r + 1) // 2
                    if ends[m] < s:
                        l = m
                    else:
                        r = m - 1
                pre = dp[l] if ends[l] < s else 0
                tmp[i] = max(tmp[i - 1], pre + v)
            dp = tmp
        return dp[-1]


if __name__ == '__main__':
    s = Solution()
    print(s.maxValue(events=[[1, 2, 4], [3, 4, 3], [2, 3, 1]], k=2))
    print(s.maxValue(events=[[1, 2, 4], [3, 4, 3], [2, 3, 10]], k=2))
    print(s.maxValue(events=[[1, 1, 1], [2, 2, 2], [3, 3, 3], [4, 4, 4]], k=3))
