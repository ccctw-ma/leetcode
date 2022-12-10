from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def sumSubseqWidths(self, nums: List[int]) -> int:
        res = 0
        n = len(nums)
        nums.sort()
        MOD = 10 ** 9 + 7
        for i, num in enumerate(nums):
            left = pow(2, i, MOD) * num
            right = pow(2, n - i - 1, MOD) * num
            res = (res + left - right) % (10 ** 9 + 7)
        return res


if __name__ == '__main__':
    s = Solution()
