# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
import json
from collections import deque
from math import inf

from src.Medium.TreeTest import TreeNode


class Codec:

    def serialize(self, root: TreeNode) -> str:
        """Encodes a tree to a single string.
        """

        if root is None:
            return " "
        return ','.join([str(root.val), self.serialize(root.left), self.serialize(root.right)])

    def deserialize(self, data: str) -> TreeNode:
        """Decodes your encoded data to tree.
        """

        queue = deque(data.split(","))

        def dfs():
            node = queue.popleft()
            if node == ' ':
                return None
            root = TreeNode(int(node))
            root.left = dfs()
            root.right = dfs()
            return root
        return dfs()

# Your Codec object will be instantiated and called as such:
# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# tree = ser.serialize(root)
# ans = deser.deserialize(tree)
# return ans
