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
    def decodeAtIndex(self, s: str, k: int) -> str:
        # n = len(s)
        # i = 0
        #
        # stk = []
        # while i < n:
        #     j = i
        #     while j < n and s[j].isalpha():
        #         j += 1
        #     ss = s[i:j]
        #     num = 1
        #     while j < n and s[j].isdigit():
        #         num *= int(s[j])
        #         j += 1
        #     if stk:
        #         stk.append((ss, stk[-1][1] * stk[-1][2] + len(ss), num))
        #     else:
        #         stk.append((ss, len(ss), num))
        #
        #     i = j
        # # print(stk)
        # k -= 1
        # while stk:
        #     ss, ll, times = stk.pop()
        #     k = k % ll
        #     if len(stk) == 0:
        #         return ss[k]
        #     else:
        #         ps, pl, pt = stk[-1]
        #         a = pl * pt
        #         if k >= a:
        #             return ss[k - a]

        size = 0
        # Find size = length of decoded string
        for c in s:
            if c.isdigit():
                size *= int(c)
            else:
                size += 1

        for c in reversed(s):
            k %= size
            if k == 0 and c.isalpha():
                return c

            if c.isdigit():
                size /= int(c)
            else:
                size -= 1




if __name__ == '__main__':
    s = Solution()
    # print(s.decodeAtIndex(s="leet2code3", k=10))
    # print(s.decodeAtIndex("ha22", 2))
    print(s.decodeAtIndex("a2b3c4d5e6f7g8h9"
                          , 3))
