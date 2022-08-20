from functools import lru_cache
from typing import Optional, List


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def decodeMessage(self, key: str, message: str) -> str:
        buc = {}
        base = 97
        for c in key:
            if c == ' ':
                continue
            if c not in buc:
                buc[c] = chr(base)
                base += 1
        res = []
        for c in message:
            if c == ' ':
                res.append(c)
            else:
                res.append(buc[c])
        return ''.join(res)

    def spiralMatrix(self, m: int, n: int, head: Optional[ListNode]) -> List[List[int]]:
        arr = [[-1 for _ in range(n)] for _ in range(m)]
        # print(arr)
        direction = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        t, b, l, r = 0, m - 1, 0, n - 1
        i, j = 0, 0
        index = 0
        while head:
            arr[i][j] = head.val
            head = head.next
            nx, ny = i + direction[index][0], j + direction[index][1]
            if nx < 0 or nx == m or ny < 0 or ny == n or arr[nx][ny] != -1:
                index = (index + 1) % 4
            i += direction[index][0]
            j += direction[index][1]
        return arr

    def peopleAwareOfSecret(self, n: int, delay: int, forget: int) -> int:
        mod = 10 ** 9 + 7
        dp = [[0 for _ in range(2)] for _ in range(n)]
        dp[0][0] = 1
        if delay < n:
            dp[delay][0] -= 1
            dp[delay][1] += 1
        if forget < n:
            dp[forget][1] -= 1
        for i in range(1, n):
            dp[i][0] = dp[i][0] + dp[i - 1][0]
            dp[i][1] = dp[i][1] + dp[i - 1][1]

            c = dp[i][1]
            if (i + delay) < n:
                dp[i + delay][0] -= c
                dp[i + delay][1] += c
            if (i + forget) < n:
                dp[i + forget][1] -= c

            dp[i][0] = (dp[i][0] + c) % mod

        return (dp[n - 1][0] + dp[n - 1][1]) % mod

    def countPaths(self, grid: List[List[int]]) -> int:
        mod = 10 ** 9 + 7
        direction = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        row, col = len(grid), len(grid[0])

        @lru_cache(None)
        def dfs(i, j):
            res = 1
            for d in direction:
                a, b = d
                ni = i + a
                ny = j + b
                if 0 <= ni < row and 0 <= ny < col and grid[i][j] < grid[ni][ny]:
                    res = (res + dfs(ni, ny)) % mod
            return res

        res = 0
        for i in range(row):
            for j in range(col):
                res = (res + dfs(i, j)) % mod
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.decodeMessage(key="the quick brown fox jumps over the lazy dog", message="vkbs bs t suepuv"))
    # print(s.spiralMatrix(m=3, n=5, head=[3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0]))
    # print(s.peopleAwareOfSecret(n=6, delay=2, forget=4))
    # print(s.peopleAwareOfSecret(n=4, delay=1, forget=3))
    # print(s.countPaths(grid=[[1, 1], [3, 4]]))
    # print(s.countPaths(grid=[[1], [2]]))
    if (d := 2 - 1) <= 1:
        print(d)

    print((s := "Hello world").center(40, '*'))
