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
    def maxEvents(self, events: List[List[int]]) -> int:
        events.sort()
        heap = []
        ans = eveIdx = 0
        day = events[0][0]
        size = len(events)
        while heap or eveIdx < size:
            while eveIdx < size and events[eveIdx][0] == day:
                heappush(heap, events[eveIdx][1])
                eveIdx += 1
            while heap:
                if heappop(heap) >= day:
                    ans += 1
                    break
            day += 1
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.maxEvents(events=[[1, 2], [2, 3], [3, 4]]))
    # print(s.maxEvents(events=[[1, 2], [2, 3], [3, 4], [1, 2]]))
    print(s.maxEvents([[1, 2], [1, 2], [3, 3], [1, 5], [1, 5]]))
    print(s.maxEvents([[1, 5], [1, 5], [1, 5], [2, 3], [2, 3]]))


