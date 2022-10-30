from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def robotWithString(self, s: str) -> str:
        n = len(s)
        f = [''] * (n + 1)
        f[n] = chr(ord('z') + 10)
        for i in range(n - 1, - 1, -1):
            f[i] = min(f[i + 1], s[i])
        stk = []
        res = ''
        for i, c in enumerate(s):
            stk.append(c)
            while stk and stk[-1] <= f[i + 1]:
                res += stk.pop()
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.robotWithString(s="bdda"))
