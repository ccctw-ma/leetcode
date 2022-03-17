"""
# Definition for a Node.

"""

from typing import List


class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children


def preorder2(root: 'Node') -> List[int]:
    res = []

    def pre(node: 'Node'):
        if node is not None:
            res.append(node.val)
            for child in node.children:
                pre(child)

    pre(root)
    return res


def preorder(root: 'Node') -> List[int]:
    if root is None:
        return []
    ans = []
    st = [root]
    while st:
        node = st.pop()
        ans.append(node.val)
        st.extend(reversed(node.children))
    return ans


if __name__ == '__main__':
    o = {}
    print(o.children[0])
    print(preorder(Node()))
