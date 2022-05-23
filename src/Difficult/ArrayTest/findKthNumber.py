from bisect import bisect_left


class Solution:
    def findKthNumber(self, m: int, n: int, k: int) -> int:
        l, r = 1, m * n
        while l < r:
            mid = (l + r) // 2
            count = mid // n * n
            for i in range(mid // n + 1, m + 1):
                count += mid // i
            if count >= k:
                r = mid
            else:
                l = mid + 1
        return l


if __name__ == '__main__':
    print(Solution().findKthNumber(10, 10, 20))
