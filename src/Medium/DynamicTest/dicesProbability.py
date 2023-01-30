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
    def dicesProbability(self, n: int) -> List[float]:
        if n == 1:
            return [1 / 6] * 6
        dp = self.dicesProbability(n - 1)
        ndp = [0] * (len(dp) + 5)
        for i, p in enumerate(dp):
            for add in range(1, 7):
                ndp[i + add - 1] += (p / 6)
        return ndp


if __name__ == '__main__':
    s = Solution()
    print(s.dicesProbability(11))
