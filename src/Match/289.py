import collections
from functools import lru_cache
from itertools import accumulate
from typing import List


class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            temp = ""
            l, r = 0, k
            while l < len(s):
                ss = s[l:r]
                res = 0
                for c in ss:
                    res += int(c)
                temp += str(res)
                l = r
                r = min(len(s), r + k)
            s = temp
        return s

    def minimumRounds(self, tasks: List[int]) -> int:
        cnt = collections.Counter(tasks)
        res = 0
        for count in cnt.values():
            if count == 1:
                return -1
            else:
                temp = 0
                while count != 0 and count % 3 != 0:
                    count -= 2
                    temp += 1
                temp += count // 3
            res += temp

        return res

    def maxTrailingZeros(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        pre_sum_two = [[[0, 0] for _ in range(n + 1)] for _ in range(m + 1)]
        pre_sum_five = [[[0, 0] for _ in range(n + 1)] for _ in range(m + 1)]
        twos = [[0] * n for _ in range(m)]
        fives = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                two, five = 0, 0
                while grid[i][j] % 2 == 0:
                    two += 1
                    grid[i][j] //= 2
                while grid[i][j] % 5 == 0:
                    five += 1
                    grid[i][j] //= 5
                pre_sum_two[i + 1][j + 1][0] = pre_sum_two[i][j + 1][0] + two
                pre_sum_two[i + 1][j + 1][1] = pre_sum_two[i + 1][j][1] + two
                pre_sum_five[i + 1][j + 1][0] = pre_sum_five[i][j + 1][0] + five
                pre_sum_five[i + 1][j + 1][1] = pre_sum_five[i + 1][j][1] + five
                twos[i][j] = two
                fives[i][j] = five
        res = 0
        for i in range(m):
            for j in range(n):
                ul = min(pre_sum_two[i + 1][j + 1][0] + pre_sum_two[i + 1][j + 1][1] - twos[i][j],
                         pre_sum_five[i + 1][j + 1][0] + pre_sum_five[i + 1][j + 1][1] - fives[i][j])
                ur = min(pre_sum_two[i + 1][j + 1][0] + pre_sum_two[i + 1][n][1] - pre_sum_two[i + 1][j + 1][1],
                         pre_sum_five[i + 1][j + 1][0] + pre_sum_five[i + 1][n][1] - pre_sum_five[i + 1][j + 1][1])
                dl = min(pre_sum_two[m][j + 1][0] - pre_sum_two[i + 1][j + 1][0] + pre_sum_two[i + 1][j + 1][1],
                         pre_sum_five[m][j + 1][0] - pre_sum_five[i + 1][j + 1][0] + pre_sum_five[i + 1][j + 1][1])
                dr = min(pre_sum_two[m][j + 1][0] - pre_sum_two[i + 1][j + 1][0] + pre_sum_two[i + 1][n][1] -
                         pre_sum_two[i + 1][j + 1][1] + twos[i][j],
                         pre_sum_five[m][j + 1][0] - pre_sum_five[i + 1][j + 1][0] + pre_sum_five[i + 1][n][1] -
                         pre_sum_five[i + 1][j + 1][1] + fives[i][j])
                res = max(res, max([ul, ur, dl, dr]))
        return res

    def longestPath(self, parent: List[int], s: str) -> int:

        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)

        ans = 0

        def dfs(x: int) -> int:
            nonlocal ans
            max_len = 0
            for y in g[x]:
                len = dfs(y) + 1
                if s[y] != s[x]:
                    ans = max(ans, max_len + len)
                    max_len = max(max_len, len)
            return max_len

        dfs(0)
        return ans + 1


if __name__ == '__main__':
    s = Solution()
    # print(s.digitSum(s="11111222223", k=3))
    # print(s.minimumRounds(tasks=[2, 2, 3, 3, 2, 4, 4, 4, 4, 4]))
    # print(s.maxTrailingZeros(grid=[[23, 17, 15, 3, 20], [8, 1, 20, 27, 11], [9, 4, 6, 2, 21], [40, 9, 1, 10, 6], [22, 7, 4, 5, 3]]))
    print(s.maxTrailingZeros([[534, 575, 625, 84, 20, 999, 35], [208, 318, 96, 380, 819, 102, 669]]))
    # print(s.maxTrailingZeros([[596], [991], [748], [1000], [904], [922], [940], [962]]))
    # print(s.longestPath(parent=[-1, 0, 0, 0], s="aabc"))
