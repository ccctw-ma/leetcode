import bisect
import collections
import heapq
from itertools import product
from math import inf
from string import ascii_lowercase
from typing import List


class Solution:
    def calculateTax(self, brackets: List[List[int]], income: int) -> float:
        res = 0
        preU, preP = 0, 0
        for u, p in brackets:
            if u <= income:
                res += (u - preU) * p / 100
            else:
                res += (income - preU) * p / 100
                break
            preU = u
        return res

    def minPathCost(self, grid: List[List[int]], moveCost: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        buc = collections.defaultdict(list)
        for index, mov in enumerate(moveCost):
            buc[index] = mov
        dp = grid[0]
        for i in range(m - 1):
            row = grid[i]
            next = grid[i + 1]
            temp = [0] * n
            for j in range(n):
                move = buc[row[j]]
                if j == 0:
                    for k, c in enumerate(move):
                        temp[k] = dp[j] + c + next[k]
                else:
                    for k, c in enumerate(move):
                        temp[k] = min(temp[k], dp[j] + next[k] + c)
            dp = temp
        return min(dp)

    def distributeCookies(self, cookies: List[int], k: int) -> int:
        n = len(cookies)
        res = inf
        for p in product(range(k), repeat=n - 1):
            # print(p)
            t = [0] * k
            for i in range(n - 1):
                t[p[i]] += cookies[i]
            t[0] += cookies[-1]
            res = min(res, max(t))
        return res

    def distinctNames(self, ideas: List[str]) -> int:
        ss = [set() for _ in range(26)]
        for x in ideas:
            ss[ord(x[0]) - ord('a')].add(x[1:])
        res = 0
        for i in range(26):
            for j in range(i + 1, 26):
                res += len(ss[i] - ss[j]) * len(ss[j] - ss[i])
        return res * 2


if __name__ == '__main__':
    s = Solution()
    # print(s.calculateTax(brackets=[[1, 0], [4, 25], [5, 50]], income=2))
    # print(s.minPathCost(grid=[[5, 3], [4, 0], [2, 1]], moveCost=[[9, 8], [1, 5], [10, 12], [18, 6], [2, 4], [14, 3]]))
    # print(s.distributeCookies([15, 14, 8, 13, 7, 2, 13, 19]
    #                           , 7))
    # print(s.distinctNames(ideas=["coffee", "donuts", "time", "toffee"]))
    print(ascii_lowercase)