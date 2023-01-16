from typing import List


# 动态开店 节省空间
class DynamicSegmentTree:
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


class SegmentTree:
    def __init__(self, data, merge=max):
        '''
        data:传入的数组
        merge:处理的业务逻辑，例如求和/最大值/最小值，lambda表达式
        '''

        self.data = data
        self.n = len(data)
        #  申请4倍data长度的空间来存线段树节点
        self.tree = [None] * (4 * self.n)  # 索引i的左孩子索引为2i+1，右孩子为2i+2
        self._merge = merge
        if self.n:
            self._build(0, 0, self.n - 1)

    def query(self, ql, qr):
        '''
        返回区间[ql,..,qr]的值
        '''
        return self._query(0, 0, self.n - 1, ql, qr)

    def update(self, index, value):
        # 将data数组index位置的值更新为value,然后递归更新线段树中被影响的各节点的值
        self.data[index] = value
        self._update(0, 0, self.n - 1, index)

    def _build(self, tree_index, l, r):
        '''
        递归创建线段树
        tree_index : 线段树节点在数组中位置
        l, r : 该节点表示的区间的左,右边界
        '''
        if l == r:
            self.tree[tree_index] = self.data[l]
            return
        mid = (l + r) // 2  # 区间中点,对应左孩子区间结束,右孩子区间开头
        left, right = 2 * tree_index + 1, 2 * tree_index + 2  # tree_index的左右子树索引
        self._build(left, l, mid)
        self._build(right, mid + 1, r)
        self.tree[tree_index] = self._merge(self.tree[left], self.tree[right])

    def _query(self, tree_index, l, r, ql, qr):
        '''
        递归查询区间[ql,..,qr]的值
        tree_index : 某个根节点的索引
        l, r : 该节点表示的区间的左右边界
        ql, qr: 待查询区间的左右边界
        '''
        if l == ql and r == qr:
            return self.tree[tree_index]

        mid = (l + r) // 2  # 区间中点,对应左孩子区间结束,右孩子区间开头
        left, right = tree_index * 2 + 1, tree_index * 2 + 2
        if qr <= mid:
            # 查询区间全在左子树
            return self._query(left, l, mid, ql, qr)
        elif ql > mid:
            # 查询区间全在右子树
            return self._query(right, mid + 1, r, ql, qr)

        # 查询区间一部分在左子树一部分在右子树
        return self._merge(self._query(left, l, mid, ql, mid),
                           self._query(right, mid + 1, r, mid + 1, qr))

    def _update(self, tree_index, l, r, index):
        '''
        tree_index:某个根节点索引
        l, r : 此根节点代表区间的左右边界
        index : 更新的值的索引
        '''
        if l == r == index:
            self.tree[tree_index] = self.data[index]
            return
        mid = (l + r) // 2
        left, right = 2 * tree_index + 1, 2 * tree_index + 2
        if index > mid:
            # 要更新的区间在右子树
            self._update(right, mid + 1, r, index)
        else:
            # 要更新的区间在左子树index<=mid
            self._update(left, l, mid, index)
        # 里面的小区间变化了，包裹的大区间也要更新
        self.tree[tree_index] = self._merge(self.tree[left], self.tree[right])


class XianDuanShu:

    def __init__(self, data, func=max):
        self.data = data
        self.n = len(self.data)
        self.f = [0] * (self.n * 4)
        self.func = func
        if self.n:
            self._build(0, 0, self.n - 1)

    def _build(self, index, l, r):
        if l == r:
            self.f[index] = self.data[l]
            return
        mid = (l + r) // 2
        self._build(index * 2 + 1, l, mid)
        self._build(index * 2 + 2, mid + 1, r)
        self.f[index] = self.func(self.f[index * 2 + 1], self.f[index * 2 + 2])

    def calc(self, index, l, r, s, e):
        if l == s and r == e:
            return self.f[index]
        mid = (l + r) // 2
        if e <= mid:
            return self.calc(index * 2 + 1, l, mid, s, e)
        elif s > mid:
            return self.calc(index * 2 + 2, mid + 1, r, s, e)
        return self.func(self.calc(index * 2 + 1, l, mid, s, mid), self.calc(index * 2 + 2, mid + 1, r, mid + 1, e))

    def update(self, index, l, r, x, y):
        if l == r:
            self.f[index] = y
            return
        mid = (l + r) // 2
        if x <= mid:
            self.update(index * 2 + 1, l, mid, x, y)
        else:
            self.update(index * 2 + 2, mid + 1, r, x, y)
        self.f[index] = self.func(self.f[index * 2 + 1], self.f[index * 2 + 2])


if __name__ == '__main__':
    print("Hello SegmentTree")
    data = [1, 2, 3, 4, 5, 6]
    tree = XianDuanShu(data, lambda x, y: x + y)
    tree.update(0, 0, 5, 5, 100)
    print(tree.calc(0, 0, 5, 2, 5))
