import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
# from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def replaceNonCoprimes(self, nums: List[int]) -> List[int]:

        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        def lcm(a, b):
            return (a * b) // gcd(a, b)


        stk = [nums[0]]
        for i in range(1, len(nums)):
            c = nums[i]
            while stk and gcd(stk[-1], c) > 1:
                c = lcm(stk[-1], c)
                stk.pop()
            stk.append(c)
        return stk


if __name__ == '__main__':
    s = Solution()
    print(s.replaceNonCoprimes(nums=[6, 4, 3, 2, 7, 6, 2]))
    print(s.replaceNonCoprimes([287, 41, 49, 287, 899, 23, 23, 20677, 5, 825]))


