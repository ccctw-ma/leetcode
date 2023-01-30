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
    def secondsToRemoveOccurrences(self, s: str) -> int:
        pre = 0
        count = 0
        res = 0
        for i, c in enumerate(s):
            if c == '1' and i > 0:
                if s[i - 1] == '0':
                    cur = pre + count
                else:
                    cur = pre + 1 if pre != 0 else 0
                res = max(res, cur)
                pre = cur
                count = 0
            elif c == '0':
                count += 1

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.secondsToRemoveOccurrences(s="0110101"))
    print(s.secondsToRemoveOccurrences("0100111"))
    print(s.secondsToRemoveOccurrences("11100"))
    print(s.secondsToRemoveOccurrences("1011010101"))
    print(s.secondsToRemoveOccurrences("1001111111110001011001110000000110101"))
    print("1100101".replace("01", "10"))