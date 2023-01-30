import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt, log
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def leastMinutes(self, n: int) -> int:
        cur = 1
        res = 0
        while n > 0:
            if n <= cur:
                n -= cur
                res += 1
            else:
                res += 1
                cur *= 2
        return res


if __name__ == '__main__':
    s = Solution()
