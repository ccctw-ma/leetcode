import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def queryString(self, s: str, n: int) -> bool:
        res = set()
        for i in range(len(s)):
            base = 0
            for j in range(i, min(len(s), i + 31)):
                base *= 2
                base += int(s[j])
                if 1 <= base <= n:
                    res.add(base)
        return len(res) == n


if __name__ == '__main__':
    s = Solution()
    print(s.queryString(s = "0110", n = 4))