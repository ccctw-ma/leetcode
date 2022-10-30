from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class StockSpanner:

    def __init__(self):
        self.stk = []

    def next(self, price: int) -> int:
        count = 0
        while self.stk and price >= self.stk[-1][0]:
            count += self.stk[-1][1]
            self.stk.pop()
        count += 1
        self.stk.append((price, count))
        return self.stk[-1][1]


if __name__ == '__main__':
    s = StockSpanner()

