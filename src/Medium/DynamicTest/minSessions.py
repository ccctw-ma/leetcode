import bisect
from typing import List


class Solution:
    def minSessions(self, tasks: List[int], sessionTime: int) -> int:
        if sum(tasks) <= sessionTime:
            return 1

        n = len(tasks)
        tasks.sort(reverse=True)
        dp, num = [], 0

        def check(count):
            nonlocal dp, num
            num = count
            dp = [0] * num
            return tb(0)

        def tb(index):
            nonlocal dp, num
            if index == n:
                return True
            for i in range(num):
                if dp[i] + tasks[index] > sessionTime:
                    continue
                if i > 0 and dp[i] == dp[i - 1]:
                    continue
                dp[i] += tasks[index]
                if tb(index + 1):
                    return True
                dp[i] -= tasks[index]
            return False

        l, r = 1, n
        while l < r:
            mid = (l + r) // 2
            flag = check(mid)
            print(mid, flag)
            if flag:
                r = mid
            else:
                l = mid + 1
        return l


if __name__ == '__main__':
    s = Solution()
    print(s.minSessions(tasks=[3, 1, 3, 1, 1], sessionTime=8))
