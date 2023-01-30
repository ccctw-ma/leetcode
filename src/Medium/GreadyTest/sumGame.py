import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    # bob can win hardly
    def sumGame(self, num: str) -> bool:
        n = len(num)
        ls, lq = 0, 0
        for i in range(n // 2):
            if num[i] == '?':
                lq += 1
            else:
                ls += int(num[i])
        rs, rq = 0, 0
        for i in range(n // 2, n):
            if num[i] == '?':
                rq += 1
            else:
                rs += int(num[i])
        return (lq + rq) % 2 == 1 or ls - rs != 9 * (rq - lq) / 2


if __name__ == '__main__':
    s = Solution()
    print(s.sumGame(num="5023"))
