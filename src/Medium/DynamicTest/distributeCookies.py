from itertools import product
from math import inf
from typing import List


class Solution:
    def distributeCookies(self, cookies: List[int], k: int) -> int:
        res, n = sum(cookies), len(cookies)
        dp = [0] * k
        cookies.sort(reverse=True)

        def tb(index):
            nonlocal res
            if max(dp) > res:
                return
            if index == n:
                res = min(res, max(dp))
                return
            for i in range(k):
                if i and dp[i] == dp[i - 1]:
                    continue
                dp[i] += cookies[index]
                tb(index + 1)
                dp[i] -= cookies[index]

        tb(0)
        return res

    def distributeCookies2(self, cookies: List[int], k: int) -> int:
        n = len(cookies)
        bags = [0] * k
        self.res = sum(cookies)

        def dfs(bags, i, val):
            if val >= self.res:
                return
            if i == n:
                self.res = min(self.res, val)
                return
            for j in range(k):
                if j > 0 and bags[j] == bags[j - 1]:
                    continue
                bags[j] += cookies[i]
                newval = max(val, bags[j])
                dfs(bags, i + 1, newval)
                bags[j] -= cookies[i]

        dfs(bags, 0, 0)
        return self.res


if __name__ == '__main__':
    s = Solution()
    print(s.distributeCookies(cookies=[8, 15, 10, 20, 8], k=2))
    # print(list(product(range(2), repeat=5)))
