from collections import deque
from typing import List


class Solution:
    def findMinHeightTrees(self, n: int, edges: List[List[int]]) -> List[int]:
        if n == 1:
            return [0]
        deg = [0] * n
        gra = [[] for _ in range(n)]
        for a, b in edges:
            deg[a] += 1
            deg[b] += 1
            gra[a].append(b)
            gra[b].append(a)
        q = [i for i, d in enumerate(deg) if d == 1]
        count = n
        while count > 2:
            count -= len(q)
            temp = q
            q = []
            for x in temp:
                for y in gra[x]:
                    deg[y] -= 1
                    if deg[y] == 1:
                        q.append(y)
        return q


if __name__ == '__main__':
    solution = Solution()
    print(solution.findMinHeightTrees(n=6, edges=[[3, 0], [3, 1], [3, 2], [3, 4], [5, 4]]))
    deque()
    arr = [1, 2, 3, 4]
