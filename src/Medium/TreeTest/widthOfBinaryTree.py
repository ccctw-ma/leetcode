# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque
from typing import Optional

from src.Easy.TreeTest.TreeNode import TreeNode


class Solution:
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        q = deque([(root, 0)])
        max_len = 1
        while q:
            n = len(q)
            max_len = max(max_len, q[-1][1] - q[0][1] + 1)
            for _ in range(n):
                temp = q.popleft()
                node, index = temp
                if node.left:
                    q.append((node.left, index * 2 + 1))
                if node.right:
                    q.append((node.right, index * 2 + 2))
        return max_len
