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
    def isValid(self, s: str) -> bool:
        while s:
            t = s.replace("abc", "")
            if len(t) == len(s):
                return False
            s = t
        return True

if __name__ == '__main__':
    s = Solution()
    print(s.isValid(
        "abccba"))
    print(s.isValid("aabcbabcc"))
