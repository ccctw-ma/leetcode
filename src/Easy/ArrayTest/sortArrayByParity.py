from typing import List


class Solution:
    def sortArrayByParity(self, nums: List[int]) -> List[int]:
        odd, even = 0, len(nums) - 1
        while odd < even:
            while odd < even and nums[odd] % 2 == 0:
                odd += 1
            while odd < even and nums[even] % 2 == 1:
                even -= 1
            if odd < even:
                nums[odd], nums[even] = nums[even], nums[odd]
                odd += 1
                even -= 1
        return nums


if __name__ == '__main__':
    print(Solution().sortArrayByParity(nums=[0, 1]))
