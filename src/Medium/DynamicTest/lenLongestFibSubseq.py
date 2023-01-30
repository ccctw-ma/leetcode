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
    def lenLongestFibSubseq(self, arr: List[int]) -> int:
        buc = set(arr)
        n = len(arr)
        res = 0
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                base = 2
                a, b = arr[i], arr[j]
                while a + b in buc:
                    a, b = b, a + b
                    base += 1
                res = max(res, base)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.lenLongestFibSubseq(arr=[1, 2, 3, 4, 5, 6, 7, 8]))
    print(s.lenLongestFibSubseq(arr=[1, 3, 7, 11, 12, 14, 18]))
    print(s.lenLongestFibSubseq([2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50]))
