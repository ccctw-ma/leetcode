from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def countSpecialNumbers(self, n: int) -> int:
        s = list(map(int, str(n)))

        @cache
        def f(index, mask, isLimit, isNum):
            if index == len(s):
                return int(isNum)
            res = 0
            if not isNum:
                res += f(index + 1, mask, False, False)
            up = s[index] if isLimit else 9
            for i in range(0 if isNum else 1, up + 1):
                if mask & (1 << i) == 0:
                    res += f(index + 1, mask | (1 << i), isLimit and i == up, True)

        return f(0, 0, True, False)


if __name__ == '__main__':
    s = Solution()
    print(perm(4, 2))
    print(comb(4, 2))