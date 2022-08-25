# Definition for a binary tree node.
from collections import deque


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class CBTInserter:

    def __init__(self, root: TreeNode):
        self.root = root
        self.cnt = 0
        q = deque([root])
        while q:
            self.cnt += 1
            temp = q.popleft()
            if temp.left:
                q.append(temp.left)
            if temp.right:
                q.append(temp.right)

    def insert(self, val: int) -> int:
        self.cnt += 1
        child = TreeNode(val)
        root = self.root
        highbit = self.cnt.bit_length() - 1
        for i in range(highbit - 1, 0, -1):
            if self.cnt & 1 << i:
                root = root.right
            else:
                root = root.left
        if self.cnt & 1:
            root.right = child
        else:
            root.left = child

        return root.val
    def get_root(self) -> TreeNode:
        return self.root


if __name__ == '__main__':
    a = 4
    print(a.bit_length())
