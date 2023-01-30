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
    def minimumOperations(self, leaves: str) -> int:
        n = len(leaves)
        dp = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            if leaves[i] == 'y':
                dp[i] = dp[i + 1] + 1
            else:
                dp[i] = dp[i + 1]
        rr, ry = 0, 0
        s = leaves[:2]
        if s == 'rr':
            rr, ry = 0, 1
        if s == 'ry':
            rr, ry = 1, 0
        if s == 'yr':
            rr, ry = 1, 2
        if s == 'yy':
            rr, ry = 2, 1
        res = n
        for i in range(2, n):
            res = min(res, ry + dp[i])
            c = leaves[i]
            nrr, nry = 0, 0
            if c == 'r':
                nrr = rr
                nry = min(rr + 1, ry + 1)
            if c == 'y':
                nrr = rr + 1
                nry = min(rr, ry)
            rr, ry = nrr, nry

        return res


if __name__ == '__main__':
    s = Solution()

    print(s.minimumOperations(leaves="rrryyyrryyyrr"))
    print(s.minimumOperations(leaves="ryr"))
    print(s.minimumOperations("yry"))
    print(s.minimumOperations("rryy"))