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
    def maxCompatibilitySum(self, students: List[List[int]], mentors: List[List[int]]) -> int:
        m, n = len(students), len(students[0])
        g = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(m):
                cnt = 0
                for k in range(n):
                    cnt += (1 - students[i][k] ^ mentors[j][k])
                g[i][j] = cnt

        MAX = 1 << m - 1

        # @cache
        # def f(stu, men):
        #     if stu == MAX and men == MAX:
        #         return 0
        #
        #     res = 0
        #     for i in range(m):
        #         if stu >> i & 1:
        #             continue
        #         for j in range(m):
        #             if men >> j & 1:
        #                 continue
        #             res = max(res, g[i][j] + f(stu | (1 << i), men | (1 << j)))
        #     return res
        #
        # return f(0, 0)

        f = [0] * (1 << m)
        for mask in range(1, 1 << m):
            c = bin(mask).count("1")
            for i in range(m):
                # 判断 mask 的第 i 位是否为 1
                if mask & (1 << i):
                    f[mask] = max(f[mask], f[mask ^ (1 << i)] + g[c - 1][i])

        return f[(1 << m) - 1]

if __name__ == '__main__':
    s = Solution()
    # print(s.maxCompatibilitySum(students=[[1, 1, 0], [1, 0, 1], [0, 0, 1]], mentors=[[1, 0, 0], [0, 0, 1], [1, 1, 0]]))
    # print(s.maxCompatibilitySum(students=[[0, 0], [0, 0], [0, 0]], mentors=[[1, 1], [1, 1], [1, 1]]))
    print(s.maxCompatibilitySum([[0, 1, 0, 1, 1, 1], [1, 0, 0, 1, 0, 1], [1, 0, 1, 1, 0, 0]],
                                [[1, 0, 0, 0, 0, 1], [0, 1, 0, 0, 1, 1], [0, 1, 0, 0, 1, 1]]))

    print(list(permutations([1,2,3,4,1,2,3,4], 2)))