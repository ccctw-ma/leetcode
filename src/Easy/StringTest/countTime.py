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
    def countTime(self, time: str) -> int:
        n = time.count("?")
        res = 0
        chars = '0123456789'
        for i in range(10 ** n):
            t = time
            for j in range(n):
                t = t.replace('?', chars[i % 10], 1)
                i //= 10
            if "00" <= t[:2] <= "23" and "00" <= t[3:] <= "59":
                res += 1
        return res





if __name__ == '__main__':
    s = Solution()
    # print(s.countTime(time = "?5:00"))
    # print(s.countTime(time = "0?:0?"))
    print(s.countTime(time = "??:??"))