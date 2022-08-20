from collections import defaultdict, deque
from typing import List


class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        res = 0
        n = len(nums)
        for i in range(0, n - 2):
            for j in range(i + 1, n - 1):
                for k in range(j + 1, n):
                    if nums[k] - nums[j] == nums[j] - nums[i] and nums[j] - nums[i] == diff:
                        res += 1

        return res

    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        dp = defaultdict(set)
        for edge in edges:
            a, b = edge
            dp[a].add(b)
            dp[b].add(a)
        restricted = set(restricted)
        visited = set()
        q = deque([0])
        while q:
            temp = q.popleft()
            visited.add(temp)
            nexts = dp[temp]
            for next in nexts:
                if next not in restricted and next not in visited:
                    q.append(next)

        return len(visited)

    def validPartition(self, nums: List[int]) -> bool:
        def check(arr):
            if len(arr) == 2:
                return arr[0] == arr[1]
            if len(arr) == 3:
                if arr[0] == arr[1] and arr[1] == arr[2]:
                    return True
                if arr[0] - arr[1] == -1 and arr[1] - arr[2] == -1:
                    return True
            return False

        n = len(nums)
        dp = [False] * (n + 1)
        dp[0] = True
        dp[1] = False
        for i in range(2, n + 1):
            a = check(nums[i - 2: i]) and dp[i - 2]
            b = False
            if i >= 3:
                b = check(nums[i - 3: i]) and dp[i - 3]
            dp[i] = a or b
        return dp[n]

    def longestIdealString(self, s: str, k: int) -> int:
        arr = list(map(lambda x: ord(x) - ord('a'), list(s)))
        n = len(s)
        dp = [0] * 26
        dp[arr[0]] = 1
        for i in range(1, n):
            cur = arr[i]
            left = max(cur - k, 0)
            right = min(cur + k, 25)
            temp = 1
            for j in range(left, right + 1):
                temp = max(temp, dp[j] + 1)
            dp[cur] = temp
        return max(dp)


if __name__ == '__main__':
    s = Solution()
    # s.reachableNodes(n=7, edges=[[0, 1], [1, 2], [3, 1], [4, 0], [0, 5], [5, 6]], restricted=[4, 5])
    # print(s.validPartition(nums=[4, 4, 4, 5, 6]))
    print(s.longestIdealString(s="acfgbd", k=2))
