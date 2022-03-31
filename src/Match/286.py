from typing import List


class Solution:
    def findDifference(self, nums1: List[int], nums2: List[int]) -> List[List[int]]:
        s1, s2 = set(nums1), set(nums2)
        res1, res2 = [], []
        for i in s1:
            if i not in s2:
                res1.append(i)
        for i in s2:
            if i not in s1:
                res2.append(i)
        return [res1, res2]

    def minDeletion(self, nums: List[int]) -> int:
        length = len(nums)
        l, r = 0, 0
        res, index = 0, 0
        while l < length:
            r = l + 1
            while r < length and nums[r] == nums[l]:
                r += 1
            count = r - l
            if count > 1:
                if index % 2 == 0:
                    res += (count - 1)
                    index += 1
                else:
                    res += (count - 2)
                    index += 2
            else:
                index += 1
            l = r
        if (length - res) % 2 != 0:
            res += 1
        return res

    def kthPalindrome(self, queries: List[int], intLength: int) -> List[int]:
        res = [-1] * len(queries)
        half = (intLength + 1) // 2
        size = 9 * (10 ** (half - 1))
        for i in range(len(queries)):
            query = queries[i]
            if query <= size:
                left = (10 ** (half - 1)) + query - 1
                right = str(left)[::-1] if intLength % 2 == 0 else str(left)[-2::-1]
                res[i] = int(str(left) + right)
        return res

    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        # dp = [0] * (k + 1)
        # size = 0
        # for pile in piles:
        #     n = len(pile)
        #     size = min(size + n, k)
        #     for i in range(1, n):
        #         pile[i] += pile[i - 1]
        #     for j in range(size, 0, -1):
        #         for w in range(min(n, j)):
        #             dp[j] = max(dp[j], dp[j - w - 1] + pile[w])
        # return dp[k]

        # f = [0] * (k + 1)
        # sum_n = 0
        # for pile in piles:
        #     n = len(pile)
        #     for i in range(1, n):
        #         pile[i] += pile[i - 1]  # pile 前缀和
        #     sum_n = min(sum_n + n, k)  # 优化：j 从前 i 个栈的大小之和开始枚举（不超过 k）
        #     for j in range(sum_n, 0, -1):
        #         f[j] = max(f[j], max(f[j - w - 1] + pile[w] for w in range(min(n, j))))  # w 从 0 开始，物品体积为 w+1
        # return f[k]

        dp = [0] * (k + 1)
        for pile in piles:
            temp = dp[:]
            s = 0
            for i in range(min(k, len(pile))):
                s += pile[i]
                for j in range(i + 1, k + 1):
                    temp[j] = max(temp[j], dp[j - i - 1] + s)
            dp = temp
        return dp[k]


if __name__ == '__main__':
    solution = Solution()
    # print(solution.kthPalindrome(queries=[1, 2, 3, 4, 5, 90], intLength=3))
    # print("123456"[5::-1])
    arr = [48, 14, 23, 38, 33, 79, 3, 52, 73, 58, 49, 23, 74, 44, 69, 76, 83, 41, 46, 32, 28]
    # print(sum(arr[:10]))
    print(solution.maxValueOfCoins([[48, 14, 23, 38, 33, 79, 3, 52, 73, 58, 49, 23, 74, 44, 69, 76, 83, 41, 46, 32, 28]]
                                   , 10))
