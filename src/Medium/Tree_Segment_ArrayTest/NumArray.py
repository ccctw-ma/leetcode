from typing import List


class NumArray:

    def __init__(self, nums: List[int]):
        self.arr = [0] * (len(nums) + 5)
        self.nums = nums
        self.n = len(nums)
        for i in range(self.n):
            val = self.nums[i]
            i += 1
            while i <= self.n:
                self.arr[i] += val
                i += i & (-i)

    def update(self, index: int, val: int) -> None:
        diff = val - self.nums[index]
        self.nums[index] = val
        index += 1
        while index <= self.n:
            self.arr[index] += diff
            index += index & (-index)

    def sumRange(self, left: int, right: int) -> int:
        sum_left, sum_right = 0, 0
        while left > 0:
            sum_left += self.arr[left]
            left -= left & (-left)
        right += 1
        while right > 0:
            sum_right += self.arr[right]
            right -= right & (-right)
        return sum_right - sum_left


class NumArray2:
    def __init__(self, nums: List[int]):
        n = len(nums)
        self.n = n
        self.seg = [0] * (n * 4)
        self.build(nums, 0, 0, n - 1)

    def build(self, nums: List[int], node: int, s: int, e: int):
        if s == e:
            self.seg[node] = nums[s]
            return
        m = s + (e - s) // 2
        self.build(nums, node * 2 + 1, s, m)
        self.build(nums, node * 2 + 2, m + 1, e)
        self.seg[node] = self.seg[node * 2 + 1] + self.seg[node * 2 + 2]

    def change(self, index: int, val: int, node: int, s: int, e: int):
        if s == e:
            self.seg[node] = val
            return
        m = s + (e - s) // 2
        if index <= m:
            self.change(index, val, node * 2 + 1, s, m)
        else:
            self.change(index, val, node * 2 + 2, m + 1, e)
        self.seg[node] = self.seg[node * 2 + 1] + self.seg[node * 2 + 2]

    def change2(self, left: int, right: int, val: int, node: int, s: int, e: int):

        pass

    def range(self, left: int, right: int, node: int, s: int, e: int) -> int:
        if left == s and right == e:
            return self.seg[node]
        m = s + (e - s) // 2
        if right <= m:
            return self.range(left, right, node * 2 + 1, s, m)
        if left > m:
            return self.range(left, right, node * 2 + 2, m + 1, e)
        return self.range(left, m, node * 2 + 1, s, m) + self.range(m + 1, right, node * 2 + 2, m + 1, e)

    def update(self, index: int, val: int) -> None:
        self.change(index, val, 0, 0, self.n - 1)

    def sumRange(self, left: int, right: int) -> int:
        return self.range(left, right, 0, 0, self.n - 1)


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)

if __name__ == '__main__':
    # obj = NumArray([7, 2, 7, 2, 0])
    # obj.update(4, 6)
    # obj.update(0, 2)
    # obj.update(0, 9)
    # print(obj.sumRange(4, 4))
    o = NumArray2([1, 2, 3, 4, 5])
    print(o.sumRange(0, 4))
