import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def canBeValid(self, s: str, locked: str) -> bool:
        if len(s) & 1:
            return False
        arr = []
        for c, lock in zip(s, locked):
            if lock == '1':
                arr.append(c)
            else:
                arr.append('#')
        left, tmp, right = 0, 0, 0
        for c in arr:
            if c == '(':
                left += 1
            elif c == '#':
                tmp += 1
            else:
                right += 1
            if left + tmp < right:
                return False
        left, tmp, right = 0, 0, 0
        for i in range(len(s) - 1, - 1, -1):
            c = arr[i]
            if c == '(':
                left += 1
            elif c == '#':
                tmp += 1
            else:
                right += 1
            if right + tmp < left:
                return False
        return True



if __name__ == '__main__':
    s = Solution()
    print(s.canBeValid(s="))()))", locked="010100"))
    print(s.canBeValid(s="()()", locked="0000"))
    print(s.canBeValid(s="((((((((", locked="00110011"))
    print(s.canBeValid(s=")", locked="0"))
    print(s.canBeValid(s="))))(((", locked="0101011"))
    print(s.canBeValid("((()(()()))()((()()))))()((()(()",
                       "10111100100101001110100010001001"))
    print(s.canBeValid("())()))()(()(((())(()()))))((((()())(())",
                       "1011101100010001001011000000110010100101"))
