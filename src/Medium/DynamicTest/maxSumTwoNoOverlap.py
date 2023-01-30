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
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        if firstLen + secondLen == n:
            return sum(nums)
        rs = [0] * n
        rps = sum(nums[n - secondLen:])
        rmax = rps
        for i in range(n - secondLen - 1, -1, -1):
            rs[i] = rmax
            rps += nums[i]
            rps -= nums[i + secondLen]
            rmax = max(rmax, rps)
        # print(rs)

        ls = [0] * n
        lps = sum(nums[0:secondLen])
        lmax = lps
        for i in range(secondLen, n):
            ls[i] = lmax
            lps += nums[i]
            lps -= nums[i - secondLen]
            lmax = max(lmax, lps)
        # print(ls)

        tmp = sum(nums[:firstLen])
        res = tmp + rs[firstLen - 1]
        for i in range(firstLen, n):
            tmp += nums[i]
            tmp -= nums[i - firstLen]
            res = max(res, tmp + rs[i], tmp + ls[i - firstLen + 1])

        return res

    # very nice
    def maxSumTwoNoOverlap2(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        for i in range(1, n):
            nums[i] += nums[i - 1]
        res = nums[firstLen + secondLen - 1]
        Lmax = nums[firstLen - 1]
        Mmax = nums[secondLen - 1]
        for i in range(firstLen + secondLen, n):
            Lmax = max(Lmax, nums[i - secondLen] - nums[i - firstLen - secondLen])
            Mmax = max(Mmax, nums[i - firstLen] - nums[i - firstLen - secondLen])
            res = max(res, max(Lmax + nums[i] - nums[i - secondLen], Mmax + nums[i] - nums[i - firstLen]))

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxSumTwoNoOverlap([0, 6, 5, 2, 2, 5, 1, 9, 4], 1, 2))
