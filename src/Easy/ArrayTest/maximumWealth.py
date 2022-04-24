from typing import List


class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        return max([sum(arr) for arr in accounts])


if __name__ == '__main__':
    print(Solution().maximumWealth(accounts=[[1, 5], [7, 3], [3, 5]]))
