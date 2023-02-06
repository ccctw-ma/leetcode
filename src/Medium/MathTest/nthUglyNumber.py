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
    # 容斥定理
    def nthUglyNumber(self, n: int, a: int, b: int, c: int) -> int:
        lcm_ab = lcm(a, b)
        lcm_bc = lcm(b, c)
        lcm_ac = lcm(a, c)
        lcm_abc = lcm(a, b, c)
        l, r = 0, min(a, b, c) * n + 1
        while l < r:
            mid = (l + r - 1) // 2
            # import
            cnt = mid // a + mid // b + mid // c - mid // lcm_ab - mid // lcm_ac - mid // lcm_bc + mid // lcm_abc  #
            if cnt >= n:
                r = mid
            else:
                l = mid + 1
        return l


if __name__ == '__main__':
    s = Solution()
    print(s.nthUglyNumber(n=5, a=2, b=11, c=13))
    print(s.nthUglyNumber(n=3, a=2, b=3, c=5))
    print(s.nthUglyNumber(n=4, a=2, b=3, c=4))
    print(s.nthUglyNumber(n=1000000000, a=2, b=217983653, c=336916467))
