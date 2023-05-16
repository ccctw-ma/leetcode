import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def minPushBox(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])

        def check(x, y) -> bool:
            return m > x >= 0 <= y < n and grid[x][y] != '#'

        b, s, tar = None, None, None
        for i, row in enumerate(grid):
            for j, c in enumerate(row):
                if c == 'B':
                    b = (i, j)
                if c == 'S':
                    s = (i, j)
                if c == 'T':
                    tar = (i, j)
        q = deque([(s[0], s[1], b[0], b[1], 0)])
        vis = set()
        while q:
            px, py, bx, by, step = q.popleft()
            if (bx, by) == tar:
                return step

            for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                pnx, pny = px + dx, py + dy
                if not check(pnx, pny):
                    continue
                # 可以推
                if pnx == bx and pny == by:
                    bnx, bny = bx + dx, by + dy
                    if check(bnx, bny) and (pnx, pny, bnx, bny) not in vis:
                        q.append((pnx, pny, bnx, bny, step + 1))
                        vis.add((pnx, pny, bnx, bny))
                # 不能推
                elif (pnx, pny, bx, by) not in vis:
                        q.appendleft((pnx, pny, bx, by, step))
                        vis.add((pnx, pny, bx, by))

        return -1


# class Solution:
#
#     def minPushBox(self, grid: List[List[str]]) -> int:
#         rows, cols = len(grid), len(grid[0])
#         dirs = ((1, 0, -1, 0), (-1, 0, 1, 0), (0, -1, 0, 1), (0, 1, 0, -1))
#
#         def check(start, target, bpos):
#             if start == target: return True
#             tr, tc = target
#             if (not (rows > tr >= 0 <= tc < cols)) or grid[tr][tc] == '#': return False
#             q = deque([start])
#             vis = {start, bpos}
#             while q:
#                 r, c = q.popleft()
#                 for dr, dc, _, _ in dirs:
#                     nr, nc = r + dr, c + dc
#                     if rows > nr >= 0 <= nc < cols and (nr, nc) not in vis and grid[nr][nc] != '#':
#                         if (nr, nc) == target:
#                             return True
#                         vis.add((nr, nc))
#                         q.append((nr, nc))
#             return False
#
#         for r in range(rows):
#             for c in range(cols):
#                 if grid[r][c] == 'S':
#                     s = (r, c)
#                 elif grid[r][c] == 'B':
#                     b = (r, c)
#                 elif grid[r][c] == 'T':
#                     t = (r, c)
#         if b == t: return 0
#         q = deque([(*b, *s, 0)])
#         vis = {(*b, *s)}  # ！状态是 箱子位置和方向（人的位置）
#         while q:
#             r, c, sr, sc, step = q.popleft()
#             for dr, dc, dr2, dc2 in dirs:
#                 nr, nc = r + dr, c + dc  # 箱子目标位置
#                 nsr, nsc = r + dr2, c + dc2  # 人的目标位置
#                 if rows > nr >= 0 <= nc < cols and (nr, nc, nsr, nsc) not in vis and grid[nr][nc] != '#' and check((sr, sc), (nsr, nsc), (r, c)):
#                     if (nr, nc) == t:
#                         return step + 1
#                     vis.add((nr, nc, nsr, nsc))
#                     q.append((nr, nc, r, c, step + 1))
#         return -1


class Solution:
    def minPushBox(self, grid: List[List[str]]) -> int:
        # 判断p这个点是否为有效点（即在边界之内且不为墙）
        def is_vaild(p: Tuple[int]) -> bool:
            return 0 <= p[0] < m and 0 <= p[1] < n and grid[p[0]][p[1]] != '#'

        # 判断s和t是否连通（传入现在箱子的位置，因为人不能跨越箱子，而箱子又在时刻变动）
        def is_connect(s: Tuple[int], t: Tuple[int], box_pos: Tuple[int]) -> bool:
            q = deque([s])
            dis = {s}
            while q:
                x, y = q.popleft()
                if (x, y) == t:
                    return True
                for p in (x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1):
                    if is_vaild(p) and p not in dis and p != box_pos:
                        dis.add(p)
                        q.append(p)
            return False

        # 初始化各变量
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 'B':
                    box = (i, j)
                elif grid[i][j] == 'T':
                    target = (i, j)
                elif grid[i][j] == 'S':
                    people = (i, j)

        # 状态定义：(0)人在箱子上边 (1)人在箱子下边 (2)人在箱子右面 (3)人在箱子左面
        # 判断人与箱子是否连通，以及给定最初状态
        statue = -1
        x, y = box
        # 方向变量，具体见下方同名变量注释；表示推箱子的位置
        sym_dir_statue = [(x - 1, y), (x + 1, y), (x, y + 1), (x, y - 1)]
        for i, p in enumerate(sym_dir_statue):
            if is_vaild(p) and is_connect(p, people, box):
                statue = i
                break
        if statue == -1: return -1  # 人与箱子不连通

        q = [(box, statue)]
        vis = {q[0]}
        step = 0
        while q:
            tmp = q
            q = []
            # (x, y)表示为当前箱子的位置
            for (x, y), d in tmp:
                # 到终点了，因为BFS可以保证为最短路，所以直接返回即可
                if (x, y) == target:
                    return step

                # 状态方向数组，下标表示状态，元素表示方向；表示箱子下一个移动位置
                dir_statue = [(x + 1, y), (x - 1, y), (x, y - 1), (x, y + 1)]

                # 与上述数组类似，但方向对称 / 相反；表示推箱子的位置
                sym_dir_statue = [(x - 1, y), (x + 1, y), (x, y + 1), (x, y - 1)]

                # p1为箱子下一次要到达的位置
                for i, p1 in enumerate(dir_statue):
                    # p2为状态i需要的推箱子位置
                    p2 = sym_dir_statue[i]

                    # p3为当前人所在的位置（即状态对应推箱子的位置）
                    p3 = sym_dir_statue[d]

                    if is_vaild(p1) and is_vaild(p2) and (p1, i) not in vis and is_connect(p2, p3, (x, y)):
                        vis.add((p1, i))
                        q.append((p1, i))
            step += 1
        return -1


if __name__ == '__main__':
    s = Solution()
    # print(s.minPushBox(grid=[["#", "#", "#", "#", "#", "#"],
    #                          ["#", "T", "#", "#", "#", "#"],
    #                          ["#", ".", ".", "B", ".", "#"],
    #                          ["#", ".", "#", "#", ".", "#"],
    #                          ["#", ".", ".", ".", "S", "#"],
    #                          ["#", "#", "#", "#", "#", "#"]]
    #
    #                    ))
    print(s.minPushBox(
        [[".", ".", "#", ".", ".", ".", ".", ".", ".", "."],
         [".", "#", ".", "#", "B", "#", ".", "#", ".", "."],
         [".", "#", ".", ".", ".", ".", ".", ".", "T", "."],
         ["#", ".", ".", ".", ".", ".", ".", ".", ".", "."],
         [".", ".", ".", ".", ".", ".", ".", ".", ".", "#"],
         [".", ".", ".", ".", ".", ".", ".", ".", "#", "."],
         [".", ".", ".", "#", ".", ".", "#", "#", ".", "."],
         [".", ".", ".", ".", "#", ".", ".", "#", ".", "."],
         [".", "#", ".", "S", ".", ".", ".", ".", ".", "."],
         ["#", ".", ".", "#", ".", ".", ".", ".", ".", "#"]]))
