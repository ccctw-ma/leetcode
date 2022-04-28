from typing import List


class Solution:
    def projectionArea(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        zeros = 0
        row_maxs, col_maxs = [], []
        for row in grid:
            row_maxs.append(max(row))
        for j in range(n):
            col_max = 0
            for i in range(m):
                col_max = max(col_max, grid[i][j])
                if grid[i][j] == 0:
                    zeros += 1
            col_maxs.append(col_max)
        return m * n - zeros + sum(row_maxs) + sum(col_maxs)


if __name__ == '__main__':
    # print(Solution().projectionArea([[1, 0], [0, 2]]))
    grid = [[1, 2], [3, 4]]
    arr = [1, 2, 3, 4]
    for i in zip(arr):
        print(i)
    print(1 + (2 == 1))