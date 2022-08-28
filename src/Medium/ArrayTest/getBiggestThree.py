from collections import defaultdict
from heapq import heappush, heappop
from itertools import accumulate
from typing import List


class Solution:
    def getBiggestThree(self, grid: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])
        # buc = defaultdict(list)
        # for i in range(m + n - 1):
        #     temp = []
        #     x, y = min(i, m - 1), 0 if i < m else i % (m - 1)
        #     while x >= 0 and y < n:
        #         temp.append(grid[x][y])
        #         x -= 1
        #         y += 1
        #     buc[i].append([0] + list(accumulate(temp)))
        # print(buc)
        h = []

        def push(val):
            nonlocal h
            if len(h) == 3 and val > h[0]:
                heappop(h)
                heappush(h, val)
            elif len(h) < 3:
                heappush(h, val)

        def fn(x, y, k):
            if k == 0:
                return grid[x][y]
            res = grid[x][y]
            tx, ty = x, y
            bx, by = x, y
            for _ in range(k):
                tx -= 1
                ty += 1
                res += grid[tx][ty]
                bx += 1
                by += 1
                res += grid[bx][by]
            rx, ry = x, y + 2 * k
            rtx, rty = rx, ry
            rbx, rby = rx, ry
            res += grid[rx][ry]
            for _ in range(k - 1):
                rtx -= 1
                rty -= 1
                res += grid[rtx][rty]
                rbx += 1
                rby -= 1
                res += grid[rbx][rby]
            return res

        buc = set()
        for i in range(m):
            for j in range(n):

                top, bottom, right = i + 1, m - i, (n - j + 1) // 2
                length = min(top, bottom, right)
                for k in range(length):
                    temp = fn(i, j, k)
                    buc.add(temp)

        return sorted(buc, reverse=True)[:min(len(buc), 3)]


if __name__ == '__main__':
    s = Solution()
    print(s.getBiggestThree(
        grid=[[3, 4, 5, 1, 3], [3, 3, 4, 2, 3], [20, 30, 200, 40, 10], [1, 5, 5, 4, 1], [4, 3, 2, 2, 5]]))
