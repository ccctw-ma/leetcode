import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        m = 1 << n
        vis = [False] * m
        idxs = [0] * m
        vis[start] = True
        idxs[0] = start
        res = []

        def tb(i, pre):
            nonlocal res
            if res:
                return
            if i == m - 1:
                for j in range(n):
                    next = pre ^ (1 << j)
                    if not vis[next]:
                        final = pre ^ next
                        if final & (final - 1) == 0:
                            idxs[i] = next
                            res = idxs.copy()
                    return

            for j in range(n):
                next = pre ^ (1 << j)
                if not vis[next]:
                    idxs[i] = next
                    vis[next] = True
                    tb(i + 1, next)
                    vis[next] = False
                    idxs[i] = 0

        tb(1, start)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.circularPermutation(n=3, start=2))
    print(s.circularPermutation(n=2, start=3))
