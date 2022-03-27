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


if __name__ == '__main__':
    solution = Solution()
    # print(solution.kthPalindrome(queries=[1, 2, 3, 4, 5, 90], intLength=3))
    print("123456"[5::-1])