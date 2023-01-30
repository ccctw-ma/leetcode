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
    def fullBloomFlowers2(self, flowers: List[List[int]], persons: List[int]) -> List[int]:
        arr = []
        for s, e in flowers:
            arr.append((s, -1, 1))
            arr.append((e, 1, -1))
        for i, t in enumerate(persons):
            arr.append((t, 0, i))
        arr.sort()
        cnt = 0
        res = [0] * len(persons)
        for t, s, i in arr:
            if s == -1:
                cnt += 1
            elif s == 0:
                res[i] = cnt
            else:
                cnt -= 1

        return res

    def fullBloomFlowers(self, flowers: List[List[int]], persons: List[int]) -> List[int]:
        starts = sorted(s for s, _ in flowers)
        ends = sorted(e for _, e in flowers)
        arr = []
        for p in persons:
            a = bisect_right(starts, p)
            b = bisect_left(ends, p)
            arr.append(a - b)
            print(a, b)
        return arr

if __name__ == '__main__':
    s = Solution()
    # print(s.fullBloomFlowers(flowers=[[1, 6], [3, 7], [9, 12], [4, 13]], persons=[2, 3, 7, 11]))
    # print(s.fullBloomFlowers(flowers=[[1, 10], [3, 3]], persons=[3, 3, 2]))
    arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    print(bisect_left(arr, 20))

