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

from collections import deque


class Solution:

    def getEstime(self, map: List[List[int]], ax, ay, bx, by) -> int:
        m, n = len(map), len(map[0])

        def fn():
            q = deque([(ax, ay)])
            vis = set()
            step = 0
            while q:
                for _ in range(len(q)):
                    cx, cy = q.popleft()
                    if cx == bx and cy == by:
                        return step
                    for dx, dy in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
                        nx, ny = cx + dx, cy + dy
                        if 0 <= nx < m and 0 <= ny < n and (nx, ny) not in vis:
                            q.append((nx, ny))
                            vis.add((nx, ny))
                step += 1
            return -1

        ans = fn()
        return ans if ans == -1 else (ans + 1) // 2


if __name__ == '__main__':
    s = Solution()
    print(s.getEstime([[1, 1], [1, 1]], 0, 0, 1, 1))
    print(
        s.getEstime([[1, 1, 1, 0, 1],
                     [1, 0, 1, 1, 1],
                     [1, 1, 1, 0, 1],
                     [0, 0, 1, 1, 1],
                     [1, 0, 0, 0, 1]
                     ],
                    0, 0, 4, 4))
