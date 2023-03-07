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
    def minTaps(self, n: int, ranges: List[int]) -> int:
        MAX = 10 ** 9
        dp = [MAX] * (n + 1)
        for i, ran in enumerate(ranges):
            if ran == 0:
                continue
            pre = dp[i - ran] if i - ran > 0 else 0
            for j in range(max(0, i - ran), min(n + 1, i + ran + 1)):
                dp[j] = min(dp[j], pre + 1)
        print(dp)
        return dp[-1] if dp[-1] != MAX else -1


if __name__ == '__main__':
    s = Solution()
    print(s.minTaps(n=5, ranges=[3, 4, 1, 1, 0, 0]))
    print(s.minTaps(n=3, ranges=[0, 0, 0, 0]))
    print(s.minTaps(35, [1, 0, 4, 0, 4, 1, 4, 3, 1, 1, 1, 2, 1, 4, 0, 3, 0, 3, 0, 3, 0, 5, 3, 0, 0, 1, 2, 1, 2, 4, 3, 0,
                         1, 0, 5, 2]))
    print(s.minTaps(8,
                    [4, 0, 0, 0, 4, 0, 0, 0, 4]))
