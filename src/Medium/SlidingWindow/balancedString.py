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
    def balancedString(self, s: str) -> int:
        n = len(s)
        tar = n // 4
        buc = defaultdict(int)
        for c in s:
            buc[c] += 1
        if all(x == tar for x in buc.values()):
            return 0

        def check(table):
            return all(x <= tar for x in table.values())

        res = n
        i, j = 0, 0
        while i < n:
            while i < n and not check(buc):
                buc[s[i]] -= 1
                i += 1
            while j < i and check(buc):
                buc[s[j]] += 1
                j += 1

            res = min(res, i - j + 1)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.balancedString(s="QQQW"))
    print(s.balancedString(s="QWER"))
    print(s.balancedString(s="QQWE"))
    print(s.balancedString(s="WQWQWRQWRQRWQEQWRQWRQWRQWEQWEQWRQWEQWR"))
