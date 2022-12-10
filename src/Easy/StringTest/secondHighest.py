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
    def secondHighest(self, s: str) -> int:
        buc = set([int(c) for c in s if c.isdigit()])
        return sorted(buc, reverse=True)[1] if len(buc) > 1 else -1


if __name__ == '__main__':
    s = Solution()
