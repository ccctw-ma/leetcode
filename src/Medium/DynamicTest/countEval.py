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
    def countEval(self, s: str, result: int) -> int:
        @cache
        def f(i, j):
            if i == j:
                if s[i] == '0':
                    return 1, 0
                else:
                    return 0, 1
            if i + 2 == j:
                tmp = int(eval(s[i: j + 1]))
                if tmp:
                    return 0, 1
                else:
                    return 1, 0

            zero, one = 0, 0
            for k in range(i + 1, j, 2):
                lz, lo = f(i, k - 1)
                rz, ro = f(k + 1, j)
                c = s[k]
                if c == '&':
                    zero += lz * rz + lz * ro + lo * rz
                    one += lo * ro
                elif c == '|':
                    zero += lz * rz
                    one += lo * rz + lo * ro + lz * ro
                else:
                    zero += lz * rz + lo * ro
                    one += lz * ro + lo * rz
            return zero, one

        zero, one = f(0, len(s) - 1)
        return one if result else zero


if __name__ == '__main__':
    s = Solution()
    print(s.countEval(s="1^0|0|1", result=0))
    print(s.countEval(s="0&0&0&1^1|0", result=1))
    print(s.countEval("1|1|1^1^0^0^1|0&0^0&1&1|0|1^0|0"
                      , 0))
    # print(1 ^ 0)