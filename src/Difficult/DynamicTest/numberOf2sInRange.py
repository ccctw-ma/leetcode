from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def numberOf2sInRange(self, n: int) -> int:
        s = list(map(int, str(n)))

        @cache
        def f(index, isLimit, count):
            if index == len(s):
                return count
            up = s[index] if isLimit else 9
            res = 0
            for i in range(up + 1):
                res += f(index + 1, isLimit and i == up, count + int(i == 2))
            return res

        return f(0, True, 0)


if __name__ == '__main__':
    s = Solution()
    print(s.numberOf2sInRange())