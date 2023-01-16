# from typing import List
#
# arr = list(range(21))
#
#
# def calc(i):
#     res = 0
#     while i > 0:
#         res += arr[i]
#         i -= i & (-i)
#     return res
#
#
# def update(i, k):
#     while i <= 20:
#         arr[i] += k
#         i += i & (-i)
from typing import List


# i = 1022
# while i > 0:
#     print(i)
#     i -= i & (-i)
#
# i = 17
# while i <= 10000:
#     print(bin(i)[2:])
#     i += i & (-i)

# update(20, )
# print(calc(3))


# 树状数组，基础版本
# 树状数组的核心思想是分治
class BIT:
    def __init__(self, n):
        # 有效索引为 1～n 闭区间
        # self.f 掌管的是它所治的范围的和
        self.f = [0 for i in range(n + 1)]
        self.n = n

    def lowbit(self, x):
        return x & (-x)

    def update(self, i, k):
        # 对索引数组索引i，给予增量k， 关键：那么所有父节点都需要增加
        while i <= self.n:
            self.f[i] += k
            i += self.lowbit(i)

    def query(self, i):
        # 处理1～i的区间和
        ans = 0
        while i > 0:
            ans += self.f[i]
            i -= self.lowbit(i)
        return ans

    def queryRange(self, start, end):
        # 处理 start～end的区间和， 这里用到了前缀和的思想
        return self.query(end) - self.query(start - 1)


class NumArray:
    def __init__(self, nums: List[int]):
        self.nums = nums
        self.bit = BIT(len(nums))
        for i, val in enumerate(nums):
            self.bit.update(i + 1, val)

    def update(self, index: int, val: int) -> None:
        delta = val - self.nums[index]  # 记录增量
        self.nums[index] = val
        self.bit.update(index + 1, delta)  # 更新增量

    def sumRange(self, left: int, right: int) -> int:
        return self.bit.queryRange(left + 1, right + 1)


treeArr = NumArray([1, 2, 3, 4, 5])
treeArr.update(0, 2)
print(treeArr.sumRange(0, 4))
