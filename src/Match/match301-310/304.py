from collections import defaultdict
from typing import List


class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        nums.sort()
        count = 0
        while sum(nums) != 0:
            count += 1
            i = 0
            while i < len(nums) and nums[i] == 0:
                i += 1
            cur = nums[i]
            for i in range(len(nums)):
                if nums[i] != 0:
                    nums[i] -= cur
        return count

    def maximumGroups(self, grades: List[int]) -> int:
        n = len(grades)
        res = 1
        count = 1
        while True:
            n -= count
            count += 1
            if n >= count:
                res += 1
            else:
                break
        return res

    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        s1 = set()
        s2 = set()
        buc1 = defaultdict(int)
        buc2 = defaultdict(int)

        step = 0
        while True:
            s1.add(node1)
            buc1[node1] = step
            step += 1
            if edges[node1] == -1:
                break
            node1 = edges[node1]
            if node1 in s1:
                break

        step = 0
        while True:
            s2.add(node2)
            buc2[node2] = step
            step += 1
            if edges[node2] == -1:
                break
            node2 = edges[node2]
            if node2 in s2:
                break

        s = s1 & s2
        if len(s) == 0:
            return -1
        max_length = 10 ** 9
        res = []
        for node in s:
            l = max(buc1[node], buc2[node])
            if l < max_length:
                res = [node]
                max_length = l
            elif l == max_length:
                res.append(node)
        res.sort()
        return res[0]

    def longestCycle(self, edges: List[int]) -> int:
        n = len(edges)
        dp = [0] * n
        loop = []
        for i in range(n):
            node = i
            if dp[node] == 1:
                continue
            dp[node] = 1
            ss = set()
            ss.add(node)
            while True:
                if edges[node] == -1:
                    break
                node = edges[node]
                if dp[node] == 1:
                    if node in ss:
                        loop.append(node)
                    break
                dp[node] = 1
                ss.add(node)

        if len(loop) == 0:
            return -1
        max_res = 1
        for node in loop:

            ss = set()
            while node not in ss:
                ss.add(node)
                node = edges[node]
            max_res = max(max_res, len(ss))
        return max_res


if __name__ == '__main__':
    s = Solution()
    # print(s.maximumGroups(grades=[10, 6, 12, 7, 3, 5]))

    # print(s.closestMeetingNode(edges=[2, 2, 3, -1], node1=0, node2=1))
    # print(s.closestMeetingNode(edges=[1, 2, -1], node1=0, node2=2))

    # print(s.closestMeetingNode([4, 3, 0, 5, 3, -1]
    #                            , 4,
    #                            0))
    print(s.longestCycle(edges=[3, 3, 4, 2, 3]))
