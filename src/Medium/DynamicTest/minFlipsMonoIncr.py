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
    def minFlipsMonoIncr(self, s: str) -> int:
        zero, one = 0, 0
        for c in s:
            if c == '1':
                one = min(one, zero)
                zero = zero + 1
            else:
                one = one + 1

        return min(zero, one)


if __name__ == '__main__':
    s = Solution()
    print(s.minFlipsMonoIncr("00110"))
    print(s.minFlipsMonoIncr("010110"))
    print(s.minFlipsMonoIncr("00011000"))