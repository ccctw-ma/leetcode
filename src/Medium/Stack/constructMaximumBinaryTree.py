# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
import itertools
from typing import List, Optional

from src.Easy.TreeTest.TreeNode import TreeNode


class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        n = len(nums)
        stk = list()
        tree = [None] * n

        for i in range(n):
            tree[i] = TreeNode(nums[i])
            while stk and nums[i] > nums[stk[-1]]:
                tree[i].left = tree[stk[-1]]
                stk.pop()
            if stk:
                tree[stk[-1]].right = tree[i]
            stk.append(i)

        return tree[stk[0]]


if __name__ == '__main__':
    s = Solution()
    print(s.constructMaximumBinaryTree(nums=[3, 2, 1, 6, 0, 5]))
    arr = [1, 2, 3]
    # arr = [i if i == 2 else 2 for i in arr]
    print(list(itertools.permutations([1, 2, 3, 4], 4)))

    print("i love eating burger".split(" "))
