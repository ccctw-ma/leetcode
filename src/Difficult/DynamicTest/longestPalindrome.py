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
    def longestPalindrome(self, word1: str, word2: str) -> int:
        a, b = len(word1), len(word2)
        s = word1 + word2
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        res = 0
        for i in range(n - 1, - 1, -1):
            dp[i][i] = 1
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    dp[i][j] = dp[i + 1][j - 1] + 2
                    if i < a <= j:
                        res = max(res, dp[i][j])
                else:
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.longestPalindrome(word1="cacb", word2="cbba"))
    print(s.longestPalindrome(word1="ab", word2="ab"
                              ))
    print(s.longestPalindrome(word1="aa", word2="bb"))
    print(s.longestPalindrome("aba", "cc"))