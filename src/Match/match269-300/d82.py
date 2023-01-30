# Definition for a binary tree node.
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        if root.left is None and root.right is None:
            return bool(root.val)
        v = root.val
        a = self.evaluateTree(root.left)
        b = self.evaluateTree(root.right)
        if v == 2:
            return a or b
        else:
            return a and b

    def latestTimeCatchTheBus(self, buses: List[int], passengers: List[int], capacity: int) -> int:
        buses.sort()
        passengers.sort(reverse=True)
        ps = set(passengers)
        a = []
        for bus in buses:
            a = []
            while len(a) < capacity and passengers and passengers[-1] <= bus:
                a.append(passengers.pop())
        if len(a) == capacity:
            ans = a[-1] - 1
        else:
            ans = buses[-1]
        while ans in ps:
            ans -= 1
        return ans

    def minSumSquareDiff(self, nums1: List[int], nums2: List[int], k1: int, k2: int) -> int:
        ans, k = 0, k1 + k2
        for i in range(len(nums1)):
            nums1[i] = abs(nums1[i] - nums2[i])
            ans += nums1[i] * nums1[i]
        if sum(nums1) <= k:
            return 0  # 所有 a[i] 均可为 0
        nums1.sort(reverse=True)
        nums1.append(0)  # 哨兵
        for i, v in enumerate(nums1):
            ans -= v * v
            j = i + 1
            c = j * (v - nums1[j])
            if c < k:
                k -= c
                continue
            v -= k // j
            return ans + k % j * (v - 1) * (v - 1) + (j - k % j) * v * v




if __name__ == '__main__':
    s = Solution()
    # print(s.latestTimeCatchTheBus(buses=[20, 30, 10], passengers=[19, 13, 26, 4, 25, 11, 21], capacity=2))

    # print(s.latestTimeCatchTheBus(buses=[10, 20], passengers=[2, 17, 18, 19], capacity=2))

    # print(s.latestTimeCatchTheBus([2], [2], 2))
    # print(s.latestTimeCatchTheBus([18, 8, 3, 12, 9, 2, 7, 13, 20, 5]
    #                               , [13, 10, 8, 4, 12, 14, 18, 19, 5, 2, 30, 34]
    #                               , 1))
    print(divmod(11, 2))
