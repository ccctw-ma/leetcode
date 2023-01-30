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
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort(key=lambda x: x[1])
        endIds = [e for _, e, _ in events]
        dp = [0] * (len(events) + 1)
        res = 0
        for i, (s, e, v) in enumerate(events):
            idx = bisect_right(endIds, s - 1)
            dp[i + 1] = max(dp[i], v)
            res = max(res, dp[idx] + v)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxTwoEvents(events=[[1, 3, 2], [4, 5, 2], [2, 4, 3]]))
