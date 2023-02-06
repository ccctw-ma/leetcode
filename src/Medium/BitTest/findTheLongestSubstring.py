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
    def findTheLongestSubstring(self, s: str) -> int:
        mask = 0
        res = 0
        vowMap = {}
        buc = {
            'a': 1,
            'e': 2,
            'i': 3,
            'o': 4,
            'u': 5
        }
        vowMap[0] = -1
        for i, c in enumerate(s):
            if c in buc:
                mask ^= 1 << buc[c]
            if mask in vowMap:
                res = max(res, i - vowMap[mask])
            else:
                vowMap[mask] = i
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.findTheLongestSubstring(s="eleetminicoworoep"))
