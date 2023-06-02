import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def differenceOfDistinctValues(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ls, rs = set(), set()
                x, y = i - 1, j - 1
                while x >= 0 and y >= 0:
                    ls.add(grid[x][y])
                    x, y = x - 1, y - 1
                x, y = i + 1, j + 1
                while x < m and y < n:
                    rs.add(grid[x][y])
                    x, y = x + 1, y + 1
                ans[i][j] = abs(len(ls) - len(rs))

        return ans

    def minimumCost(self, s: str) -> int:

        n = len(s)
        rd = [[0, 0] for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            if i == n - 1:
                rd[i][0] = int(s[i] == '1')
                rd[i][1] = int(s[i] == '0')
                continue
            p = n - i
            if s[i] == '0':
                rd[i][0] = min(rd[i + 1][0], rd[i + 1][1] + p - 1)
                rd[i][1] = min(rd[i + 1][0] + p, rd[i + 1][1] + 2 * p - 1)
            if s[i] == '1':
                rd[i][0] = min(rd[i + 1][0] + 2 * p + 1, rd[i + 1][1] + p)
                rd[i][1] = min(rd[i + 1][0] + p - 1, rd[i + 1][1])
        res = min(rd[0])
        dp = [[0, 0] for _ in range(n + 1)]
        for i in range(n):
            if i == 0:
                dp[i][0] = int(s[i] == '1')
                dp[i][1] = int(s[i] == '0')
            else:
                p = i + 1
                if s[i] == '0':
                    dp[i][0] = min(dp[i - 1][0], dp[i - 1][1] + p - 1)
                    dp[i][1] = min(dp[i - 1][0] + p, dp[i - 1][1] + 2 * p - 1)

                if s[i] == '1':
                    dp[i][0] = min(dp[i - 1][0] + 2 * p + 1, dp[i - 1][1] + p)
                    dp[i][1] = min(dp[i - 1][0] + p - 1, dp[i - 1][1])

            res = min(res, dp[i][0] + rd[i + 1][0], dp[i][1] + rd[i + 1][1])

        return res

    def maxIncreasingCells(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        rows = [[] for _ in range(m)]
        cols = [[] for _ in range(n)]

        for i in range(m):
            for j in range(n):
                rows[i].append((mat[i][j], i, j))
            rows[i].sort(key=lambda x: x[0])
        for j in range(n):
            for i in range(m):
                cols[j].append((mat[i][j], i, j))
            cols[j].sort(key=lambda x: x[0])

        print(rows)
        print(cols)

        @cache
        def fn(x, y):

            row = rows[x]
            col = cols[y]
            idx = bisect_right(row, mat[x][y], key=lambda x: x[0])
            res = 0
            for j in range(idx, n):
                _, nx, ny = row[j]
                res = max(res, fn(nx, ny))
            idx = bisect_right(col, mat[x][y], key=lambda x: x[0])
            for i in range(idx, m):
                _, nx, ny = col[i]
                res = max(res, fn(nx, ny))

            return res + 1

        # print(fn(2, 0))

        # buc = defaultdict(int)
        # ans = 1
        mn, midx = inf, 0
        for i in range(m):
            for j in range(n):
                if mat[i][j] < mn:
                    mn = mat[i][j]
                    midx = (i, j)
                # print(mat[i][j], i, j, fn(i, j))
                # buc[(i, j)] = fn(i, j)
                # ans = max(ans, fn(i, j))
        # print(buc)
        return fn(midx[0], midx[1])


if __name__ == '__main__':
    s = Solution()
    # print(s.minimumCost(s="010101"))
    # print(s.minimumCost(s="0011"))
    # print(s.maxIncreasingCells(mat=[[3, 1, 6], [-9, 5, 7]]))
    print(s.maxIncreasingCells([[7, 6, 3],
                                [-7, -5, 6],
                                [-7, 0, -4],
                                [6, 6, 0],
                                [-8, 6, 0]]))
