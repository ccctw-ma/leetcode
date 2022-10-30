from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        res = - 1
        s = set(nums)
        for c in s:
            if c > 0 and -c in s:
                res = max(c, res)
        return res

    def countDistinctIntegers(self, nums: List[int]) -> int:
        s = set(nums)
        ss = set()
        for c in s:
            cc = str(c)
            cc = cc[::-1]
            cc = int(cc)
            ss.add(cc)
        return len(s | ss)

    def reverse(self, k):
        return int(str(k)[::-1])

    def sumOfNumberAndReverse(self, num: int) -> bool:
        for i in range(num // 2, num + 1):
            if i + self.reverse(i) == num:
                return True
        return False

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
    print(s.countSubarrays(nums=[1, 1, 1, 1], minK=1, maxK=1
                           ))
