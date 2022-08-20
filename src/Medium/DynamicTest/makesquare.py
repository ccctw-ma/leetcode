from typing import List


class Solution:

    # o(4 ^ n) 会超时 不好
    def makesquare(self, matchsticks: List[int]) -> bool:
        ss = sum(matchsticks)
        if ss % 4 != 0:
            return False
        ew = ss // 4
        n = len(matchsticks)
        if max(matchsticks) > ew:
            return False
        edges = [0] * 4
        matchsticks.sort(reverse=True)

        def dfs(index):
            if index == n:
                return True
            stick = matchsticks[index]
            for i in range(4):
                edges[i] += stick
                if edges[i] <= ew and dfs(index + 1):
                    return True
                edges[i] -= stick
            return False

        return dfs(0)

    def makesquare2(self, matchsticks: List[int]) -> bool:
        target, remain = divmod(sum(matchsticks), 4)
        if remain:
            return False
        n = len(matchsticks)
        dp = [-1] * (1 << n)
        dp[0] = 0
        for s in range(1, len(dp)):
            for i, k in enumerate(matchsticks):
                if s & 1 << i == 0:
                    continue
                s1 = s & ~(1 << i)
                if dp[s1] >= 0 and dp[s1] + k <= target:
                    dp[s] = (dp[s1] + k) % target
        return dp[-1] == 0

    def makesquare3(self, matchsticks: List[int]) -> bool:
        edge, remain = divmod(sum(matchsticks), 4)
        vis = [False] * len(matchsticks)
        if remain:
            return False
        matchsticks.sort(reverse=True)

        def backtrace(u, st, cnt):
            if u == edge:
                if cnt + 1 == 4:
                    return True
                u = st = 0
                cnt += 1
            for i, x in enumerate(matchsticks[st:], st):
                if vis[i] or i > 0 and x == matchsticks[i - 1] and not vis[i - 1]:
                    continue
                if u + x > edge:
                    continue
                vis[i] = True
                if backtrace(u + x, i + 1, cnt):
                    return True
                vis[i] = False
                if u == 0 or u + x == edge:
                    break
            return False

        return backtrace(0, 0, 0)


if __name__ == '__main__':
    s = Solution()
    # print(s.makesquare(matchsticks=[1, 1, 2, 2, 2]))
    # print(s.makesquare([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]))
    print(s.makesquare2(
        [1569462, 2402351, 9513693, 2220521, 7730020, 7930469, 1040519, 5767807, 876240, 350944, 4674663, 4809943,
         8379742, 3517287, 8034755]))
