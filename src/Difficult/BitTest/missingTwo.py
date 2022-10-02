from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional


class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        n = len(nums) + 2
        xorSum = 0
        for num in (nums + [i + 1 for i in range(n)]):
            xorSum ^= num
        xorSum = xorSum & (- xorSum)
        r1, r2 = 0, 0
        for num in (nums + [i + 1 for i in range(n)]):
            if num & xorSum:
                r1 ^= num
            else:
                r2 ^= num
        return [r1, r2]


if __name__ == '__main__':
    s = Solution()
    print(s.missingTwo([2, 3]))
    print(s.missingTwo([1]))
