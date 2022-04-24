from itertools import accumulate
from typing import List


class Solution:
    def maxRotateFunction(self, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        mul, max_value = 0, 0
        for i, num in enumerate(nums):
            pre_sum[i + 1] = pre_sum[i] + num
            mul += num * i
        max_value = mul
        for k in range(1, n):
            al, ar, am = 0, n - k, k
            bl, br, bm = n - k, n, -(n - k)
            a = (pre_sum[ar] - pre_sum[al]) * am
            b = (pre_sum[br] - pre_sum[bl]) * bm
            max_value = max(max_value, mul + a + b)
        return max_value


if __name__ == '__main__':
    print(Solution().maxRotateFunction(nums=[4, 3, 2, 6]))
