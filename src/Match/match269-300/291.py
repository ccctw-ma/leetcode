import bisect
import collections
from typing import List


class Solution:
    def removeDigit(self, number: str, digit: str) -> str:
        max_res = 0
        for index, c in enumerate(number):
            if c == digit:
                num = int(number[:index] + number[index + 1:])
                max_res = max(num, max_res)
        return str(max_res)

    def minimumCardPickup(self, cards: List[int]) -> int:
        buc = collections.defaultdict(list)
        for index, c in enumerate(cards):
            buc[c].append(index)
        min_val = 1000000
        for ll in buc.values():
            if len(ll) == 1:
                continue
            else:
                for i in range(1, len(ll)):
                    min_val = min(ll[i] - ll[i - 1] + 1, min_val)
        return -1 if min_val == 1000000 else min_val

    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        res = 0
        n = len(nums)
        buc = collections.defaultdict(set)
        for i in range(n):
            count = 0
            j = i
            while j < n:
                if nums[j] % p == 0:
                    count += 1
                if count <= k:
                    length = j - i + 1
                    s = str(nums[i:i + length])
                    if s not in buc[length]:
                        res += 1
                        buc[length].add(s)
                    j += 1
                else:
                    break
        return res

    # 动态规划 还是自己没有理解题意 没有做好状态转移方程的推导
    def appealSum(self, s: str) -> int:

        buc = [-1] * 26
        res = 0
        sum_g = 0
        for i, c in enumerate(s):
            sum_g += (i - buc[ord(c) - 97])
            buc[ord(c) - 97] = i
            res += sum_g
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.removeDigit(number="123", digit="3"))
    # print("".join(map(str, [1, 2, 3])))
    print(s.appealSum(s="abbca"))
    # python 是支持按字典序进行字符串比较的
    print("123" <= "123")
