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
    def strangePrinter(self, s: str) -> int:
        @cache
        def back(l, r):
            if l >= r:
                return r - l + 1
            if s[l] == s[r]:
                return back(l, r - 1)
            return min(
                [1 + back(l + 1, r)] + [back(l, i - 1) + back(i + 1, r) for i in range(l + 1, r) if s[i] == s[l]])

        return back(0, len(s) - 1)


if __name__ == '__main__':
    s = Solution()
    print(s.strangePrinter(s="aaabbb"))
    print(s.strangePrinter(s="aba"))
    print(s.strangePrinter("tbgtgb"))
    print(s.strangePrinter("bababaddcbcaabdbdddcccdd"))
