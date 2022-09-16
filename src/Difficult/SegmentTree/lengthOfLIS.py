from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def lengthOfLIS(self, nums: List[int], k: int) -> int:
        u = max(nums)
        f = [0] * (u * 4)

        def query(index, l, r, s, e):
            if l == s and e == r:
                return f[index]
            m = (l + r) // 2
            if e <= m:
                return query(index * 2 + 1, l, m, s, e)
            elif s > m:
                return query(index * 2 + 2, m + 1, r, s, e)
            return max(query(index * 2 + 1, l, m, s, m), query(index * 2 + 2, m + 1, r, m + 1, e))

        def update(index, l, r, x, y):
            if y >= f[index]:
                f[index] = y
            if l == r:
                return
            m = (l + r) // 2
            if x <= m:
                update(index * 2 + 1, l, m, x, y)
            else:
                update(index * 2 + 2, m + 1, r, x, y)

        for num in nums:
            if num == 1:
                update(0, 0, u, 1, 1)
            else:
                max_pre = 1 + query(0, 0, u, max(1, num - k), num - 1)
                update(0, 0, u, num, max_pre)
        return f[0]


if __name__ == '__main__':
    s = Solution()
    print(s.lengthOfLIS(
        [4, 2, 1, 4, 3, 4, 5, 8, 15]
        , 3))
