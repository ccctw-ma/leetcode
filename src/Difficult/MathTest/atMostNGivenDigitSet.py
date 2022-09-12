import bisect
from typing import List
import heapq
from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def atMostNGivenDigitSet(self, digits: List[str], n: int) -> int:
        size = len(str(n))
        digits = list(map(int, digits))
        digits.sort()
        ds = len(digits)
        dp = [0] * size
        dp[0] = 1
        res = 0
        for i in range(1, size):
            dp[i] = ds * dp[i - 1]
            res += dp[i]
        flag = 0
        for i, d in enumerate(str(n)):
            d = int(d)
            index = bisect.bisect_left(digits, d)
            res += index * dp[size - i - 1]
            if index == ds or d != digits[index]:
                break
            flag += 1
        return res + bool(flag == size)


if __name__ == '__main__':
    s = Solution()
    print(s.atMostNGivenDigitSet(digits=["1", "3", "5", "7"], n=188))
    print(s.atMostNGivenDigitSet(digits=["7"], n=8))
    print(s.atMostNGivenDigitSet(digits=["1", "4", "9"], n=1000000000))
    print(s.atMostNGivenDigitSet(["3", "4", "8"], 4))
