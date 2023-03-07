import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def maxScoreWords(self, words: List[str], letters: List[str], score: List[int]) -> int:
        letterCounter = Counter(letters)
        arr = []
        for word in words:
            tc = Counter(word)
            if all(tc[c] <= letterCounter[c] for c in tc):
                arr.append(word)
        n = len(arr)
        MAX = 1 << n
        res = 0
        for i in range(MAX):
            tc = Counter()
            for j in range(n):
                if i & (1 << j):
                    tc += Counter(arr[j])
            # 计算得分
            tmp = 0
            f = True
            for c in tc:
                if tc[c] <= letterCounter[c]:
                    tmp += tc[c] * (score[ord(c) - ord('a')])
                else:
                    f = False
                    break
            if f:
                res = max(res, tmp)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxScoreWords(words=["dog", "cat", "dad", "good", "zep"],
                          letters=["a", "a", "c", "d", "d", "d", "g", "o", "o"],
                          score=[1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))
    print(s.maxScoreWords(words=["xxxz", "ax", "bx", "cx"], letters=["z", "a", "b", "c", "x", "x", "x"],
                          score=[4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10]))
