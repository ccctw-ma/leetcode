from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def temperatureTrend(self, temperatureA: List[int], temperatureB: List[int]) -> int:
        n = len(temperatureA)
        da = [0] * (n - 1)
        db = [0] * (n - 1)
        for i in range(1, n):
            da[i - 1] = (temperatureA[i] - temperatureA[i - 1])
            db[i - 1] = temperatureB[i] - temperatureB[i - 1]
        res = 0
        i = 0
        while i < n - 1:
            j = i
            while j < n - 1 and (da[j] * db[j] > 0 or da[j] == db[j] == 0):
                j += 1
            res = max(res, j - i)
            i = j + 1
        return res

    def transportationHub(self, path: List[List[int]]) -> int:
        inDegree, outDegree = defaultdict(int), defaultdict(int)
        nodes = set()
        for a, b in path:
            nodes.add(a)
            nodes.add(b)
            inDegree[b] += 1
            outDegree[a] += 1
        for node in nodes:
            if inDegree[node] == (len(nodes) - 1) and outDegree[node] == 0:
                return node
        return -1

    def ballGame(self, num: int, plate: List[str]) -> List[List[int]]:
        m, n = len(plate), len(plate[0])
        directions = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        res = []
        for i in range(m):
            for j in range(n):
                # 排除四个角
                if (i == 0 and (j == 0 or j == n - 1)) or (i == m - 1 and (j == 0 or j == n - 1)):
                    continue
                # 排除非边缘区域地区
                if (0 < i < m - 1) and (0 < j < n - 1):
                    continue
                if plate[i][j] != '.':
                    continue
                if i == 0:
                    dir = 2
                elif i == m - 1:
                    dir = 0
                elif j == 0:
                    dir = 1
                else:
                    dir = 3
                x, y = i, j
                for t in range(num):
                    nx = x + directions[dir][0]
                    ny = y + directions[dir][1]
                    if 0 <= nx < m and 0 <= ny < n:
                        if plate[nx][ny] == 'O':
                            res.append([i, j])
                            break
                        if plate[nx][ny] == 'W':
                            dir = (dir + 3) % 4

                        elif plate[nx][ny] == 'E':
                            dir = (dir + 1) % 4
                        else:
                            pass
                        x, y = nx, ny
                    else:
                        break
        return res

    def closeLampInTree(self, root: TreeNode) -> int:
        def dfs(root):
            if root.left is None and root.right is None:
                return root.val, 1 - root.val, 1 - root.val, root.val  # 00, 11, 10, 01    子树关，子树开，自身开孩子关, 自身关子树开
            x1 = y1 = z1 = t1 = 0
            if root.left:
                x1, y1, z1, t1 = dfs(root.left)
            x2 = y2 = z2 = t2 = 0
            if root.right:
                x2, y2, z2, t2 = dfs(root.right)
            xx = x1 + x2
            yy = y1 + y2
            zz = z1 + z2
            tt = t1 + t2
            if root.val == 1:
                # 100 111 110 101
                x = min(xx + 1, yy + 1, zz + 1, tt + 3)  # 000
                y = min(xx + 2, yy, zz + 2, tt + 2)  # 111
                z = min(xx, yy + 2, zz + 2, tt + 2)  # 100
                t = min(xx + 1, yy + 1, zz + 3, tt + 1)  # 011
            else:
                # 000 011 010 001
                x = min(xx, yy + 2, zz + 2, tt + 2)  # 000
                y = min(xx + 1, yy + 1, zz + 3, tt + 1)  # 111
                z = min(xx + 1, yy + 1, zz + 1, tt + 3)  # 100
                t = min(xx + 2, yy, zz + 2, tt + 2)  # 011

            return x, y, z, t

        return dfs(root)[0]


if __name__ == '__main__':
    s = Solution()
    # print(s.temperatureTrend(temperatureA=[5, 10, 16, -6, 15, 11, 3],
    #                          temperatureB=[16, 22, 23, 23, 25, 3, -16]))
    # print(s.ballGame(num=4,
    #                  plate=["..E.", ".EOW", "..W."]))
    # print(s.ballGame(num=5,
    #                  plate=[".....", "..E..", ".WO..", "....."]))
    print(s.ballGame(6,
                     ["....", ".EE.", "O.E.", "...."]))
