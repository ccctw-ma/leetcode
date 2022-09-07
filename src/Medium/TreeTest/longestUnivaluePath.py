# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from typing import Optional

from src.Easy.TreeTest.TreeNode import TreeNode


class Solution:
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:

        res = 1

        def dfs(root: Optional[TreeNode]):
            nonlocal res
            if not root:
                return 0

            l = dfs(root.left)
            r = dfs(root.right)
            ll, rr = 0, 0
            if root.left and root.left.val == root.val:
                ll += l + 1
            if root.right and root.right.val == root.val:
                rr += r + 1
            res = max(res, ll + rr)
            return max(ll, rr)

        return res


if __name__ == '__main__':
    pass
