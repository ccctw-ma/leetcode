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
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        if x == 0:
            return 0

        visit = set()
        for f in forbidden:
            visit.add((f, -1))
            visit.add((f, 1))
        far = max(max(forbidden) + a + b, x + b)

        step = 1
        stack = [(0, 0)]
        while stack:
            print(stack)
            nex = []
            for i, d in stack:
                nodes = [(i + a, 1)]
                if d != -1:
                    nodes.append((i - b, -1))
                for j, e in nodes:
                    if 0 <= j <= far and (j, e) not in visit:
                        if j == x:
                            return step
                        visit.add((j, e))
                        nex.append((j, e))
            stack = nex
            step += 1
        return -1


if __name__ == '__main__':
    s = Solution()
    # print(s.minimumJumps(forbidden=[8, 3, 16, 6, 12, 20], a=15, b=13, x=11))
    # print(s.minimumJumps(forbidden=[14, 4, 18, 1, 15], a=3, b=15, x=9))
    # print(s.minimumJumps(forbidden=[1, 6, 2, 14, 5, 17, 4], a=16, b=9, x=7))
    # print(s.minimumJumps([18, 13, 3, 9, 8, 14]
    #                      , 3
    #                      , 8
    #                      , 6))
    print(s.minimumJumps(
        [162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73, 200, 127, 30, 124, 193, 84, 184, 36, 103, 149, 153, 9,
         54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59, 71, 48, 177, 82, 35, 14, 176, 16, 108, 111, 6, 168, 31, 134,
         164, 136, 72, 98]
        , 29
        , 98
        , 80))
