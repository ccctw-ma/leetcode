import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def countPalindromicSubsequences(self, s: str) -> int:
        MOD = int(1e9 + 7)
        n = len(s)

        @cache
        def dp(c, i, j):
            if i == j: return s[i] == c
            if i > j: return 0
            if s[i] == s[j] == c: return (2 + sum(dp(x, i + 1, j - 1) for x in 'abcd')) % MOD
            if s[i] == c: return dp(c, i, j - 1)
            if s[j] == c: return dp(c, i + 1, j)
            return dp(c, i + 1, j - 1)

        return sum(dp(x, 0, n - 1) for x in 'abcd') % MOD

    def countPalindromicSubsequences2(self, S: str) -> int:
        N = len(S)
        MOD = 1000000007

        # nxt记录在当前字符之后，截止当前的j之前，一共有多少个回文串；例如：bccb中j=2时，当前nxt[0]=2，因为在第1个b之后有2个回文串c和cc
        nxt = [0] * N
        # use用于去重，记录当前字符之后的回文串中，已被计入到ans中的回文串有多少个；例如：bccb中j=3时，use[0]=3，第1个b之后有3个回文串c、cc和b，但都已经被最后一个b收获掉了
        use = [0] * N
        # nxt[i]-use[i] : 当前可以收获的数量
        # “收获”可理解为：从i+1开始，还有多少个回文串没有被前后两个S[i]嵌套；也就是当一个新的S[j]=S[i]后，可以通过前后嵌套的S[i]和S[j]来新增的回文串数量

        ans = 0
        for j in range(N):
            # 新字符直接带来的仅包含当前字符的回文串，例如b->b；bc->c；bcc->cc；bccb->bb
            x = 1

            for i in range(j - 1, -1, -1):
                # 字符S[i]等于新增字符S[j]，则收获S[i]字符之后未收获的回文串
                if S[i] == S[j]:
                    # x : 此时的x是当前字符S[i]之后因为当前新增字符S[j]而新增的回文串数量

                    # now_nxt : 当前字符S[i]后可收获的回文串数量
                    now_nxt = nxt[i]

                    # now_use : 当前字符S[i]后已收获的回文串数量
                    now_use = use[i]

                    # 将新增的回文串数量添加到S[i]中，这些回文串是因为新增字符才新增的，所以并不能在这次被直接收获，需要等待下一个相同的字符
                    nxt[i] += x

                    # 计算当前可以收获的新增回文串数量：可收获的数量-已收获的数量
                    # 对于相同的字符来说，靠前的字符中记录的回文串数量，一定已经包含靠后的字符中记录的回文串数量（不使用+=的原因）
                    x = now_nxt - now_use + 1

                    # 将这一次收获的回文串回文串数量累计到已收获的回文串数量之中
                    use[i] = now_nxt + 1

                # 字符S[i]不等于新增字符S[j]，新增字符不能收获新的回文串，但是对于i来说，它后面又新增了x个回文串，以备之后收获
                else:
                    nxt[i] += x

            # 记录当前字符新增的回文串数
            ans += x
        return ans % MOD


if __name__ == '__main__':
    s = Solution()
