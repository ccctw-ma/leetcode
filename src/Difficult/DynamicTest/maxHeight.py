from typing import List, Optional, Union, Set
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from functools import lru_cache, cache, reduce


class Solution:
    def maxHeight(self, cuboids: List[List[int]]) -> int:
        n = len(cuboids)
        for c in cuboids:
            c.sort()
        cuboids.sort(key=sum)
        ans = 0
        dp = [0] * n
        for i in range(n):
            dp[i] = cuboids[i][2]
            for j in range(i):
                if cuboids[i][0] >= cuboids[j][0] and cuboids[i][1] >= cuboids[j][1] and cuboids[i][2] >= cuboids[j][2]:
                    dp[i] = max(dp[i], dp[j] + cuboids[i][2])
            ans = max(ans, dp[i])
        return ans

if __name__ == '__main__':
    s = Solution()
    # print(s.maxHeight(cuboids=[[38, 25, 45], [76, 35, 3]]))
    print(s.maxHeight(cuboids=[[50, 45, 20], [95, 37, 53], [45, 23, 12]]))
