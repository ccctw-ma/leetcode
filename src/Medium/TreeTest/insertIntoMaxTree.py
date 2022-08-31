# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from typing import Optional

from src.Easy.TreeTest.TreeNode import TreeNode


class Solution:
    def insertIntoMaxTree(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        def fn(root):
            if root is None:
                return []
            larr = fn(root.left)
            rarr = fn(root.right)
            return larr + [root.val] + rarr

        arr = fn(root)
        arr.append(val)
        n = len(arr)
        lmax, rmax = [-1] * n, [-1] * n
        stk = []
        trees = [None] * n
        for i, num in enumerate(arr):
            trees[i] = TreeNode(num)
            while stk and num > arr[stk[-1]]:
                rmax[stk[-1]] = i
                stk.pop()
            if stk:
                lmax[i] = stk[-1]
            stk.append(i)
        root = None
        for i in range(n):
            if lmax[i] == rmax[i] == -1:
                root = trees[i]
            elif rmax[i] == -1 or (lmax[i] != -1 and arr[lmax[i]] < arr[rmax[i]]):
                trees[lmax[i]].right = trees[i]
            else:
                trees[rmax[i]].left = trees[i]
        return root


if __name__ == '__main__':
    s = Solution()
    arr = [1, 4, 2, 3, 5]
    n = len(arr)
    lmax, rmax = [-1] * n, [-1] * n
    stk = []
    for i, num in enumerate(arr):
        while stk and num > arr[stk[-1]]:
            rmax[stk[-1]] = i
            stk.pop()
        if stk:
            lmax[i] = stk[-1]
        stk.append(i)
    print(lmax, rmax)
