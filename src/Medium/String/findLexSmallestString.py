import math
from collections import defaultdict, deque

import sortedcontainers


class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        buc = sortedcontainers.SortedSet()
        buc.add(s)
        q = deque([s])
        while q:
            n = len(q)
            for i in range(n):
                ts = q.popleft()
                temp = [''] * len(ts)
                for j, c in enumerate(ts):
                    temp[j] = str((int(c) + a) % 10) if j & 1 else c
                ss = ''.join(temp)
                if ss not in buc:
                    buc.add(ss)
                    q.append(ss)
                ss = ''.join(temp[-b:] + temp[:-b])
                if ss not in buc:
                    buc.add(ss)
                    q.append(ss)
        return buc[0]

    def findLexSmallestString2(self, s: str, a: int, b: int) -> str:
        def f(num, a):
            s = set()
            while num not in s:
                s.add(num)
                num += a
                num %= 10
            return min(s)

        n = len(s)
        lst = [ord(c) - ord('0') for c in s]
        lst += lst[:-1]
        # 轮转lst，穷举每一种情况。
        # 在一种情况下，求得lst0最佳变换方案(快，且有可能变不了)，lst1最佳变换方案(快)。
        results = set()
        idx = 0
        while True:
            addition = [0, 0]
            if b % 2 == 1:
                target = f(lst[idx], a)
                addition[0] = (target + 10 - lst[idx]) % 10
            target = f(lst[idx + 1], a)
            addition[1] = (target + 10 - lst[idx + 1]) % 10
            ret = [(lst[i] + addition[(i - idx) % 2]) % 10 for i in range(idx, idx + n)]
            results.add(tuple(ret))
            idx += b
            idx %= n
            if idx == 0:
                break
        min_s = min(results)
        return "".join([str(c) for c in min_s])


if __name__ == '__main__':
    s = Solution()
    print(s.findLexSmallestString(s="5525", a=9, b=2))
    print(s.findLexSmallestString2(s="43987654", a=7, b=3))
