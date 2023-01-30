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
    def maximumWhiteTiles(self, tiles: List[List[int]], carpetLen: int) -> int:
        tiles.sort()
        stk = []
        maxL = 1
        for tile in tiles:
            if stk and stk[-1][1] == tile[0] - 1:
                a, b, _ = stk.pop()
                stk.append([a, tile[1], tile[1] - a + 1])
            else:
                stk.append(tile + [tile[1] - tile[0] + 1])
            maxL = max(maxL, stk[-1][2])

        print(stk, maxL)
        if maxL >= carpetLen:
            return carpetLen
        n = len(stk)
        l, r = 0, 0
        ss = 0
        res = maxL
        # q = deque()

        while l < n:
            if r == n:
                break
            s, e = stk[l][0], stk[l][0] + carpetLen - 1
            while r < n and stk[r][1] <= e:
                ss += stk[r][2]
                r += 1
            append = 0
            if r < n and stk[r][0] <= e:
                append = e - stk[r][0] + 1
            res = max(res, ss + append)
            # 队首出队
            ss -= stk[l][2]
            l += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maximumWhiteTiles(tiles=[[1, 5], [10, 11], [12, 18], [20, 25], [30, 32]], carpetLen=10))
    print(s.maximumWhiteTiles(tiles=[[10, 11], [1, 1]], carpetLen=2))
