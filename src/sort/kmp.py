from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def getNext(self, word: str) -> List[int]:
        m = len(word)
        arr = [-1] * m
        for i in range(1, m):
            j = arr[i - 1]
            while j != -1 and word[j + 1] != word[i]:
                j = arr[j]
            if word[j + 1] == word[i]:
                arr[i] = j + 1

        return arr


if __name__ == '__main__':
    s = Solution()
    print(s.getNext("abcabcabcd"))
