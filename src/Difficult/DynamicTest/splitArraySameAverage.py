from functools import reduce, lru_cache, cache
from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from typing import List, Optional


class Solution:
    def splitArraySameAverage(self, nums: List[int]) -> bool:
        dp = [[0] * 2 for _ in range(2)]

        n = len(nums)
        aver = sum(nums) / n
        vis = [0] * n
        nums.sort()

        @cache
        def tb(sa, na, sb, nb, c):
            if c == n:
                print(sa, na, sb, nb, c)
                return sa * nb == sb * na != 0
            for i in range(n):
                if vis[i]:
                    continue
                vis[i] = 1
                if sa <= na * aver and tb(sa + nums[i], na + 1, sb, nb, c + 1):
                    return True
                if sb <= nb * aver and tb(sa, na, sb + nums[i], nb + 1, c + 1):
                    return True
                vis[i] = 0
            return False

        return tb(0, 0, 0, 0, 0)

    def splitArraySameAverage2(self, nums: List[int]) -> bool:

        n, s = len(nums), sum(nums)
        mul = n // gcd(n, s)
        A = [num * mul - mul * s // n for num in nums]
        st = 0
        for x in sorted(A[:-1], reverse=True):
            st |= (st | 1) << x if x >= 0 else st >> (-x)
            if st & 1:
                return True
        return False

    def splitArraySameAverage3(self, nums: List[int]) -> bool:
        l = len(nums)
        if l == 1: return False
        if l == 2: return True if nums[0] == nums[1] else False
        l = len(nums)
        s = sum(nums)
        if s / l in nums: return True
        num = [nums[i] * l - s for i in range(l)]
        ld = l // 2
        left = {num[0]}
        for i in range(1, ld):
            left = {z + num[i] for z in left} | left | {num[i]}
        if 0 in left: return True

        right = {num[-1]}
        for i in range(ld + 1, l - 1):
            right = {z + num[i] for z in right} | right | {num[i]}
        if 0 in right: return True

        sleft = sum(num[i] for i in range(ld))
        sright = sum(num[i] for i in range(ld + 1, l - 1))
        return any(-ha in right and (ha, -ha) != (sleft, sright) for ha in left)


if __name__ == '__main__':
    s = Solution()
    print(s.splitArraySameAverage(nums=[1, 2, 3, 4, 5, 6, 7, 8]))
    print(s.splitArraySameAverage(nums=[3, 1]))
    print(s.splitArraySameAverage(
        [60, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
         30, 30]))
    print(s.splitArraySameAverage3([84, 44, 32, 42, 26, 26, 87, 65, 12, 95, 68, 41, 6, 72, 8, 15, 66, 55, 71, 79, 6]))
