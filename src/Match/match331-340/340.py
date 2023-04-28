import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def diagonalPrime(self, nums: List[List[int]]) -> int:
        res = 0

        def check(x):
            if x == 1:
                return False
            for i in range(2, int(sqrt(x)) + 1):
                if x % i == 0:
                    return False
            return True

        m, n = len(nums), len(nums[0])
        for i in range(m):
            a = nums[i][i]
            b = nums[i][n - i - 1]
            if check(a):
                res = max(res, a)
            if check(b):
                res = max(res, b)
        return res

    def distance(self, nums: List[int]) -> List[int]:
        buc = defaultdict(list)
        for i, c in enumerate(nums):
            buc[c].append(i)
        preBuc = defaultdict(list)
        for k in buc.keys():
            arr = buc[k]
            preSum = [0] + list(accumulate(arr))
            preBuc[k] = preSum

        ans = []
        for i, c in enumerate(nums):
            if len(buc[c]) == 1:
                ans.append(0)
            else:
                arr = buc[c]
                preSum = preBuc[c]
                idx = bisect_left(arr, i)
                left = (i * (idx + 1)) - preSum[idx + 1]
                right = preSum[-1] - preSum[idx] - i * (len(arr) - idx)
                ans.append(left + right)
        return ans

    def minimizeMax(self, nums: List[int], p: int) -> int:
        n = len(nums)
        nums.sort()

        def f(x):
            cnt = 0
            i = 0
            while i < n - 1:
                if nums[i + 1] - nums[i] <= x:
                    cnt += 1
                    i += 2
                else:
                    i += 1

            return cnt >= p

        l, r = 0, 10 ** 9 + 1
        while l < r:
            m = (l + r) // 2
            if f(m):
                r = m
            else:
                l = m + 1
        return l

    def minimumVisitedCells2(self, grid: List[List[int]]) -> int:

        m, n = len(grid), len(grid[0])
        q = deque([(0, 0)])
        step = 1
        vis = [[10 ** 9 + 7] * n for _ in range(m)]
        vis[0][0] = 1
        Row, Col = [0] * m, [0] * n
        Row[0] = Col[0] = 1
        while q:
            nn = len(q)
            for _ in range(nn):
                cx, cy = q.popleft()
                if cx == m - 1 and cy == n - 1:
                    return step
                v = grid[cx][cy]
                for i in range(max(cx + 1, Col[cy]), min(cx + v + 1, m)):
                    q.append((i, cy))
                Col[cy] = max(Col[cy], min(cx + v + 1, m))

                for j in range(max(cy + 1, Row[cx]), min(cy + v + 1, n)):
                    q.append((cx, j))
                Row[cx] = max(Row[cx], min(cy + v + 1, n))
            step += 1
        return -1

    def minimumVisitedCells3(self, grid: List[List[int]]) -> int:

        m, n = len(grid), len(grid[0])
        MAX = 10 ** 9 + 7
        vis1 = [[MAX] * n for _ in range(m)]
        vis2 = [[MAX] * n for _ in range(m)]
        vis1[0][0] = 1
        vis2[m - 1][n - 1] = 1
        q1 = deque([(0, 0)])
        q2 = deque([(m - 1, n - 1)])
        while q1 and q2:
            for _ in range(len(q1)):
                i, j = q1.popleft()
                for ni in range(i + 1, min(i + grid[i][j] + 1, m)):
                    if vis1[ni][j] > vis1[i][j] + 1:
                        vis1[ni][j] = vis1[i][j] + 1
                        if vis2[ni][j] != MAX:
                            return vis1[ni][j] + vis2[ni][j] - 1
                        q1.append((ni, j))
                for nj in range(j + 1, min(j + grid[i][j] + 1, n)):
                    if vis1[i][nj] > vis1[i][j] + 1:
                        vis1[i][nj] = vis1[i][j] + 1
                        if vis2[i][nj] != MAX:
                            return vis1[i][nj] + vis2[i][nj] - 1
                        q1.append((i, nj))
            for _ in range(len(q2)):
                i, j = q1.popleft()
                for ni in range(i - 1, -1, -1):
                    if grid[ni][j] + ni < i:
                        continue
                    if vis2[ni][j] > vis2[i][j] + 1:
                        vis2[ni][j] = vis2[i][j] + 1
                        if vis1[ni][j] != MAX:
                            return vis1[ni][j] + vis2[ni][j] - 1
                        q1.append((ni, j))
                for nj in range(j - 1, -1, -1):
                    if grid[i][nj] + nj < j:
                        continue
                    if vis2[i][nj] > vis2[i][j] + 1:
                        vis2[i][nj] = vis2[i][j] + 1
                        if vis1[i][nj] != MAX:
                            return vis1[i][nj] + vis2[i][nj] - 1
                        q1.append((i, nj))

        return -1

    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        dist = [[float('inf')] * n for _ in range(m)]

        h = []
        heappush(h, (1, 0, 0))
        rx = [0] * n
        ry = [0] * m
        dist[0][0] = 1
        while h:
            d, x, y = heappop(h)
            dist[x][y] = d
            for dy in range(max(ry[x], y + 1), min(n, y + grid[x][y] + 1)):
                if dist[x][dy] > d + 1:
                    dist[x][dy] = d + 1
                    heappush(h, (d + 1, x, dy))
                ry[x] = dy
            for dx in range(max(rx[y], x + 1), min(m, x + grid[x][y] + 1)):
                if dist[dx][y] > d + 1:
                    dist[dx][y] = d + 1
                    heappush(h, (d + 1, dx, y))
                rx[y] = dx
        if dist[m - 1][n - 1] == float('inf'):
            return -1
        return dist[m - 1][n - 1]


if __name__ == '__main__':
    s = Solution()
    # print(s.distance(nums=[1, 3, 1, 1, 2, 1]))
    # print(s.distance(nums=[0, 5, 3]))
    # print(s.minimizeMax(nums=[10, 1, 2, 7, 1, 3], p=2))
    # print(s.minimizeMax(nums=[4, 2, 1, 2], p=1))
    print(s.minimumVisitedCells(grid=[[3, 4, 2, 1], [4, 2, 3, 1], [2, 1, 0, 0], [2, 4, 0, 0]]))
    print(s.minimumVisitedCells(grid=[[3, 4, 2, 1], [4, 2, 1, 1], [2, 1, 1, 0], [3, 4, 1, 0]]))
    print(s.minimumVisitedCells(grid=[[2, 1, 0], [1, 0, 0]]))
    print(s.minimumVisitedCells([[6, 4, 8], [7, 3, 2], [2, 1, 11], [8, 13, 12], [4, 3, 0]]))
