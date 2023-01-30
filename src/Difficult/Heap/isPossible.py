import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def isPossible(self, target: List[int]) -> bool:
        n = len(target)
        if n == 1:
            return target[0] == 1
        s = sum(target)
        arr = [-x for x in target]
        heapify(arr)
        while s > n:
            x = heappop(arr)
            x = -x
            remain = s - x
            if remain >= x:
                return False
            y = remain if x % remain == 0 else x % remain
            s = y + remain
            heappush(arr, -y)
        return s == n


if __name__ == '__main__':
    s = Solution()
    print(s.isPossible([8, 5]))
    print(s.isPossible(target=[9, 3, 5]))
    print(s.isPossible(target=[1, 1, 1, 2]))
    print(s.isPossible([9, 9, 9]))
    print(s.isPossible([1, 1000000000]))
