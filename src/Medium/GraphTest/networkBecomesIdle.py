from collections import deque
from typing import List


class Node:
    def __init__(self, val):
        self.val = val
        self.connections = []


class Solution:
    def networkBecomesIdle(self, edges: List[List[int]], patience: List[int]) -> int:
        n = len(patience)
        arr = [Node(i) for i in range(n)]
        for edge in edges:
            a, b = edge
            arr[a].connections.append(b)
            arr[b].connections.append(a)
        visited = set()
        visited.add(0)
        queue = [arr[0]]
        step = 0
        while queue:
            length = len(queue)
            temp = []
            for i in range(length):
                node = queue[i]
                node.step = step
                for connection in node.connections:
                    if connection not in visited:
                        temp.append(arr[connection])
                        visited.add(connection)
            step += 1
            queue = temp
        res = 0
        for i in range(1, n):
            node = arr[i]
            p = patience[i]
            distance = node.step
            if distance * 2 <= p:
                res = max(res, distance * 2)
            else:
                num = (distance * 2) // p - 1 if (distance * 2) % p == 0 else (distance * 2) // p
                res = max(res, p * num + distance * 2)
        return res + 1


if __name__ == '__main__':
    solution = Solution()
    print(solution.networkBecomesIdle(
        [[3, 8], [4, 13], [0, 7], [0, 4], [1, 8], [14, 1], [7, 2], [13, 10], [9, 11], [12, 14], [0, 6], [2, 12],
         [11, 5], [6, 9], [10, 3]]
        , [0, 3, 2, 1, 5, 1, 5, 5, 3, 1, 2, 2, 2, 2, 1]))

    queue = deque()
