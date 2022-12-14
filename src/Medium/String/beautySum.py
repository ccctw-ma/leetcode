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
    def beautySum(self, s: str) -> int:
        res = 0
        n = len(s)
        for i in range(n):
            cnt = Counter()
            mx = 0
            for j in range(i, n):
                cnt[s[j]] += 1
                mx = max(mx, cnt[s[j]])
                res += mx - min(cnt.values())
        return res


if __name__ == '__main__':
    s = Solution()
