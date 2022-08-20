from collections import deque
from typing import List

from fontTools.misc.arrayTools import pairwise


class Solution:
    def sequenceReconstruction(self, nums: List[int], sequences: List[List[int]]) -> bool:

        n = len(nums)
        graph = [[] for _ in range(n)]
        indeg = [0] * n
        for seq in sequences:
            for x, y in pairwise(seq):
                graph[x - 1].append(y - 1)
                indeg[y - 1] += 1
        q = deque([i for i, d in enumerate(indeg) if d == 0])
        while q:
            if len(q) > 1:
                return False
            temp = q.popleft()
            for next in graph[temp]:
                indeg[next] -= 1
                if indeg[next] == 0:
                    q.append(next)
        return True


if __name__ == '__main__':
    s = Solution()
    print(s.sequenceReconstruction(nums=[1, 2, 3], sequences=[[1, 2], [1, 3], [2, 3]]))
