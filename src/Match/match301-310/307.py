import heapq
from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def minNumberOfHours(self, initialEnergy: int, initialExperience: int, energy: List[int],
                         experience: List[int]) -> int:
        es = sum(energy)
        a = max(0, 1 + es - initialEnergy)
        b = 0
        for e in experience:
            if initialExperience > e:
                initialExperience += e
            else:
                add = (e - initialExperience + 1)
                b += add
                initialExperience += add
                initialExperience += e
        return a + b

    def largestPalindromic(self, num: str) -> str:
        dp = [0] * 10
        for c in num:
            dp[ord(c) - ord('0')] += 1
        middle = ''
        part = ''
        for i in range(9, - 1, - 1):
            if dp[i] % 2 == 1 and len(middle) == 0:
                middle = str(i)
            part += str(i) * (dp[i] // 2)
        if len(part) == 0 and len(middle) == 1:
            return middle
        if len(part) > 0 and int(part) == 0 and len(middle) == 1:
            return middle
        if len(part) > 0 and int(part) == 0 and len(middle) == 0:
            return "0"
        return part + middle + "".join(reversed(list(part)))

    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:

        graph = defaultdict(set)

        def dfs(root):
            if root == None:
                return
            if root.left:
                graph[root.val].add(root.left.val)
                graph[root.left.val].add(root.val)
                dfs(root.left)
            if root.right:
                graph[root.val].add(root.right.val)
                graph[root.right.val].add(root.val)
                dfs(root.right)

        dfs(root)
        visited = set()
        count = -1
        visited.add(start)
        q = deque([start])
        while q:
            n = len(q)
            for i in range(n):
                temp = q.popleft()
                for node in graph[temp]:
                    if node not in visited:
                        visited.add(node)
                        q.append(node)
            count += 1
        return count

    # 稍后再看
    def kSum(self, nums: List[int], k: int) -> int:
        tmp = sum(x for x in nums if x < 0)
        nums = [abs(x) for x in nums]
        nums.sort()
        hpq = [(0, -1)]  # 初始情况空列表的小细节
        for _ in range(k - 1):
            tot, idx = heapq.heappop(hpq)
            if 0 <= idx < len(nums) - 1:
                heapq.heappush(hpq, (tot + nums[idx + 1], idx + 1))
                heapq.heappush(hpq, (tot + nums[idx + 1] - nums[idx], idx + 1))
            if idx == -1:
                heapq.heappush(hpq, (nums[0], 0))
        return sum(nums) - hpq[0][0] + tmp


if __name__ == '__main__':
    s = Solution()
    # print(s.minNumberOfHours(initialEnergy=5, initialExperience=3, energy=[1, 4, 3, 2], experience=[2, 6, 3, 1]))
    # print(s.minNumberOfHours(1
    #                          , 1
    #                          , [1, 1, 1, 1]
    #                          , [1, 1, 1, 50]))
    print(s.kSum(nums=[2, 4, -2], k=5))
    print(s.kSum(nums=[1, -2, 3, 4, -10, 12], k=16))
