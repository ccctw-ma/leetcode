from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional
from functools import lru_cache

class Solution:
    def minCut(self, s: str) -> int:

        @lru_cache(None)
        def isPalindrome(i, j) -> bool:
            if i >= j:
                return True
            return s[i] == s[j] and isPalindrome(i + 1, j - 1)

        @lru_cache(None)
        def minSubCur(i, j):
            if i >= j:
                return 0
            if isPalindrome(i, j):
                return 0
            return min(1 + minSubCur(i, k) + minSubCur(k + 1, j) for k in range(i + 1, j))

        return minSubCur(0, len(s) - 1)

if __name__ == '__main__':
    s = Solution()
    n = 100
    g = [[True] * n for _ in range(n)]
    print(g[20][10])