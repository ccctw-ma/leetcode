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


# todo: suffixArray to solve this problem
class Solution:
    def lastSubstring(self, s: str) -> str:
        buc = defaultdict(list)
        for i, c in enumerate(s):
            buc[c].append(i)
        maxC = sorted(buc.keys())[-1]
        idxs = buc[maxC]


if __name__ == '__main__':
    s = Solution()
    arr = [1, 2, 3, 4, 5, 6, 7]
    a, b, *rest = arr
    print(rest)
    print(gcd(*arr))
