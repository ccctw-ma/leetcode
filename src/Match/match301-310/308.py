import heapq
from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        n = len(nums)
        m = len(queries)
        res = [0] * m
        nums.sort()
        pre_sum = list(accumulate(nums))
        for i in range(m):
            tar = queries[i]
            flag = False
            for j in range(n):
                if pre_sum[j] > tar:
                    res[i] = j
                    flag = True
                    break
            if not flag:
                res[i] = n
        return res

    def removeStars(self, s: str) -> str:
        stk = []
        for c in s:
            if c == '*':
                if stk:
                    stk.pop()
            else:
                stk.append(c)
        return ''.join(stk)

    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        n = len(garbage)
        ms, ps, gs = [], [], []

        for i, s in enumerate(garbage):
            m = s.count('M')
            p = s.count('P')
            g = s.count('G')
            ms.append(m)
            ps.append(p)
            gs.append(g)
        pms = list(accumulate(ms))
        pps = list(accumulate(ps))
        pgs = list(accumulate(gs))
        res = 0
        for i in range(n):
            res += ms[i]
            if pms[i] == pms[-1]:
                break
            if i < n - 1:
                res += travel[i]
        for i in range(n):
            res += ps[i]
            if pps[i] == pps[-1]:
                break
            if i < n - 1:
                res += travel[i]
        for i in range(n):
            res += gs[i]
            if pgs[i] == pgs[-1]:
                break
            if i < n - 1:
                res += travel[i]

        return res

    def buildMatrix(self, k: int, rowConditions: List[List[int]], colConditions: List[List[int]]) -> List[List[int]]:

        ans = [[0] * k for _ in range(k)]
        degreeRow = [0] * (k + 1)
        graphRow = defaultdict(list)
        for a, b in rowConditions:
            graphRow[a].append(b)
            degreeRow[b] += 1
        degreeCol = [0] * (k + 1)
        graphCol = defaultdict(list)
        for a, b in colConditions:
            graphCol[a].append(b)
            degreeCol[b] += 1
        # 先判断行的情况

        xi, yi = 0, 0
        rowBuc = {i: -1 for i in range(1, k + 1)}
        q = deque()
        visited = set()
        for i in range(1, k + 1):
            if degreeRow[i] == 0:
                q.append(i)
        while q:
            temp = q.popleft()
            visited.add(temp)
            rowBuc[temp] = xi
            xi += 1
            for next in graphRow[temp]:
                degreeRow[next] -= 1
                if degreeRow[next] == 0:
                    q.append(next)
        if sum(degreeRow) != 0:
            return []

        colBuc = {i: -1 for i in range(1, k + 1)}
        q = deque()
        visited = set()
        for i in range(1, k + 1):
            if degreeCol[i] == 0:
                q.append(i)
        while q:
            temp = q.popleft()
            visited.add(temp)
            colBuc[temp] = yi
            yi += 1
            for next in graphCol[temp]:
                degreeCol[next] -= 1
                if degreeCol[next] == 0:
                    q.append(next)
        if sum(degreeCol) != 0:
            return []
        for i in range(1, k + 1):
            x = rowBuc[i]
            if x == -1:
                x = xi + 1
                xi += 1
            y = colBuc[i]
            if y == -1:
                y = yi + 1
                yi += 1
            ans[x][y] = i
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.buildMatrix(k=3, rowConditions=[[1, 2], [2, 3], [3, 1], [2, 3]], colConditions=[[2, 1]]))
    print(s.buildMatrix(k=3, rowConditions=[[1, 2], [3, 2]], colConditions=[[2, 1], [3, 2]]
                        ))
