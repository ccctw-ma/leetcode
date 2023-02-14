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
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        if k == 1:
            return max(a * b for a, b in zip(nums1, nums2))
        n = len(nums1)
        pairs = [(a, b) for a, b in zip(nums1, nums2)]
        pairs.sort(key=lambda x: x[1])
        # print(pairs)
        dp = [0] * n
        curS = 0
        h = []
        for i in range(n - 1, -1, -1):
            if len(h) < k - 1:
                heappush(h, pairs[i][0])
                curS += pairs[i][0]
            else:
                tmp = pairs[i][0]
                if tmp > h[0]:
                    curS -= heappop(h)
                    curS += tmp
                    heappush(h, tmp)

            dp[i] = curS

        ans = 0
        for i in range(n - k + 1):
            ans = max(ans, pairs[i][1] * (pairs[i][0] + dp[i + 1]))
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.maxScore(nums1=[1, 3, 3, 2], nums2=[2, 1, 3, 4], k=3
                     ))
    print(s.maxScore(nums1=[4, 2, 3, 1, 1], nums2=[7, 5, 10, 9, 6], k=1))
