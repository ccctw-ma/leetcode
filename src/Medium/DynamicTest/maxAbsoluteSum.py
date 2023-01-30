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
    def maxAbsoluteSum(self, nums: List[int]) -> int:
        preSum = [0] + list(accumulate(nums))
        print(preSum)
        maxR, minR = 0, 0
        maxP, minP = 0, 0
        for c in nums:
            maxP = max(maxP + c, 0)
            maxR = max(maxR, maxP)
            minP = min(minP + c, 0)
            minR = min(minR, minP)
        return max(maxR, -minR)


if __name__ == '__main__':
    s = Solution()
    print(s.maxAbsoluteSum(nums=[2, -5, 1, -4, 3, -2]))
