import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        n = len(encoded)
        x = 0
        for num in range(1, n + 2):
            x ^= num
        res = [x] + [0] * n
        for i in range(1, n, 2):
            res[0] ^= encoded[i]
        for i in range(1, n + 1):
            res[i] = res[i - 1] ^ encoded[i - 1]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.decode(encoded=[6, 5, 4, 6]))
