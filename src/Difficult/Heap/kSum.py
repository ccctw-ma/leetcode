from heapq import heappop, heappush
from typing import List


class Solution:
    def kSum(self, nums: List[int], k: int) -> int:

        tot = 0
        for i, x in enumerate(nums):
            if x >= 0:
                tot += x
            else:
                nums[i] = -x
        nums.sort()
        h = [(0, 0)]
        while k > 1:
            k -= 1
            sub_sum, i = heappop(h)
            if i < len(nums):
                heappush(h, (sub_sum + nums[i], i + 1))
                if i:
                    heappush(h, (sub_sum + nums[i] - nums[i - 1], i + 1))

        return tot - h[0][0]


if __name__ == '__main__':
    s = Solution()
    print(s.kSum(nums=[1, -2, 3, 4, -10, 12], k=16))
