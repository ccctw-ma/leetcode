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
    def countHomogenous(self, s: str) -> int:
        mod = 10 ** 9 + 7
        res = 0
        i = 0
        n = len(s)
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            span = j - i
            res += span * (span + 1) // 2
            i = j
        return res % mod


if __name__ == '__main__':
    s = Solution()
    print(s.countHomogenous(s="abbcccaa"))
