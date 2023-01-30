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
    def findMinStep(self, board: str, hand: str) -> int:
        def clean(s: str) -> str:
            n = 1
            while n:
                s, n = re.subn(r'(.)\1{2,}', '', s)
            return s

        @cache
        def f(b: str, h: str) -> int:
            if not b:
                return 0
            res = 6
            for i, j in product(range(len(b) + 1), range(len(h))):
                if j > 0 and h[j] == h[j - 1]:
                    continue
                if i > 0 and b[i - 1] == h[j]:
                    continue
                choose = False
                if i < len(b) and b[i] == h[j]:
                    choose = True
                if 0 < i < len(b) and b[i - 1] == b[i] and b[i - 1] != h[j]:
                    choose = True
                if choose:
                    res = min(res, f(clean(b[:i] + h[j] + b[i:]), h[:j] + h[j + 1:]) + 1)
            return res

        ans = f(board, "".join(sorted(hand)))
        return ans if ans <= 5 else -1


if __name__ == '__main__':
    s = Solution()
    print(s.findMinStep(board="WWRRBBWW", hand="WRBRW"))
    print(s.findMinStep(board="WRRBBW", hand="RB"))
    print(s.findMinStep(board="G", hand="GGGGG"))
    print(s.findMinStep(board="RBYYBBRRB", hand="YRBGB"))
    print(s.findMinStep("RRWWRRBBRR"
                        , "WB"))
