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
    def maxPoints(self, points: List[List[int]]) -> int:
        m, n = len(points), len(points[0])
        dp = points[0]
        for i in range(1, m):
            tmp = [0] * n
            leftMax = 0
            for j in range(n):
                leftMax = max(leftMax, dp[j] + j)
                tmp[j] = max(tmp[j], leftMax + points[i][j] - j)
            rightMax = -inf
            for j in range(n - 1, -1, -1):
                rightMax = max(rightMax, dp[j] - j)
                tmp[j] = max(tmp[j], rightMax + points[i][j] + j)
            dp = tmp
            print(dp)
        return max(dp)


if __name__ == '__main__':
    s = Solution()
    print(s.maxPoints(points=[[1, 2, 3], [1, 5, 1], [3, 1, 1]]))
    print(s.maxPoints([[0, 3, 0, 4, 2], [5, 4, 2, 4, 1], [5, 0, 0, 5, 1], [2, 0, 1, 0, 3]]))
    print(s.maxPoints([[4, 1, 0, 4, 0], [1, 0, 4, 0, 5], [1, 3, 0, 4, 1], [4, 4, 0, 4, 0]]))
