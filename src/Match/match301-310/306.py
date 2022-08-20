from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List


class Solution:
    def largestLocal(self, grid: List[List[int]]) -> List[List[int]]:
        res = []
        n = len(grid)
        for i in range(n - 2):
            l = []
            for j in range(n - 2):
                cur = grid[i][j]
                for ii in range(3):
                    for jj in range(3):
                        cur = max(cur, grid[i + ii][j + jj])
                l.append(cur)
            res.append(l)
        return res

    def edgeScore(self, edges: List[int]) -> int:
        n = len(edges)
        dp = [0] * n
        for i, c in enumerate(edges):
            dp[c] += i
        res = 0
        index = 0
        for i, c in enumerate(dp):
            if c > res:
                res = c
                index = i
        return index

    def smallestNumber(self, pattern: str) -> str:
        res = []

        def dfs(pre, i, arr):
            if i == len(pattern):
                res.append(''.join(map(str, arr)))
                return
            cur = pattern[i]
            if cur == 'I':
                # flag = False
                for next in range(pre + 1, 10):
                    if arr.count(next) > 0:
                        continue
                    else:
                        pre = next
                        arr.append(next)
                        dfs(pre, i + 1, arr)
                        arr.pop()
            else:
                for next in range(pre - 1, 0, -1):
                    if arr.count(next) > 0:
                        continue
                    else:
                        pre = next
                        arr.append(next)
                        dfs(pre, i + 1, arr)
                        arr.pop()

        arr = []
        for i in range(1, 10):
            pre = i
            # ss.add(i)
            arr.append(i)
            dfs(pre, 0, arr)
            arr.pop()

        return res[0]

    def countSpecialNumbers(self, N: int) -> int:  # 小于等于n的  没有重复digit的num 的 个数
        arr = list(map(int, list(str(N))))
        n = len(arr)
        # 先处理 0xxx, 00xx, 000x的情况
        res = 0
        for i in range(0, n - 1):
            res += 9 * perm(9, i)
        visited = [0] * 10
        for i in range(n):
            begin = 1 if i == 0 else 0
            num = arr[i]
            for j in range(begin, num):
                # 选择的数没有重复
                if visited[j] == 0:
                    # xxxx 选择了第一位之后， 剩下还有n - i - 1 位， 在 9 - i个数据中选择n - i - 1个数据
                    res += perm(9 - i, n - i - 1)
            visited[num] += 1
            # 4456 4400之后就不用考虑了
            if visited[num] > 1:
                break
            # 4567 前面进行到了 4563， 还剩4567没有考虑 加上
            if i == n - 1:
                res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.largestLocal(grid=[[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 2, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1]]))
    # print(s.edgeScore(edges=[1, 0, 0, 0, 0, 7, 7, 5]))
    # print(s.smallestNumber(pattern="IIIDIDDD"))
    # print(s.smallestNumber(pattern="DDD"))
    print(s.countSpecialNumbers(4567))
    print(perm(5, 3))
    print(comb(5, 3))
