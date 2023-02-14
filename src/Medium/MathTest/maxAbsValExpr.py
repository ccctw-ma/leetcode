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
    def maxAbsValExpr(self, arr1: List[int], arr2: List[int]) -> int:
        A, B, C, D = [], [], [], []
        for i in range(len(arr1)):
            x, y = arr1[i], arr2[i]
            A.append(x + y + i)
            B.append(x + y - i)
            C.append(x - y + i)
            D.append(x - y - i)

        a = max(A) - min(A)
        b = max(B) - min(B)
        c = max(C) - min(C)
        d = max(D) - min(D)
        return max(a, b, c, d)


if __name__ == '__main__':
    s = Solution()
    print(s.maxAbsValExpr(arr1=[1, 2, 3, 4], arr2=[-1, 4, 5, 6]))
