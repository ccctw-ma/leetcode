from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class FreqStack:

    def __init__(self):
        self.feq = defaultdict(int)
        self.list = defaultdict(list)
        self.maxC = 0

    def push(self, val: int) -> None:
        self.feq[val] += 1
        self.list[self.feq[val]].append(val)
        self.maxC = max(self.maxC, self.feq[val])

    def pop(self) -> int:
        res = self.list[self.maxC].pop()
        if len(self.list[self.maxC]) == 0:
            self.maxC -= 1
        self.feq[res] -= 1
        return res

if __name__ == '__main__':
    obj = FreqStack()
    obj.push(1)
