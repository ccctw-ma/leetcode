from collections import deque


class Solution:
    def findTheWinner(self, n: int, k: int) -> int:
        count = n
        dp = [1] * n
        index = 0

        def find(arr, i, n):
            i = (i + 1) % n
            while arr[i] == 0:
                i = (i + 1) % n
            return i

        def findK(arr, i, n, k):
            for t in range(k - 1):
                i = find(arr, i, n)
            return i

        while count != 1:
            ik = findK(dp, index, n, k)
            dp[ik] = 0
            count -= 1
            index = find(dp, ik, n)
        return index + 1

    def findTheWinner2(self, n: int, k: int) -> int:
        q = deque(range(1, n + 1))
        while len(q) > 1:
            for _ in range(k - 1):
                q.append(q.popleft())
            q.popleft()
        return q[0]

    def findTheWinner3(self, n: int, k: int) -> int:
        winner = 1
        for i in range(2, n + 1):
            winner = (k + winner - 1) % i + 1
        return winner


if __name__ == '__main__':
    print(Solution().findTheWinner(1, 1))
