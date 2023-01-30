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
    def findSquare(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        dp = [[[0, 0] for _ in range(n)] for _ in range(m)]
        res = [-1, -1, -1]
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 1:
                    continue

                left = dp[i][j - 1] if j > 0 else [0, 0]
                top = dp[i - 1][j] if i > 0 else [0, 0]
                dp[i][j] = [1 + left[0], 1 + top[1]]
                maxSpan = min(dp[i][j])
                tmpSpan = 1
                for span in range(1, maxSpan):
                    if dp[i][j - span][1] >= (span + 1) and dp[i - span][j][0] >= (span + 1):
                        tmpSpan = span + 1
                if tmpSpan > res[2]:
                    res = [i - tmpSpan + 1, j - tmpSpan + 1, tmpSpan]
        return res if res != [-1, -1, -1] else []


if __name__ == '__main__':
    s = Solution()
    print(s.findSquare([
        [1, 0, 1, 0],
        [0, 0, 0, 1],
        [0, 0, 0, 1],
        [0, 0, 0, 0]
    ]))
    print(s.findSquare([
        [0, 1, 1],
        [1, 0, 1],
        [1, 1, 0]
    ]))
