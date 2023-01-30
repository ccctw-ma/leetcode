import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


# 参见 887 掉落k枚鸡蛋
class Solution:
    def twoEggDrop(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[0] = 0
        dp[1] = 1
        for i in range(2, n + 1):
            dp[i] = 10000
            for j in range(1, i):
                dp[i] = min(dp[i], max(j, dp[i - j] + 1))
        return dp[n]


if __name__ == '__main__':
    s = Solution()
    print(s.twoEggDrop(100))
