import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def minLength(self, s: str) -> int:
        while "AB" in s or "CD" in s:
            s = s.replace("AB", "").replace("CD", "")
        return len(s)

    def makeSmallestPalindrome(self, s: str) -> str:
        s = list(s)
        l, r = 0, len(s) - 1
        while l < r:
            if s[l] != s[r]:
                s[l] = s[r] = min(s[l], s[r])
            l += 1
            r -= 1
        return "".join(s)

    def punishmentNumber(self, n: int) -> int:

        arr = [1,
               9,
               10,
               36,
               45,
               55,
               82,
               91,
               99,
               100,
               235,
               297,
               369,
               370,
               379,
               414,
               657,
               675,
               703,
               756,
               792,
               909,
               918,
               945,
               964,
               990,
               991,
               999,
               1000]

        buc = set(arr)

        @cache
        def fn(s: str, tar):
            if not s:
                return False
            if int(s) == tar:
                return True
            if tar < 0:
                return False
            for i in range(1, n):
                l = s[:i]
                r = s[i:]
                if fn(r, tar - int(l)):
                    return True
            return False

        res = 0
        for i in range(1, n + 1):
            if i in buc:
                res += (i ** 2)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.punishmentNumber(n=1000))
