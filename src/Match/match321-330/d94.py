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
    def captureForts(self, forts: List[int]) -> int:
        res = 0
        n = len(forts)
        for i in range(n):
            if forts[i] == 1:
                l, r = i - 1, i + 1
                cnt = 0
                while l >= 0 and forts[l] == 0:
                    cnt += 1
                    l -= 1
                if l >= 0 and forts[l] == -1:
                    res = max(res, i - l - 1)
                cnt = 0
                while r < n and forts[r] == 0:
                    cnt += 1
                    r += 1
                if r < n and forts[r] == -1:
                    res = max(res, r - i - 1)
        return res

    def topStudents(self, positive_feedback: List[str], negative_feedback: List[str], report: List[str],
                    student_id: List[int], k: int) -> List[int]:
        ps = set(positive_feedback)
        ns = set(negative_feedback)
        arr = []
        for r, id in zip(report, student_id):
            p = 0
            for word in r.split(" "):
                if word in ps:
                    p += 3
                elif word in ns:
                    p -= 1
            arr.append((p, id))
        arr.sort(key=lambda x: (-x[0], x[1]))
        res = []
        for i in range(k):
            res.append(arr[i][1])
        return res

    def minimizeSet(self, divisor1: int, divisor2: int, uniqueCnt1: int, uniqueCnt2: int) -> int:
        lc = lcm(divisor1, divisor2)
        l, r = 0, 2 * 10 ** 9
        while l < r:
            mid = (l + r) // 2
            s1 = mid - (mid // divisor1)
            s2 = mid - (mid // divisor2)
            s = mid - (mid // lc)
            if uniqueCnt1 <= s1 and uniqueCnt2 <= s2 and uniqueCnt1 + uniqueCnt2 <= s:
                r = mid
            else:
                l = mid + 1
        return l

    def countAnagrams(self, s: str) -> int:
        mod = 10 ** 9 + 7
        arr = s.split(" ")
        n = len(arr)

        @cache
        def fn(word):
            cnt = Counter(word)
            n = len(word)
            count = 1
            for k, v in cnt.items():
                count *= comb(n, v)
                n -= v

            return count

        res = fn(arr[0])
        for i in range(1, n):
            res = (res * fn(arr[i])) % mod
        return res % mod


if __name__ == '__main__':
    s = Solution()
    # print(s.captureForts([0, -1, -1, 0, -1]))
    # print(s.topStudents(positive_feedback=["smart", "brilliant", "studious"], negative_feedback=["not"],
    #                     report=["this student is studious", "the student is smart"], student_id=[1, 2], k=2))
    # print(s.minimizeSet(divisor1=2, divisor2=7, uniqueCnt1=1, uniqueCnt2=3))
    print(s.countAnagrams(s="too hot"))
    print(s.countAnagrams("rsrybprxlendseni"))
    print(sum([1, True]))