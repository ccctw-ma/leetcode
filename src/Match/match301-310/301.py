import collections
from math import comb
from typing import List

import sortedcontainers
from sortedcontainers import SortedSet


class SmallestInfiniteSet:

    def __init__(self):
        self.buc = sortedcontainers.SortedSet()
        self.index = 1

    def popSmallest(self) -> int:
        if len(self.buc) != 0 and self.buc[0] < self.index:
            res = self.buc[0]
            self.buc.pop(0)
        else:
            res = self.index
            self.index += 1
        return res

    def addBack(self, num: int) -> None:
        if num < self.index:
            self.buc.add(num)


class Solution:
    def fillCups(self, amount: List[int]) -> int:
        amount.sort(reverse=True)
        count = 0
        while True:
            if amount[0] > 0 and amount[1] > 0:
                amount[0] -= 1
                amount[1] -= 1
                count += 1
            else:
                break
            amount.sort(reverse=True)
        return count + amount[0]

    def canChange(self, start: str, target: str) -> bool:
        ca, cb = collections.Counter(start), collections.Counter(target)
        if ca['L'] != cb['L'] or ca['R'] != cb['R']:
            return False
        ss = start.replace('_', '')
        st = target.replace('_', '')
        if ss != st:
            return False
        a1 = [i for i, c in enumerate(start) if c == 'L']
        b1 = [i for i, c in enumerate(start) if c == 'R']
        a2 = [i for i, c in enumerate(target) if c == 'L']
        b2 = [i for i, c in enumerate(target) if c == 'R']
        for x, y in zip(a1, a2):
            if x < y:
                return False
        for x, y in zip(b1, b2):
            if x > y:
                return False
        return True

    def idealArrays(self, n: int, maxValue: int) -> int:
        mod = 10 ** 9 + 7
        dp = [0] + [1] * maxValue

        buc = collections.defaultdict(set)
        buc[1].add(1)
        for i in range(1, maxValue + 1):
            buc[i].add(i)
            j = 2
            while i * j <= maxValue:
                buc[i * j].add(i)
                j += 1

        # print(buc)
        for i in range(1, n):
            temp = [0] * (maxValue + 1)

            for i in range(1, maxValue + 1):
                base = i
                j = base
                while base * j <= maxValue:
                    temp[base * j] = (temp[base * j] + dp[base]) % mod
                    j += 1

            dp = temp
        return sum(dp) % mod


if __name__ == '__main__':
    # s = SortedSet()
    s = Solution()
    # print(s.idealArrays(2, 10))
    # print(s.idealArrays(5, 3))
    # print(s.idealArrays(184, 389))
    # print(s.idealArrays(5878, 2900))
    print(comb(4, 2))
