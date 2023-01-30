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
    def isScramble(self, s1: str, s2: str) -> bool:


        @cache
        def f(x, y):
            if Counter(x) != Counter(y):
                return False
            if x == y:
                return True
            n = len(x)
            for i in range(1, n):
                xl, yl, yel = x[:i], y[:i], y[n - i:]
                xr, yr, ysr = x[i:], y[i:], y[: n - i]
                if f(xl, yl) and f(xr, yr):
                    return True
                if f(xl, yel) and f(xr, ysr):
                    return True
            return False
        ans = f(s1, s2)
        f.cache_clear()
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.isScramble(s1="great", s2="rgeat"))
    print(s.isScramble(s1="abcde", s2="caebd"))
    print(s.isScramble("abcdbdacbdac"
                       , "bdacabcdbdac"))
    print(s.isScramble("abcdefghijklmn"
                       , "efghijklmncadb"))
    print(factorial(5))