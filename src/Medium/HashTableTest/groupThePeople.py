from collections import defaultdict
from typing import List


class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        buc = defaultdict(list)
        for i, c in enumerate(groupSizes):
            buc[c].append(i)
        res = []
        for k, v in buc.items():
            for i in range(0, len(v), k):
                res.append(v[i: i + k])
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.groupThePeople(groupSizes=[3, 3, 3, 3, 3, 1, 3]))
    print(s.groupThePeople(groupSizes=[2, 1, 3, 3, 3, 2]))
