from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict


class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stk = []
        for c in s:
            if not stk or not (c == ')' and stk[-1] == '('):
                stk.append(c)
                continue
            stk.pop()

        return len(stk)


if __name__ == '__main__':
    s = Solution()
    print(s.minAddToMakeValid(s="((("))
