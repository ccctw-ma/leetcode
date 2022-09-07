import bisect
from bisect import bisect_left, bisect_right
from typing import List


class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        pairs.sort(key=lambda x: x[1])
        ends = [x[1] for x in pairs]
        n = len(pairs)
        dp = [0] * n
        for i, pair in enumerate(pairs):
            a, b = pair

            index = bisect_left(ends, a)
            if index != 0:
                dp[i] = max(dp[:index]) + 1
            else:
                dp[i] = 1
        return max(dp)


if __name__ == '__main__':
    s = Solution()
    print(s.findLongestChain([[1, 2], [2, 3], [3, 4]]))

