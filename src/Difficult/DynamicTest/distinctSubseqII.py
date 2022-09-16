from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def distinctSubseqII(self, s: str) -> int:
        dp = [1]
        last = {}
        for i, x in enumerate(s):
            dp.append(dp[-1] * 2)
            if x in last:
                dp[-1] -= dp[last[x]]
            last[x] = i

        return (dp[-1] - 1) % (10 ** 9 + 7)




if __name__ == '__main__':
    s = Solution()
    print(s.distinctSubseqII('aba'))
