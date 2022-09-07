# class Solution:
#     def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
import collections
from functools import cache
from typing import List


class Solution:

    def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
        n = len(bottom)
        dp = [[0] * n for _ in range(n)]
        dp[0] = list(map(lambda x: ord(x) - ord('A'), bottom))
        buc = collections.defaultdict(list)
        for allow in allowed:
            a, b, c = ord(allow[0]) - ord('A'), ord(allow[1]) - ord('A'), ord(allow[2]) - ord('A')
            buc[a * 10 + b].append(c)

        def tb(level, index):
            if level == n - 1:
                return True
            width = n - level
            a, b = dp[level][index], dp[level][index + 1]
            tar = a * 10 + b
            if tar in buc:
                cs = buc[tar]
                for c in cs:
                    dp[level + 1][index] = c
                    if index == width - 2:
                        flag = tb(level + 1, 0)
                    else:
                        flag = tb(level, index + 1)
                    if flag:
                        return True
            return False

        return tb(0, 0)

    def pyramidTransition2(self, bottom: str, allowed: List[str]) -> bool:
        matched = collections.defaultdict(list)
        for allow in allowed:
            matched[allow[:2]].append(allow[2])

        for i in range(len(bottom) - 1):
            if not matched[bottom[i:i + 2]]:
                return False

        @cache
        def trace_back(old, new):
            if len(new) > 1 and not trace_back(new, ""):
                return False
            on, nn = len(old), len(new)
            if on == 2:
                if nn == 0:
                    return len(matched[old]) > 0
                for c in matched[old]:
                    if not matched[new[-1] + c]:
                        continue
                    if trace_back(new + c, ""):
                        return True
                return False
            else:
                for c in matched[old[:2]]:
                    if new and not matched[new[-1] + c]:
                        continue
                    if trace_back(old[1:], new + c):
                        return True
                return False

        return trace_back(bottom, "")

    def pyramidTransition3(self, bottom: str, allowed: List[str]) -> bool:
        trans = collections.defaultdict(list)
        for p in allowed:
            trans[p[:2]].append(p[2])

        @cache
        def search(a, b):
            if len(b) >= 2:
                if not search(b, ''):
                    return False
            if len(a) == 2:
                if not b:
                    return a in trans
                else:
                    return any(search(b + t, '') for t in trans.get(a, []))
            else:
                return any(search(a[1:], b + t) for t in trans.get(a[:2], []))

        return search(bottom, '')


if __name__ == '__main__':
    s = Solution()
    print(s.pyramidTransition(bottom="AABA", allowed=["AAA", "AAB", "ABA", "ABB", "BAC"]))
    print(any([]))