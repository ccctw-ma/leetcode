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
    def longestWPI(self, hours: List[int]) -> int:
        arr = [1 if x > 8 else -1 for x in hours]
        preSum = [0] + list(accumulate(arr))
        buc = {}
        n = len(hours) + 1
        res = 0
        for i in range(n - 1, -1, -1):
            cur = preSum[i]
            for k in buc.keys():
                if k > cur:
                    res = max(res, buc[k] - i)
            if cur not in buc:
                buc[cur] = i
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.longestWPI(hours=[9, 9, 6, 0, 6, 6, 9]))
    print(s.longestWPI([6, 6, 6]))
