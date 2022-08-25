import bisect
from math import gcd
from collections import Counter, defaultdict
from typing import List


class Solution:
    def numberOfPairs(self, nums: List[int]) -> List[int]:
        buc = Counter(nums)
        count = 0
        remain = 0
        for c in buc.values():
            count += c // 2
            remain += c % 2
        return [count, remain]

    def maximumSum(self, nums: List[int]) -> int:
        buc = defaultdict(list)
        for num in nums:
            s = 0
            temp = num
            while num:
                s += num % 10
                num //= 10
            buc[s].append(temp)
        # print(buc)
        res = -1
        for arr in buc.values():
            if len(arr) >= 2:
                arr.sort()
                res = max(res, arr[-1] + arr[-2])
        return res

    def smallestTrimmedNumbers(self, nums: List[str], queries: List[List[int]]) -> List[int]:
        res = []
        n = len(nums[0])
        for k, t in queries:
            arr = []
            for i, num in enumerate(nums):
                arr.append((int(num[n - t:]), i))
            arr.sort()
            res.append(arr[k - 1][1])
        return res

    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        buc = Counter(nums)
        arr = sorted(buc.keys())
        numsDivide.sort()
        pre = numsDivide[0]
        for i in range(1, len(numsDivide)):
            pre = gcd(pre, numsDivide[i])
        index = bisect.bisect_left(arr, pre)
        res = -1
        count = 0
        flag = False
        for i in range(index + 1):
            if pre % arr[i] == 0:
                flag = True
                break
            else:
                count += buc[arr[i]]
        return count if flag else res


if __name__ == '__main__':
    s = Solution()
    # print(s.maximumSum(nums=[18, 43, 36, 13, 7]))
    # print(s.smallestTrimmedNumbers(nums=["102", "473", "251", "814"], queries=[[1, 1], [2, 3], [4, 2], [1, 2]]))
    print(s.minOperations(nums=[2, 3, 2, 4, 3], numsDivide=[9, 6, 9, 3, 15]))
    # print(gcd(2, 8))
