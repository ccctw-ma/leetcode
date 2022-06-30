from typing import List


class Solution:
    def checkXMatrix(self, grid: List[List[int]]) -> bool:
        n = len(grid)
        for i in range(n):
            for j in range(n):
                if i == j or i + j == n - 1:
                    # print(i, j)
                    if grid[i][j] == 0:
                        return False
                else:
                    if grid[i][j] != 0:
                        return False
        return True

    def countHousePlacements(self, n: int) -> int:
        mod = 10 ** 9 + 7
        a, b, c, d = 1, 1, 1, 1
        for i in range(1, n):
            _a = (a + b + c + d) % mod
            _b = (a + c) % mod
            _c = (a + b) % mod
            _d = a
            a, b, c, d = _a, _b, _c, _d
        return (a + b + c + d) % mod

    def maximumsSplicedArray(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        sum1 = sum(nums1)
        sum2 = sum(nums2)
        diff = [0] * n
        for i in range(n):
            diff[i] = nums1[i] - nums2[i]

        f, res = diff[0], diff[0]
        ff, res2 = diff[0], diff[0]
        for i in range(1, n):
            f = min(f, 0) + diff[i]
            res = min(res, f)

            ff = max(ff, 0) + diff[i]
            res2 = max(res2, ff)
        res = min(res, 0)
        res2 = max(res2, 0)
        return max(sum1 - res, sum2 + res2)

    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        pass



if __name__ == '__main__':
    s = Solution()
    # print(s.checkXMatrix(grid=[[2, 0, 0, 1], [0, 3, 1, 0], [0, 5, 2, 0], [4, 0, 0, 2]]))
    # print(s.countHousePlacements(10))
    # print(s.maximumsSplicedArray(nums1=[20, 40, 20, 70, 30], nums2=[50, 20, 50, 40, 20]))
    # print(s.maximumsSplicedArray(nums1=[60, 60, 60], nums2=[10, 90, 10]))
    print(s.maximumsSplicedArray([28, 34, 38, 14, 30, 31, 23, 7, 28, 3],
                                 [42, 35, 7, 6, 24, 30, 14, 21, 20, 34]))
