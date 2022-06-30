import collections
from bisect import bisect, bisect_left, bisect_right
from functools import lru_cache
from itertools import combinations
from typing import Optional, List

import sortedcontainers


class Solution:
    def removeAnagrams(self, words: List[str]) -> List[str]:
        res = []
        s = ""
        for word in words:
            t = [c for c in word]
            w = "".join(sorted(t))
            if len(res) == 0:
                res.append(word)
                s = w
            else:
                if w != s:
                    res.append(word)
                    s = w
        return res

    def maxConsecutive(self, bottom: int, top: int, special: List[int]) -> int:
        special.sort()
        max_res = 0
        a, b = special[0] - bottom, top - special[-1]
        for i in range(1, len(special)):
            max_res = max(max_res, special[i] - special[i - 1] - 1)
        return max(a, b, max_res)

    def largestCombination(self, candidates: List[int]) -> int:
        dp = [0] * 32
        for c in candidates:
            for i, v in enumerate(list(reversed(bin(c)[2:]))):
                if v == '1':
                    dp[i] += 1

        return max(dp)


class CountIntervals:

    def __init__(self):
        self.SD = sortedcontainers.SortedDict()
        self.cnt = 0

    def add(self, left: int, right: int) -> None:
        n = len(self.SD)
        i = self.SD.bisect_left(left)
        while i < n and self.SD.values()[i] <= right:
            r, l = self.SD.items()[i]
            left = min(left, l)
            right = max(right, r)
            self.SD.popitem(i)
            self.cnt -= r - l + 1
            n -= 1
        self.SD[right] = left
        self.cnt += right - left + 1

    def count(self) -> int:
        return self.cnt


if __name__ == '__main__':
    s = Solution()
    # print(s.largestCombination(candidates=[16, 17, 71, 62, 12, 24, 14]))
    # print(bin(10000000)[2:])
    C = CountIntervals()
    arr = [2, 7, 10]
    print(bisect_left(arr, ))
