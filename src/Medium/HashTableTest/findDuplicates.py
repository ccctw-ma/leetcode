from typing import List


class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        n = len(nums)
        for i in range(n):
            nums[(nums[i] - 1) % n] += n
        res = []
        for i, num in enumerate(nums):
            if num > n * 2:
                res.append(i + 1)
        return res
