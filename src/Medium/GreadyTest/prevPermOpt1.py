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
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        n = len(arr)
        i = n - 1
        pre = 10 ** 5
        while i >= 0 and arr[i] <= pre:
            pre = arr[i]
            i -= 1
        if i == -1:
            return arr
        idx = bisect_left(arr, arr[i], i) - 1
        tar = arr[idx]
        while idx >= 0 and arr[idx] == tar:
            idx -= 1
        idx += 1

        arr[i], arr[idx] = arr[idx], arr[i]
        return arr


if __name__ == '__main__':
    s = Solution()
    print(s.prevPermOpt1([3, 1, 1, 3]))
