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
    def minSubarray(self, nums: List[int], p: int) -> int:
        nums = list(map(lambda x: x % p, nums))
        preSum = [0] + list(accumulate(nums))
        if preSum[-1] % p == 0:
            return 0

        buc = {0: -1}
        n = len(nums)
        res = n
        for i in range(n):
            right = (preSum[n] - preSum[i + 1]) % p
            target = (p - right) % p
            if target in buc:
                res = min(res, i - buc[target])
            curSum = preSum[i + 1] % p
            buc[curSum] = i
        return res if res != n else -1


if __name__ == '__main__':
    s = Solution()
    # print(s.minSubarray(nums=[3, 1, 4, 2], p=6))
    # print(s.minSubarray(nums=[6, 3, 5, 2], p=9))
    # print(s.minSubarray(nums=[1, 2, 3], p=7))
    print(s.minSubarray([8, 32, 31, 18, 34, 20, 21, 13, 1, 27, 23, 22, 11, 15, 30, 4, 2]
                        , 148))
