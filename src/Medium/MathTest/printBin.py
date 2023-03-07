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
    def printBin(self, num: float) -> str:
        cnt = 0
        res = '0.'
        while num > 1e-6:
            num *= 2
            if num >= 1.0:
                res += '1'
                num -= 1
            else:
                res += '0'
            cnt += 1
            if cnt == 32:
                break
        if cnt == 32:
            return "ERROR"
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.printBin(0.625))
    print(s.printBin(0.3))
    print(s.printBin(0.893))