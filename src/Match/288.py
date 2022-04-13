import bisect
import heapq
from bisect import bisect_right
from itertools import accumulate, permutations
from typing import List


class Solution:
    def largestInteger(self, num: int) -> int:
        length = len(str(num))
        odd_index = []
        even_index = []
        odd = []
        even = []
        index = 0
        while num:
            n = num % 10
            num //= 10
            if n % 2:
                odd.append(n)
                odd_index.append(index)
            else:
                even.append(n)
                even_index.append(index)
            index += 1
        res = [0] * length
        odd.sort()
        even.sort()
        for a, b in zip(odd_index, odd):
            res[a] = b
        for a, b in zip(even_index, even):
            res[a] = b
        ans = 0
        for index, t in enumerate(res):
            t = t * (10 ** index)
            ans += t
        return ans

    def minimizeResult(self, expression: str) -> str:
        left, right = expression.split("+")
        min_val = 1e9
        a, b, c, d = 1, 1, 1, 1
        res = ""
        for i in range(len(left)):
            for j in range(len(right)):
                if i == 0:
                    a = 1
                else:
                    a = int(left[0:i])
                b = int(left[i:])
                if j == 0:
                    d = 1
                else:
                    d = int(right[len(right) - j:])
                c = int(right[0: len(right) - j])
                r = a * (b + c) * d
                if r <= min_val:
                    min_val = r
                    res = left[0:i] + '(' + left[i:] + "+" + right[0: len(right) - j] + ')' + right[len(right) - j:]
        return res

    def maximumProduct(self, nums: List[int], k: int) -> int:
        mod = 1e9 + 7
        res = 1
        heapq.heapify(nums)
        while k:
            c = heapq.heappop(nums)
            c += 1
            k -= 1
            heapq.heappush(nums, c)
        for n in nums:
            res *= n
            res %= mod
        return int(res)

    # def maximumBeauty(self, ff: List[int], nf: int, a: int, b: int, c: int) -> int:
    #     ff.sort()
    #     k = 0
    #     while ff and ff[-1] >= a:
    #         k += 1
    #         ff.pop()
    #
    #     ans = 0
    #     if ff and sum(a - 1 - x for x in ff) <= nf:
    #         ans = k * b + (a - 1) * c
    #
    #     pf = [0] + list(accumulate(ff))
    #
    #     def calc():
    #         nonlocal ans
    #         if not ff:
    #             ans = max(ans, k * b)
    #             return
    #         l, r = ff[0], ff[-1] + nf
    #         while l < r:
    #             mid = (l + r + 1) // 2
    #             loc = bisect_right(ff, mid)
    #             if mid * loc - pf[loc] <= nf:
    #                 l = mid
    #             else:
    #                 r = mid - 1
    #         if l >= a - 1:
    #             ans = max(ans, k * b + (a - 1) * c)
    #         else:
    #             ans = max(ans, k * b + l * c)
    #
    #     calc()
    #
    #     while ff and nf >= a - ff[-1]:
    #         k += 1
    #         nf -= a - ff[-1]
    #         ff.pop()
    #
    #         calc()
    #
    #     return ans

    # 二分查找最优结果
    def maximumBeauty(self, flowers: List[int], newFlowers: int, target: int, full: int, partial: int) -> int:
        flowers.sort()
        ps = [0] + list(accumulate(flowers))
        flowers = [0] + flowers
        n = len(flowers)
        right = n - 1
        while right >= 0 and flowers[right] >= target:
            right -= 1
        cur_sum, ans = 0, 0
        while right >= 0 and cur_sum <= newFlowers:
            head, tail = 0, right
            while head < tail:
                mid = (head + tail + 1) // 2
                t = mid * flowers[mid] - ps[mid]
                if cur_sum + t <= newFlowers:
                    head = mid
                else:
                    tail = mid - 1
            x = newFlowers - cur_sum - (head * flowers[head] - ps[head])
            y = min(flowers[head] + x // head if head else 0, target - 1)
            ans = max(ans, (n - 1 - right) * full + y * partial)
            cur_sum += target - flowers[right]
            right -= 1
        return ans


if __name__ == '__main__':
    solution = Solution()
    # print(solution.largestInteger(65875))
    # print(int("aa"[0:0]))
    # print(solution.minimizeResult("12+34"))
    # print(solution.maximumProduct(nums=[6, 3, 3, 2], k=2))
    arr = [1, 2, 3, 4, 5, 9, 10]
    # print(list(accumulate(arr)))
    print(solution.maximumBeauty(flowers=[1, 3, 1, 1], newFlowers=7, target=6, full=12, partial=1))
    print(list(permutations([1, 2, 3])))
