from typing import List


class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        res = 0
        mul_sum = 1
        left, right = 0, 0
        length = len(nums)
        while right < length:
            mul_sum *= nums[right]
            while mul_sum >= k and left <= right:
                mul_sum /= nums[left]
                left += 1
            res += right - left + 1
            right += 1
        return res


if __name__ == '__main__':
    print(Solution().numSubarrayProductLessThanK(nums=[1, 2, 3], k=0))
