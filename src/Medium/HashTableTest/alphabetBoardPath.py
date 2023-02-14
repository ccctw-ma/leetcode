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
    def alphabetBoardPath(self, target: str) -> str:
        board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]
        buc = defaultdict(Tuple)
        for i, b in enumerate(board):
            for j, c in enumerate(b):
                buc[c] = (i, j)
        cur = (0, 0)

        def f(x, y):
            hor = y[0] - x[0]
            ver = y[1] - x[1]
            a = 'D' * hor if hor >= 0 else 'U' * (-hor)
            b = 'R' * ver if ver >= 0 else 'L' * (-ver)
            if y == (5, 0):
                return b + a
            if x == (5, 0):
                return a + b
            return a + b

        res = ""
        for i, c in enumerate(target):
            tP = buc[c]
            res += f(cur, tP)
            res += '!'
            cur = tP
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.alphabetBoardPath(target="leet"
                              ))
    print(s.alphabetBoardPath("zdz"))
