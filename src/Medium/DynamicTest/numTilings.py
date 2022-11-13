from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def numTilings(self, n: int) -> int:
        if n == 1:
            return 1
        if n == 2:
            return 2
        if n == 3:
            return 5
        a, b, c = 1, 2, 5
        for i in range(4, n + 1):
            t = c * 2 + a
            a, b, c = b, c, t
        return c % (10 ** 9 + 7)

if __name__ == '__main__':
    s = Solution()

    print(s.numTilings(n=3))
