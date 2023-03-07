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
    def countTriplets(self, nums: List[int]) -> int:
        buc = defaultdict(int)
        for a, b in product(nums, nums):
            buc[a & b] += 1

        res = 0
        for c in nums:
            for k in buc.keys():
                if (c & k) == 0:
                    res += buc[k]
        return res


    def countTriplets2(self, nums: List[int]) -> int:
        cnt = [0] * (1 << 16)
        for x in nums:
            for y in nums:
                cnt[x & y] += 1
        ans = 0
        for m in nums:
            m ^= 0xffff
            s = m
            while True:  # 枚举 m 的子集（包括空集）
                ans += cnt[s]
                s = (s - 1) & m
                if s == m: break
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.countTriplets(nums=[2, 1, 3]))
