import time
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

    # 暴力可以过
    def minimizeTheDifference(self, mat: List[List[int]], target: int) -> int:
        s = set(mat[0])
        m, n = len(mat), len(mat[0])
        for i in range(1, m):
            temp = set()
            for e in mat[i]:
                temp |= {x + e for x in s}
            s = temp
        return min(abs(x - target) for x in s)

    def minimizeTheDifference2(self, mat: List[List[int]], target: int) -> int:
        m, n = len(mat), len(mat[0])
        cur = 1  ## 初始状态只有一个和：0
        for i in range(m):
            tmp = 0
            for j in range(n):
                tmp |= cur << mat[i][j]  ## 左移n位，代表所有和都加n
            cur = tmp
        res = 0
        left = right = 1 << target
        while True:
            if cur & left or cur & right:
                return res
            else:
                left <<= 1
                right >>= 1
                res += 1


if __name__ == '__main__':


    s = Solution()
    print(s.minimizeTheDifference2(mat=[[1, 2, 3], [4, 5, 6], [7, 8, 9]], target=13))
    print(s.minimizeTheDifference(
        [[10, 3, 7, 7, 9, 6, 9, 8, 9, 5], [1, 1, 6, 8, 6, 7, 7, 9, 3, 9], [3, 4, 4, 1, 3, 6, 3, 3, 9, 9],
         [6, 9, 9, 3, 8, 7, 9, 6, 10, 6]]
        , 5))
