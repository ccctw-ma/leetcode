from typing import List


# 动态开店 节省空间
class SegmentTree:
    class Node:
        def __init__(self):
            self.left = None
            self.right = None
            self.val = 0
            self.add = 0

    def __init__(self, nums: List[int]):
        self.n = len(nums)
        self.nums = nums

    def pushDown(self, node: Node, leftNum: int, rightNum: int):
        if node.left is None:
            node.left = self.Node()
        if node.right is None:
            node.right = self.Node()
        if node.add == 0:
            return
        node.left.val += node.add * leftNum
        node.right.val += node.add * rightNum
        node.left.add += node.add
        node.right.add += node.add
        node.add = 0

    def pushUp(self, node: Node):
        node.val = node.left.val + node.right.val

    def update(self, node: Node, start: int, end: int, l: int, r: int, val: int):
        if l <= start and end <= r:
            node.val += (end - start + 1) * val
            # 添加懒惰标记
            node.add += val
            return
        mid = (start + end) >> 1
        self.pushDown(node, mid - start + 1, end - mid)
        if l <= mid:
            self.update(node.left, start, mid, l, r, val)
        if r > mid:
            self.update(node.right, mid + 1, end, l, r, val)
        self.pushUp(node)

    def query(self, node: Node, start: int, end: int, l: int, r: int):
        # 区间包含 查询区间[l, r]包含区间[start, end] 就可以直接取值
        if l <= start and end <= r:
            return node.val
        res = 0
        mid = (start + end) >> 1
        self.pushDown(node, mid - start + 1, end - mid)
        if l <= mid:
            res += self.query(node.left, start, mid, l, r)
        if r > mid:
            res += self.query(node.right, mid + 1, end, l, r)
        return res


if __name__ == '__main__':
    print("Hello SegmentTree")
