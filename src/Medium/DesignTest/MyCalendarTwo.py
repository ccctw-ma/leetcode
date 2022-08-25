class MyCalendarTwo:
    class Node:
        def __init__(self):
            self.left = None
            self.right = None
            self.val = 0
            self.add = 0

    def __init__(self):
        self.root = self.Node()
        self.N = 10 ** 9

    def update(self, node: Node, start: int, end: int, l: int, r: int, val: int):
        if start <= l and r <= end:
            node.val += val
            node.add += val
            return
        self.pushDown(node)
        mid = (l + r) >> 1
        if start <= mid:
            self.update(node.left, start, end, l, mid, val)
        if end > mid:
            self.update(node.right, start, end, mid + 1, r, val)
        self.pushUp(node)

    def pushUp(self, node: Node):
        node.val = max(node.left.val, node.right.val)

    # 动态开点
    def pushDown(self, node: Node):
        if node.left is None:
            node.left = self.Node()
        if node.right is None:
            node.right = self.Node()
        if node.add == 0:
            return
        node.left.val += node.add
        node.right.val += node.add
        node.left.add += node.add
        node.right.add += node.add
        node.add = 0

    def query(self, node: Node, start: int, end: int, l: int, r: int):
        if start <= l and r <= end:
            return node.val
        self.pushDown(node)
        mid = (l + r) >> 1
        res = 0
        if start <= mid:
            res = self.query(node.left, start, end, l, mid)
        if end > mid:
            res = max(res, self.query(node.right, start, end, mid + 1, r))
        return res

    def book(self, start: int, end: int) -> bool:
        if self.query(self.root, start, end - 1, 0, self.N) == 2:
            return False
        self.update(self.root, start, end - 1, 0, self.N, 1)
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(start,end)
if __name__ == '__main__':
    s = MyCalendarTwo()
    print(s.book(10, 20))
