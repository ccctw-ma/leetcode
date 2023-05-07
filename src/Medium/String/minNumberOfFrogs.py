import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        croak = {
            'r': 'c',
            'o': 'r',
            'a': 'o',
            'k': 'a'
        }

        def check(buc, c):
            if c == 'c':
                return True
            return buc[c] <= buc[croak[c]]

        res = 0
        buc = defaultdict(int)
        for c in croakOfFrogs:
            buc[c] += 1
            if not check(buc, c):
                return -1
            if c == 'k':
                for t in 'croak':
                    buc[t] -=1
            res = max(res, buc['c'])
        a= max(buc.values())
        return -1 if a != 0 else res


if __name__ == '__main__':
    s = Solution()
    print(s.minNumberOfFrogs(croakOfFrogs="croakcroak"))
    print(s.minNumberOfFrogs(croakOfFrogs = "crcoakroak"))