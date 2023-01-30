import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def largestMerge(self, word1: str, word2: str) -> str:

        m, n = len(word1), len(word2)
        res = ""
        i, j = 0, 0
        while i < m and j < n:
            if word1[i] > word2[j]:
                res += word1[i]
                i += 1
            elif word1[i] < word2[j]:
                res += word2[j]
                j += 1
            else:
                ii, jj = i + 1, j + 1
                find = False
                while ii < m and jj < n:
                    if word1[ii] == word2[jj]:
                        ii += 1
                        jj += 1
                    elif word1[ii] < word2[jj]:
                        res += word2[j]
                        j += 1
                        find = True
                        break
                    else:
                        res += word1[i]
                        i += 1
                        find = True
                        break
                if not find:

                    if ii == m and jj < n:
                        res += word2[j]
                        j += 1
                    elif ii < m and jj == n:
                        res += word1[i]
                        i += 1
                    else:
                        res += word1[i]
                        i += 1
        if i < m:
            res += word1[i:]
        if j < n:
            res += word2[j:]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.largestMerge("abcabc"
                         , "abdcaba"))
    print(s.largestMerge(
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeqeeeeeeeeeeeeqeeeeeeeeeeeeeeeqeeeeeeeqeqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeqeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeee",
        "eeeqeeeeeeqeeeeeeeeeeeeqqeeqeeqqeeeqeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeqeeeqeeeqeeeeeeeeeeeqeeeeqeqeeqeqeeeeeqeeeqeeqeqeeeeeeeqeeqeqeeeeeeeeqeqeqeeeqeeeeqeeeeqeeeqeeeqeeeeeeqeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeqeeeeeeqeqqeqeeeeeeeeeqqeeeqeqeeqeeeeeeeeeqeeeeqeeeqeeeeeqeeqeeeqqeeeeeeeeqeeeeeeeeeeeeeeqqeeqqqeeeqeeeeeeeeqeeqeeeeeqeqqeqeeqqeeeeeeeeeeqeqeqeeeeeqeeeeeeeeeqeqeeeeeeqqeeeqeeeeqeeeeeeqeeqeeeeqeeeeeeeqeeqeeeeqeeqeeeeeeeeeeeqqeeeeeeeqeeeeeqeeqeeeeeeqeqeeeqeeeeeeeeqeeeeeqeeeeeeeeeeqeqeeeeqqqeeeeeeqqqeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeqeeqqeeqeeeeeeeeeeeeeeqeeqqeeeqeeeeeeeeeeeqeeeeqeeeeeeeeeeeeqeeeeeqeeeeeeeeeeeqeqqeqqeeeeeeeeeeqqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeqeeqqeeqeeeeeeqeeqeeeqeeeeeeqeeeeqeeqeqqqeeeqeeeeeeeeqqeeqeeeeqeeeeqeeeeeeeeeqqeeqqeeeeeeqeeeeeeeeqqeeeeeeeqeeeeeeeeeeeeqeeqeeeeeeeeeqeqeeqeeeeeeeeeeqeeqeeeeeeqeeeqeeeeeeeeeqqeeqqeeeeeqeeeqeeeeeeeeeeeeeeeeqeqeeeeeeqeeqeeeqeeeqeeqeeqeeeeeeqqeeeeeeeeeqqqeqqeeeqeeeeqqeeqqqeeeqeeeeqeeeeeeeeqeeeeqqqeeeeqeeeeqeqqeeqeeeeeeeeeeqeeeeeqqqeeqeeeeeeeeeeeqeeeeqeeeeeeeeeeqeeeqeeqeeeeeeqqeqeqqeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeqqeeeeeeeeeeeeeqqeqeqeeeeeeqeeeeqqqeeeqqeqqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeqeqeeeeeeeeeeeeeeeeeqqqeeqeeeeqeeeeeqqeeeeeeeqeeeeeqqeeqqqqeeeeeeeeeeeqeeeeeeeeeeqeeqeeqeeeeeeeeeeeeqeqeeqeeeeeqqeeeeqqeeeqqeeeeeeeeeeqeeeqqeeeqeeeeeeqeeqqeeeqeqqeeeqeqeqqeeeqeeeeeeeqeeeqeeeeeeqqeeqeeqeeeeeqeeeeqeeqeeqeqeeeeeeqeeeeqeeqeeeeeqeeqeqeeeeeeqeeeeeeeeeeeeeeqeeqeeqeeeeeeeqeeqeeeeeeeqeeeeeeeeeeeeeqqeeeeeeqeeeeqeeqeqeeeeeeeeqeqeqeeeeeeeqeqeeeeeeeeqeeeqeeeeeeeeeeeeeeqeeqeeeeeeeqeeeeeeeeeeeqqeeeqeqqeeeeeeeqeeeeeqeeqeeeeqeeqeeeqeeeeeqqeeqeeeqeeeeeqeeeeeqeqeeqqeeeeqeeeeeeeeeeeeeeeeeqeqeeeeqeeqeqeqeeeeeeeeqeeeeeqqeeeqeeeeqqeeeeeeqeeeeeeqeeeqeeeeeeeeeeqqeeeeeeeeqeeeqeeqeeeeeeqeeqeeeeeeeeqeqeeeeeeeeeeqeeeeeeeeqeqeqqeeeqeeeeqeeeeeeqeeeeqeeeqeeeeeeeeqeqqqqeqeeeeeqeeeqeeeeeeeqeqeeqeeeqeeeqeeeqeeeeeeqqqeqeeqeeeeeeqeqeqeeeeeeeeeqeqeeqeeqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqqqeeeeeeqeqeqeeqqeeeqqeeeqeeeeeqeeqeqeeqeeeeeeeeqeeeqeeeeeeeqeeeeeqeqeqeqeqeeeeeeeeqeeeeqqqqeeeeqeeeeeeeeeeqeeqeeeqeeeqeeeeqeeeeeeqqqqeeqeeqeeeeeeqqeeeeeeeeeqeqqeeeeeeqeeeeeqeqeeqqeeqqeeeeeeeqqqeeeeeeeeeqeeeeeqeqeeeeeeeqqqqqeeqeeqeeeeqeeeeeqeeeeeeeeqeqeeeeeeeeeeqeeqeeeeeeeeeeeeqqeeeqqeeeeeeeeeeqeqeeeqeeeeeeeqeeeeeeeeeeeeeeeqeeeeeeqqeeqeqeeeeeeeqeqeqqeeeqeeeqeeeqeeeeeqqeeeeeeqeeqqeeeeeeeeqeeeeeqeeeeeeeeeeeeeeeqeeeeqqeeeeeqeeqeeeeqqeeeeeeeqeeeeqqeeeqeqeqeeeeeqeeeeeeqeqeeeeqeeeeqeeeeqeeqeeeeqqeeeeeeeqeeeeeeeeeqeqeeeeeeeeeqeqeeeeeqeqeqqeqeqeeeqeeeqeqeqqqqqeeqeeeeqeeeqeeeqeeqeeeqeeeeeeqqeeeqeeeeeeeeeeeeeeqeqeeqeeeeqeeqeeeqqeeeeeeeeeeeeeqeeqeeeeeeeeeqeeeqqeeeeeeeeeeqeeeeeqeeeeeeeqeeeeeeeeeeeeeeeeqeeeeee"))
    print("" < "a")