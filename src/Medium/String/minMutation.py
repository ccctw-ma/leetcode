import collections
from typing import List


class Solution:

    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        if start == end:
            return 0

        def diffOne(s: str, t: str) -> bool:
            return sum(x != y for x, y in zip(s, t)) == 1

        m = len(bank)
        adj = [[] for _ in range(m)]
        endIndex = -1
        for i, s in enumerate(bank):
            if s == end:
                endIndex = i
            for j in range(i + 1, m):
                if diffOne(s, bank[j]):
                    adj[i].append(j)
                    adj[j].append(i)
        if endIndex == -1:
            return -1

        q = [i for i, s in enumerate(bank) if diffOne(start, s)]
        vis = set(q)
        step = 1
        while q:
            tmp = q
            q = []
            for cur in tmp:
                if cur == endIndex:
                    return step
                for nxt in adj[cur]:
                    if nxt not in vis:
                        vis.add(nxt)
                        q.append(nxt)
            step += 1
        return -1


if __name__ == '__main__':
    print(Solution().minMutation("AACCGGTT",
                                 "AAACGGTA",
                                 ["AACCGGTA", "AACCGCTA", "AAACGGTA"]))
