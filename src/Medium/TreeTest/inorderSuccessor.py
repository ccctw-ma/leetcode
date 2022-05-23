# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from src.Medium.TreeTest.TreeNode import TreeNode


class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> TreeNode:
        res = []

        def inOrder(root, res):
            if root is None:
                return
            inOrder(root.left, res)
            res.append(root)
            inOrder(root.right, res)

        inOrder(root, res)
        for i, node in enumerate(res):
            if node.val == p.val and i != len(res) - 1:
                return res[i + 1]
        return None
