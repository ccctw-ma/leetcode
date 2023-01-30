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
    def racecar(self, target: int) -> int:

        q = deque([(0, 1)])
        vis = set()
        step = 0
        while q:
            n = len(q)
            for _ in range(n):
                x, p = q.popleft()
                if (x, p) in vis:
                    continue
                vis.add((x, p))

                if x == target:
                    return step
                if x + p == target:
                    return step + 1
                q.append((x + p, p * 2))
                if (p > 0 and x + p > target) or (p < 0 and x + p < target):
                    q.append((x, -1 if p > 0 else 1))

            step += 1


if __name__ == '__main__':
    s = Solution()
    print(s.racecar(target=10000))
