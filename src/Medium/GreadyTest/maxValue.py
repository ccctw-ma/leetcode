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
    def maxValue(self, n: int, index: int, maxSum: int) -> int:

        def check(x: int) -> bool:
            leftSpan = index
            leftMax = x - 1
            if leftSpan <= leftMax:
                leftRes = (leftMax + leftMax - leftSpan + 1) * leftSpan // 2
            else:
                leftRes = (1 + leftMax) * leftMax // 2 + leftSpan - leftMax
            rightSpan = n - index - 1
            rightMax = x - 1
            if rightSpan <= rightMax:
                rightRes = (rightMax + rightMax - rightSpan + 1) * rightSpan // 2
            else:
                rightRes = (1 + rightMax) * rightMax // 2 + rightSpan - rightMax
            return (x + leftRes + rightRes) <= maxSum

        l, r = 1, maxSum
        while l < r:
            mid = (l + r + 1) // 2
            if check(mid):
                l = mid
            else:
                r = mid - 1

        return l


if __name__ == '__main__':
    s = Solution()
    print(s.maxValue(n=4, index=2, maxSum=6))
    print(s.maxValue(n=6, index=1, maxSum=10))
    print(s.maxValue(3, 2, 18))