# Definition for a Node.
from typing import List


class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children


class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if root is None:
            return []
        queue = [root]
        res = []
        while len(queue):
            temp = []
            vals = []
            for node in queue:
                vals.append(node.val)
                if node.children:
                    temp.extend(node.children)
            queue = temp
            res.append(vals)
        return res


if __name__ == '__main__':
    # print(Solution().levelOrder())
    arr1 = [1, 2, 3, 4]
    arr2 = [5, 6, 7, 8]
    arr1.extend(arr2)
    print(arr1)
