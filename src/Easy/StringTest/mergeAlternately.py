from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        return reduce(lambda a, b: a + b[0] + b[1], zip_longest(word1, word2, fillvalue=''), '')


if __name__ == '__main__':
    s = Solution()
    print(s.mergeAlternately(word1="ab", word2="pqrs"))
