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
    def closetTarget(self, words: List[str], target: str, startIndex: int) -> int:
        if target not in words:
            return -1
        res = 0
        n = len(words)
        l, r = startIndex, startIndex
        ll, rr = 0, 0
        while words[l] != target and words[r] != target:
            l = (l - 1 + n) % n
            r = (r + 1 + n) % n
            ll += 1
            rr += 1
        return min(ll, rr)

    def takeCharacters(self, s: str, k: int) -> int:
        if k == 0:
            return 0
        if 3 * k > len(s):
            return -1
        buc = Counter(s)
        if buc['a'] < k or buc['b'] < k or buc['c'] < k:
            return -1

        cnt = defaultdict(int)

        def check():
            nonlocal cnt
            return cnt['a'] >= k and cnt['b'] >= k and cnt['c'] >= k

        n = len(s)
        s = s + s
        i = n
        while i < 2 * n:
            cnt[s[i]] += 1
            if check():
                break
            i += 1
        # 开始滑动窗口
        span = (i - n + 1)
        l, r = n - 1, i
        while l >= 0:
            cnt[s[l]] += 1
            while r >= n - 1 and check():
                # print(s[l: r + 1])
                span = min(span, r - l + 1)
                cnt[s[r]] -= 1
                r -= 1
            l -= 1
        return span

    def maximumTastiness(self, price: List[int], k: int) -> int:
        price.sort()

        l, r = 0, price[-1]
        while l < r:
            mid = (l + r + 1) // 2
            cur = price[0]
            cnt = 1
            for p in price:
                if p >= cur + mid:
                    cur = p
                    cnt += 1

            if cnt < k:
                r = mid - 1
            else:
                l = mid
        return l

    def countPartitions(self, nums: List[int], k: int) -> int:
        mod = 10 ** 9 + 7
        s = sum(nums)
        n = len(nums)
        tot = pow(2, n, mod)
        if s < 2 * k:
            return 0


if __name__ == '__main__':
    s = Solution()
    # print(s.takeCharacters(s="aabaaaacaabc", k=2))
    # print(s.takeCharacters(s="a", k=1))
    # print(s.takeCharacters("ccbabcc"
    #                        , 1))
    # print(s.takeCharacters("cbbac"
    #                        , 1))
    # print(s.maximumTastiness(price=[13, 5, 1, 8, 21, 2], k=3))
    print(s.countPartitions(nums=[1, 2, 3, 4], k=4))
