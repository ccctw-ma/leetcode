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
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: x[1])
        res = 0
        end = -inf
        for s, e in intervals:
            if s >= end:
                res += 1
                end = e
        return len(intervals) - res


if __name__ == '__main__':
    s = Solution()
    print(s.eraseOverlapIntervals([[0, 2], [1, 3], [2, 4], [3, 5], [4, 6]]))
    print(s.eraseOverlapIntervals(intervals=[[1, 2], [1, 2], [1, 2]]))
    print(s.eraseOverlapIntervals([[1, 100], [11, 22], [1, 11], [2, 12]]))
