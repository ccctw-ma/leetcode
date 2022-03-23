# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from typing import Optional

from src.Easy.TreeTest.TreeNode import TreeNode


class Solution:

    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        if not root:
            return False

        val_set = set()
        flag = False

        def dfs(node: Optional[TreeNode]):
            if node:
                nonlocal flag
                nonlocal val_set
                if (k - node.val) in val_set:
                    flag = True
                    return
                val_set.add(node.val)
                dfs(node.left)
                dfs(node.right)

        dfs(root)
        return flag


if __name__ == '__main__':
    s = Solution()
