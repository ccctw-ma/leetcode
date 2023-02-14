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
    def maxProfit(self, inventory: List[int], orders: int) -> int:
        mod = 10 ** 9 + 7
        inventory.sort(reverse=True)

        def check(x):
            s = 0
            for e in inventory:
                if e < x:
                    break
                s += (e - x + 1)
            return s >= orders

        l, r = 0, inventory[0]
        while l < r:
            m = (l + r + 1) // 2
            if check(m):
                l = m
            else:
                r = m - 1

        tar = l
        res = 0
        count = 0
        for e in inventory:
            if e < tar:
                break
            c = e - tar + 1
            count += c
            res = (res + (c * (2 * e - c + 1) // 2)) % mod
        if count > orders:
            res = (res - (count - orders) * tar + mod) % mod
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxProfit(inventory=[2, 8, 4, 10, 6], orders=20))
    print(s.maxProfit(inventory=[2, 5], orders=4))
    print(s.maxProfit(inventory=[3, 5], orders=6))
    print(s.maxProfit(inventory=[1000000000], orders=1000000000))
    print(s.maxProfit([497978859, 167261111, 483575207, 591815159]
                      , 836556809))
