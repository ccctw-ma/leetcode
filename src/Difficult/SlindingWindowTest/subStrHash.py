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

    #  正着走不行 就反着走
    def subStrHash(self, s: str, power: int, modulo: int, k: int, hashValue: int) -> str:
        n = len(s)

        @cache
        def val(c):
            return ord(c) - 96

        multi = pow(power, k - 1, modulo)

        cur = 0
        pos = n
        for i in range(n - 1, n - k - 1, -1):
            cur = (cur * power + val(s[i])) % modulo
        if cur == hashValue:
            pos = n - k
        for i in range(n - k - 1, -1, -1):
            add = val(s[i])
            delete = val(s[i + k]) * multi % modulo
            cur = ((cur - delete + modulo) % modulo * power + add) % modulo
            if cur == hashValue:
                pos = i
        return s[pos:pos + k]


if __name__ == '__main__':
    s = Solution()
    print(s.subStrHash(s="leetcode", power=7, modulo=20, k=2, hashValue=0))
    print(s.subStrHash(s="fbxzaad", power=31, modulo=100, k=3, hashValue=32))
