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
    def numberOfWays(self, s: str) -> int:
        arr = [int(c) for c in s]
        preSum = [0] + list(accumulate(arr))
        res = 0
        n = len(s)
        for i in range(1, n - 1):
            c = arr[i]
            ls, rs = preSum[i], preSum[n] - preSum[i + 1]
            lc, rc = i, n - i - 1
            if c:
                res += (lc - ls) * (rc - rs)
            else:
                res += ls * rs

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.numberOfWays(s="001101"))
