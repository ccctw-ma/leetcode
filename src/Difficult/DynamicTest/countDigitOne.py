from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def countDigitOne(self, n: int) -> int:
        s = list(map(int, str(n)))

        @cache
        def f(index, count, isLimit, isNum):
            if index == len(s):
                return count
            res = 0
            if not isNum:
                res += f(index + 1, 0, False, False)
            up = s[index] if isLimit else 9
            for i in range(0 if isNum else 1, up + 1):
                res += f(index + 1, count, isLimit and i == up, True)
            return res

        return f(0, 0, True, False)

    def countDigitOne2(self, n: int) -> int:
        s = str(n)

        @cache
        def dfs(idx, limit, count):
            if idx == len(s):
                return count
            up = int(s[idx]) if limit else 9
            res = 0
            for nxt in range(up + 1):
                res += dfs(idx + 1, limit and nxt == int(s[idx]), count + int(nxt == 1))
            return res

        return dfs(0, True, 0)


if __name__ == '__main__':
    s = Solution()
    print(s.countDigitOne2(2))
