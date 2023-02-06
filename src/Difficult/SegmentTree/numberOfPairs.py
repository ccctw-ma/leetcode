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
    def numberOfPairs(self, nums1: List[int], nums2: List[int], diff: int) -> int:
        arr = []
        for a, b in zip(nums1, nums2):
            arr.append(a - b)
        res = 0
        n = len(arr)
        stl = SortedList([arr[0]])
        for i in range(1, n):
            tar = arr[i] + diff
            idx = stl.bisect_right(tar)
            res += idx
            stl.add(arr[i])
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.numberOfPairs(nums1=[3, 2, 5], nums2=[2, 2, 1], diff=1))
    print(s.numberOfPairs(nums1=[3, -1], nums2=[-2, 2], diff=-1))
