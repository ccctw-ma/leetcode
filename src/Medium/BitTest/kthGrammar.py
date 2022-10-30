from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:

    @cache
    def kthGrammar(self, n: int, k: int) -> int:
        if n == 1:
            return 0
        if n == 2:
            return [0, 1][k - 1]
        if n == 3:
            return [0, 1, 1, 0][k - 1]

        pre = self.kthGrammar(n - 1, max(0, k - 1) // 2 + 1)
        if pre == 1:
            return 1 if k & 1 else 0
        return 0 if k % 2 else 1


if __name__ == '__main__':
    s = Solution()
    print(s.kthGrammar(n=2, k=2))
    print(s.kthGrammar(4
                       , 1))
