"""
# Definition for a Node.

"""
from collections import defaultdict
from typing import List


class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children


def postorder2(root: 'Node') -> List[int]:
    res = []

    def dfs(node: 'Node'):
        nonlocal res
        if node:
            for child in node.children:
                dfs(child)
            res.append(node.val)

    dfs(root)
    return res


def post_order(root: 'Node') -> List[int]:
    if root is None:
        return []
    ans = []
    st = [root]
    vis = set()
    while st:
        node = st[-1]
        # 如果当前节点为叶子节点或者当前节点的子节点已经遍历过
        if len(node.children) == 0 or node in vis:
            ans.append(node.val)
            st.pop()
            continue
        st.extend(reversed(node.children))
        vis.add(node)
    return ans


def post_order3(root: 'Node') -> List[int]:
    if root is None:
        return []
    ans = []
    st = [root]
    while st:
        node = st.pop()
        ans.append(node.val)
        st.extend(node.children)
    ans.reverse()
    return ans


if __name__ == '__main__':
    dicts = defaultdict(set)
    print(dicts['arr'])
