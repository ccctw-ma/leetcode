import collections
from functools import lru_cache
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def largestGoodInteger(self, num: str) -> str:
        res = ""
        max_v = -1
        for i in range(len(num) - 2):
            a, b, c = num[i], num[i + 1], num[i + 2]
            if a == b and b == c:
                if int(num[i:i + 3]) > max_v:
                    max_v = int(num[i:i + 3])
                    res = num[i:i + 3]
        return res

    def averageOfSubtree(self, root: Optional[TreeNode]) -> int:

        res = 0

        def dfs(root):
            nonlocal res
            if root is None:
                return 0, 0
            nl, cl = dfs(root.left)
            nr, cr = dfs(root.right)
            if (root.val + nl + nr) // (1 + cl + cr) == root.val:
                res += 1
            return root.val + nl + nr, 1 + cl + cr

        dfs(root)
        return res

    def countTexts(self, pressedKeys: str) -> int:
        mod = int(1e9 + 7)
        l, r = 0, 0
        n = len(pressedKeys)
        f, v = [0] * (n + 1), [0] * (n + 1)
        f[0], v[0] = 1, 1
        for i in range(1, n + 1):
            for j in range(1, min(i + 1, 4)):
                f[i] = (f[i] + f[i - j]) % mod
            for j in range(1, min(i + 1, 5)):
                v[i] = (v[i] + v[i - j]) % mod
        res = 1
        while l < n:
            r = l
            while r < n and pressedKeys[r] == pressedKeys[l]:
                r += 1
            ch, num = pressedKeys[l], r - l
            if ch == '7' or ch == '9':
                res = (res * v[num]) % mod
            else:
                res = (res * f[num]) % mod
            l = r
        return res

    def hasValidPath(self, grid: List[List[str]]) -> bool:
        if grid[0][0] == ')':
            return False
        m, n = len(grid), len(grid[0])
        if (m + n) % 2 == 0:
            return False

        for i in range(m):
            for j in range(n):
                if grid[i][j] == '(':
                    grid[i][j] = 1
                else:
                    grid[i][j] = -1

        @lru_cache(maxsize=None, type=False)
        def f(i, j, c):
            if i == m - 1 and j == n - 1 and c == 0:
                return True
            if i < m - 1 and c + grid[i + 1][j] >= 0 and f(i + 1, j, c + grid[i + 1][j]):
                return True
            if j < n - 1 and c + grid[i][j + 1] >= 0 and f(i, j + 1, c + grid[i][j + 1]):
                return True
            return False

        return f(0, 0, 1)


if __name__ == '__main__':
    s = Solution()
    # print(s.largestGoodInteger(num="222"))
    # print(int("000"))
    # print(s.countTexts(
    #     pressedKeys="88888888888888888888888888888999999999999999999999999999994444444444444444444444444444488888888888888888888888888888555555555555555555555555555556666666666666666666666666666666666666666666666666666666666222222222222222222222222222226666666666666666666666666666699999999999999999999999999999888888888888888888888888888885555555555555555555555555555577777777777777777777777777777444444444444444444444444444444444444444444444444444444444433333333333333333333333333333555555555555555555555555555556666666666666666666666666666644444444444444444444444444444999999999999999999999999999996666666666666666666666666666655555555555555555555555555555444444444444444444444444444448888888888888888888888888888855555555555555555555555555555555555555555555555555555555555555555555555555555555555999999999999999555555555555555555555555555554444444444444444444444444444444555"))
