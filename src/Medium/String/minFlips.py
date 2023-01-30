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
    # 就是比较绕
    def minFlips(self, s: str) -> int:
        n = len(s)
        flag = bool(n % 2)
        zo, ze, oo, oe = 0, 0, 0, 0
        for i, c in enumerate(s):
            if c == '1':
                oo += i % 2
                oe += int(i % 2 == 0)
            else:
                zo += i % 2
                ze += int(i % 2 == 0)
        res = min(zo + oe, ze + oo)
        for i in range(n - 1):
            c = s[i]
            if not flag:
                zo, ze = ze, zo
                oo, oe = oe, oo
            else:
                if c == '1':
                    zo, ze = ze, zo
                    oo, oe = oe - 1, oo + 1
                else:
                    zo, ze = ze - 1, zo + 1
                    oo, oe = oe, oo
            res = min(res, zo + oe, ze + oo)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.minFlips(s="111000"))
    print(s.minFlips(s="010"))
