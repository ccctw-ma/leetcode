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


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)

if __name__ == '__main__':
    obj = NumArray([7, 2, 7, 2, 0])
    obj.update(4, 6)
    obj.update(0, 2)
    obj.update(0, 9)
    print(obj.sumRange(4, 4))
