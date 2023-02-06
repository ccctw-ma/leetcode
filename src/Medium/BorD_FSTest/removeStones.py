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
    def removeStones(self, stones: List[List[int]]) -> int:
        n = len(stones)
        f = list(range(n))

        def find(x):
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return f[x]

        def union(x, y):
            f[find(x)] = find(y)

        xs, ys = {}, {}
        for i in range(n):
            cx, cy = stones[i]
            if cx in xs:
                union(i, xs[cx])
            else:
                xs[cx] = i
            if cy in ys:
                union(i, ys[cy])
            else:
                ys[cy] = i

        return n - sum(find(x) == x for x in range(n))


if __name__ == '__main__':
    s = Solution()
    print(s.removeStones(stones=[[0, 0], [0, 1], [1, 0], [1, 2], [2, 1], [2, 2]]))
    print(s.removeStones(stones=[[0, 0], [0, 2], [1, 1], [2, 0], [2, 2]]))
    print(s.removeStones([[0, 0]]))
    print(s.removeStones([[0, 1], [1, 2], [1, 3], [3, 3], [2, 3], [0, 2]]))
