from typing import List


class Solution:
    def imageSmoother(self, img: List[List[int]]) -> List[List[int]]:
        directions = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
        row, col = len(img), len(img[0])
        res = [[0 for _ in range(col)] for _ in range(row)]
        for i in range(row):
            for j in range(col):
                count = 1
                sum = img[i][j]
                for x, y in directions:
                    nx, ny = i + x, j + y
                    if 0 <= nx < row and 0 <= ny < col:
                        count += 1
                        sum += img[nx][ny]
                res[i][j] = sum // count
        return res

    # 二维前缀和
    def imageSmoother2(self, img: List[List[int]]) -> List[List[int]]:
        row, col = len(img), len(img[0])
        res = [[0 for _ in range(col)] for _ in range(row)]
        sums = [[0 for _ in range(col + 1)] for _ in range(row + 1)]
        for i in range(1, row + 1):
            for j in range(1, col + 1):
                sums[i][j] = img[i - 1][j - 1] - sums[i - 1][j - 1] + sums[i - 1][j] + sums[i][j - 1]
        for i in range(row):
            for j in range(col):
                top, bottom = min(row - 1, i + 1), max(0, i - 1)
                left, right = max(0, j - 1), min(col - 1, j + 1)
                count = (top - bottom + 1) * (right - left + 1)
                sum = sums[top + 1][right + 1] - sums[top + 1][left] - sums[bottom][right + 1] + sums[bottom][left]
                res[i][j] = sum // count
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.imageSmoother2(img=[[100, 200, 100], [200, 50, 200], [100, 200, 100]]))
