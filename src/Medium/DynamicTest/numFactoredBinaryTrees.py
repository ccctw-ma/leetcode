from typing import List


class Solution:
    def numFactoredBinaryTrees(self, arr: List[int]) -> int:
        mod = 10 ** 9 + 7
        n = len(arr)
        arr.sort()
        dp = [1] * n
        buc = {v: i for i, v in enumerate(arr)}
        for i in range(n):
            for j in range(i):
                a, b = arr[i], arr[j]
                if (a / b) in buc:
                    dp[i] += dp[j] * dp[buc[a // b]]
        return sum(dp) % mod


if __name__ == '__main__':
    s = Solution()
    # print(s.numFactoredBinaryTrees(arr=[2, 4, 5, 10]))
    # print(s.numFactoredBinaryTrees([18, 3, 6, 2]))
    print(s.numFactoredBinaryTrees(
        [2, 4, 8, 12, 16]))
    print(s.numFactoredBinaryTrees(
        [45, 42, 2, 18, 23, 1170, 12, 41, 40, 9, 47, 24, 33, 28, 10, 32, 29, 17, 46, 11, 759, 37, 6, 26, 21, 49, 31, 14,
         19, 8, 13, 7, 27, 22, 3, 36, 34, 38, 39, 30, 43, 15, 4, 16, 35, 25, 20, 44, 5, 48]))
