from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def mostFrequentEven(self, nums: List[int]) -> int:
        buc = Counter(nums)
        even, count = -1, -1
        for k, v in buc.items():
            if k & 1 == 0:
                if v > count:
                    count = v
                    even = k
                if v == count and k < even:
                    even = k

        return -1 if even == -1 else even

    def partitionString(self, s: str) -> int:
        count = 0
        i = 0
        while i < len(s):
            buc = set()
            j = i
            while j < len(s):
                if s[j] not in buc:
                    buc.add(s[j])
                    j += 1
                else:
                    break
            count += 1
            i = j
        return count

    def minGroups(self, intervals: List[List[int]]) -> int:
        n = len(intervals)
        intervals.sort()
        h = []
        for s, e in intervals:
            if len(h) == 0 or s <= h[0]:
                heapq.heappush(h, e)
            else:
                heapq.heappop(h)
                heapq.heappush(h, e)
        return len(h)

    def lengthOfLIS(self, nums: List[int], k: int) -> int:
        u = max(nums)
        mx = [0] * (4 * u)

        def modify(o: int, l: int, r: int, i: int, val: int) -> None:
            if l == r:
                mx[o] = val
                return
            m = (l + r) // 2
            if i <= m:
                modify(o * 2, l, m, i, val)
            else:
                modify(o * 2 + 1, m + 1, r, i, val)
            mx[o] = max(mx[o * 2], mx[o * 2 + 1])

        # 返回区间 [L,R] 内的最大值
        def query(o: int, l: int, r: int, L: int, R: int) -> int:  # L 和 R 在整个递归过程中均不变，将其大写，视作常量
            if L <= l and r <= R: return mx[o]
            res = 0
            m = (l + r) // 2
            if L <= m: res = query(o * 2, l, m, L, R)
            if R > m: res = max(res, query(o * 2 + 1, m + 1, r, L, R))
            return res

        for x in nums:
            if x == 1:
                modify(1, 1, u, 1, 1)
            else:
                res = 1 + query(1, 1, u, max(x - k, 1), x - 1)
                modify(1, 1, u, x, res)
        return mx[1]


if __name__ == '__main__':
    s = Solution()
