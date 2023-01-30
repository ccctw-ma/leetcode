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
    def minSideJumps(self, obstacles: List[int]) -> int:
        a, b, c = 1, 0, 1
        MAX = 10 ** 12
        n = len(obstacles)
        for i in range(1, n):
            o = obstacles[i]
            if o == 1:
                a = MAX
            elif o == 2:
                b = MAX
            elif o == 3:
                c = MAX

                a = min(a, b + 1, c + 1)
                b = min(b, a + 1, c + 1)
                c = min(c, a + 1, b + 1)

        return min(a, b, c)


if __name__ == '__main__':
    s = Solution()
    # print(s.minSideJumps(obstacles=[0, 1, 2, 3, 0]))
    # print(s.minSideJumps(obstacles=[0, 1, 1, 3, 3, 0]))
    # print(s.minSideJumps(obstacles=[0, 2, 1, 0, 3, 0]))
    print(s.minSideJumps([0, 0, 3, 1, 0, 1, 0, 2, 3, 1, 0]))
