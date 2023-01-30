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
    def strongPasswordCheckerII(self, password: str) -> bool:
        if len(password) < 8:
            return False
        a, b, c, d = 0, 0, 0, 0
        pre = ""
        for ch in password:
            if ch.islower():
                a += 1
            elif ch.isupper():
                b += 1
            elif ch.isdigit():
                c += 1
            elif ch in "!@#$%^&*()-+":
                d += 1
            elif ch == pre:
                return False
            pre = ch
        return a >= 1 and b >= 1 and c >= 1 and d >= 1


if __name__ == '__main__':
    s = Solution()
