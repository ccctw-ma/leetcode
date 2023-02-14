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
    def minimumReplacement(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n - 2, -1, -1):
            if nums[i] > nums[i + 1]:
                count = (nums[i] - 1) // nums[i + 1]
                ans += count
                nums[i] //= (count + 1)
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.minimumReplacement([3, 9, 3]))
    # print(s.minimumReplacement([3, 9, 12, 10]))
    # print(s.minimumReplacement(
    #     [368, 112, 2, 282, 349, 127, 36, 98, 371, 79, 309, 221, 175, 262, 224, 215, 230, 250, 84, 269, 384, 328, 118,
    #      97, 17, 105, 342, 344, 242, 160, 394, 17, 120, 335, 76, 101, 260, 244, 378, 375, 164, 190, 320, 376, 197, 398,
    #      353, 138, 362, 38, 54, 172, 3, 300, 264, 165, 251, 24, 312, 355, 237, 314, 397, 101, 117, 268, 36, 165, 373,
    #      269, 351, 67, 263, 332, 296, 13, 118, 294, 159, 137, 82, 288, 250, 131, 354, 261, 192, 111, 16, 139, 261, 295,
    #      112, 121, 234, 335, 256, 303, 328, 242, 260, 346, 22, 277, 179, 223]))
    print(s.minimumReplacement([260, 346, 22]))
