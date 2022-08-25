from functools import lru_cache
from typing import List


class Solution:
    def canPartitionKSubsets1(self, nums: List[int], k: int) -> bool:
        target, remain = divmod(sum(nums), k)
        if remain:
            return False
        n = len(nums)
        dp = [-1] * (1 << n)
        dp[0] = 0
        for s in range(1, len(dp)):
            for i, v in enumerate(nums):
                if s & 1 << i == 0:
                    continue
                s1 = s & ~(1 << i)
                if dp[s1] >= 0 and dp[s1] + v <= target:
                    dp[s] = (dp[s1] + v) % target
        return dp[-1] == 0

    def canPartitionKSubsets2(self, nums: List[int], k: int) -> bool:

        @lru_cache(None)
        def dfs(state, summ):

            if state == (1 << n) - 1:  # 所有整数均已划分，结束递归，并返回True
                return True

            for j in range(n):
                if summ + nums[j] > target:  # nums已升序排列，当前数字不行，后续肯定也不行
                    break
                if state & (1 << j) == 0:  # nums[i]暂未被划分
                    next_state = state + (1 << j)  # 划分nums[i]
                    if dfs(next_state, (summ + nums[j]) % target):  # 划分nums[i]能形成有效方案
                        return True
            return False

        total = sum(nums)
        if total % k != 0:
            return False
        n = len(nums)
        target = total // k  # 目标非空子集的和
        nums.sort()  # 升序排列
        if nums[-1] > target:  # 最大值超过目标子集和，无法划分
            return False
        return dfs(0, 0)

    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        if sum(nums) % k != 0:
            return False
        target = sum(nums) // k
        n = len(nums)
        nums.sort(reverse=True)
        if nums[0] > target:
            return False
        buckets = [0] * k

        def dfs(idx):
            print(idx)
            if idx == n:
                return True
            for i in range(k):
                if buckets[i] + nums[idx] > target:
                    continue
                if i > 0 and buckets[i] == buckets[i - 1]:
                    continue
                buckets[i] += nums[idx]
                if dfs(idx + 1):
                    return True
                buckets[i] -= nums[idx]
            return False

        return dfs(0)


if __name__ == '__main__':
    s = Solution()
    # print(s.canPartitionKSubsets(nums=[4, 3, 2, 3, 5, 2, 1], k=4))
    print(s.canPartitionKSubsets([3, 9, 4, 5, 8, 8, 7, 9, 3, 6, 2, 10, 10, 4, 10, 2]
                                 , 10))
