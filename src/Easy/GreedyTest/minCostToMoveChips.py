import collections
from collections import Counter, defaultdict
from typing import List


class Solution:

    def minCostToMoveChips(self, position: List[int]) -> int:

        collections.defaultdict
        odd, even = 0, 0
        for p in position:
            odd += p % 2
            even += (p + 1) % 2
        return min(odd, even)


if __name__ == '__main__':
    s = Solution()
    print(s.minCostToMoveChips(position=[6, 4, 7, 8, 2, 10, 2, 7, 9, 7]))
