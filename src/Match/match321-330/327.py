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
    def maxKelements(self, nums: List[int], k: int) -> int:
        res = 0
        h = []
        for c in nums:
            heappush(h, -c)
        for _ in range(k):
            p = -heappop(h)
            res += p
            heappush(h, -ceil(p / 3))
        return res

    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = Counter(word1)
        cnt2 = Counter(word2)
        k1, k2 = len(cnt1.keys()), len(cnt2.keys())
        if k1 < k2:
            k1, k2 = k2, k1
            cnt1, cnt2 = cnt2, cnt1

        if k1 == k2:
            if k1 == k2 == 1 and ((len(word1) == 1 and len(word2) > 1) or (len(word1) > 1 and len(word2) == 1)):
                return False
            return True
        if k1 == k2 + 1:
            for c in cnt1.keys():
                for d in cnt2.keys():
                    if cnt1[c] > 1 and cnt2[c] == 0 and cnt2[d] > 1 and cnt1[d] >= 1:
                        return True
                    if cnt1[c] == 1 and cnt2[c] == 0 and cnt2[d] == 1 and cnt1[d] >= 1:
                        return True
        if k1 == k2 + 2:
            for c in cnt1.keys():
                for d in cnt2.keys():
                    if cnt1[c] == 1 and cnt2[c] == 0 and cnt2[d] > 1 and cnt1[d] >= 1:
                        return True
        return False

    def findCrossingTime(self, n: int, k: int, time: List[List[int]]) -> int:
        pass



if __name__ == '__main__':
    s = Solution()
    print(s.maxKelements(nums=[1, 10, 3, 3, 3], k=3))
    print(s.isItPossible("a"
                         , "bb"))
    print(s.isItPossible("aa", "ab"))
    print(s.isItPossible("abcc", "ab"))
    print(s.isItPossible("aab", "bccd"))
