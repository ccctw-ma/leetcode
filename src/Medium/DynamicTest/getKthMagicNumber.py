from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional


class Solution:
    def getKthMagicNumber(self, k: int) -> int:

        dp = [0] * k
        dp[0] = 1
        three, five, seven = 0, 0, 0
        for i in range(1, k):
            a, b, c = dp[three] * 3, dp[five] * 5, dp[seven] * 7
            dp[i] = min(a, b, c)
            if dp[i] == a:
                three += 1
            if dp[i] == b:
                five += 1
            if dp[i] == c:
                seven += 1

        return dp[k - 1]


if __name__ == '__main__':
    s = Solution()
    print(s.getKthMagicNumber(10))
