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
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        # rides.Template(key=lambda x: (x[1], x[0]))
        # print(rides)
        # dp = [0] * (n + 1)
        # h = []
        # res = 0
        # find = lambda x: x[0]
        # insert = lambda x: (x[0], -x[1])
        # for s, e, t in rides:
        #     add = (e - s + t)
        #     if h:
        #         idx = bisect_right(h, s, key=find)
        #         if idx == 0 and h[0][0] > s:
        #             preMax = 0
        #         else:
        #             preMax = h[idx - 1][1]
        #         tmp = h[idx][1] + preMax
        #
        #     else:
        #         tmp = add
        #     res = max(res, tmp)
        #     insort(h, (e, tmp), key=insert)
        #
        # return res

        rides.sort(key=lambda x: (x[1], x[0]))
        re = [e for _, e, _ in rides]
        dp = [0] * (len(rides) + 1)

        for i, (s, e, t) in enumerate(rides):
            add = (e - s + t)
            idx = bisect_right(re, s)
            dp[i + 1] = max(dp[i], dp[idx] + add)
        return dp[-1]


if __name__ == '__main__':
    s = Solution()
    print(s.maxTaxiEarnings(n=5, rides=[[2, 5, 4], [1, 5, 1]]))
    print(s.maxTaxiEarnings(n=20, rides=[[1, 6, 1], [3, 10, 2], [10, 12, 3], [11, 12, 2], [12, 15, 2], [13, 18, 1]]))
    print(s.maxTaxiEarnings(10,
                            [[9, 10, 2], [4, 5, 6], [6, 8, 1], [1, 5, 5], [4, 9, 5], [1, 6, 5], [4, 8, 3], [4, 7, 10],
                             [1, 9, 8], [2, 3, 5]]))
    # arr = SortedList(key=lambda x: (-x[0], x[1]))
    # arr.add((10, 2))
    # arr.add((10, 3))
    # arr.add((9, 2))
    # print(arr)
    # arr = []
    # insort(arr, (3, 10), key=lambda x: (x[0], -x[1]))
    # insort(arr, (3, 9), key=lambda x: (x[0], -x[1]))
    # insort(arr, (4, 20), key=lambda x: (x[0], -x[1]))
    # print(arr)
    # print(bisect_left([], 2, key=lambda x: x[0]))
