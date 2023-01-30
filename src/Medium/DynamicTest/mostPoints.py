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
    def mostPoints(self, questions: List[List[int]]) -> int:
        n = len(questions)
        dp = [0] * (n + 1)
        for i in range(n - 1, - 1, -1):
            p, b = questions[i]
            if (i + b + 1) <= n:
                preMax = dp[i + b + 1]
            else:
                preMax = 0
            dp[i] = max(dp[i + 1], p + preMax)

        return dp[0]


if __name__ == '__main__':
    s = Solution()
    print(s.mostPoints(questions=[[3, 2], [4, 3], [4, 4], [2, 5]]))
    print(s.mostPoints(questions=[[1, 1], [2, 2], [3, 3], [4, 4], [5, 5]]))
