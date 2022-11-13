from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        res, n = 0, len(nums)
        pmin, pmax, ps = -1, -1, -1
        for i, num in enumerate(nums):
            if num == minK:
                pmin = i
            if num == maxK:
                pmax = i
            if num < minK or num > maxK:
                ps = i
            res += max(0, min(pmin, pmax) - ps)
        return res


if __name__ == '__main__':
    s = Solution()
