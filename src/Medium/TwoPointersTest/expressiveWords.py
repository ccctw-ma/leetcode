from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def expressiveWords(self, s: str, words: List[str]) -> int:
        res = 0
        n = len(s)
        for word in words:
            i = 0
            j = 0
            nn = len(word)
            while i < n and j < nn:
                c = s[i]
                ii = i + 1
                count = 1
                while ii < n and s[ii] == c:
                    ii += 1
                    count += 1

                d = word[j]
                jj = j + 1
                jc = 1
                while jj < nn and word[jj] == d:
                    jj += 1
                    jc += 1
                if c != d or jc > count or jc < count == 2:
                    break
                i = ii
                j = jj
            res += int(i == n and j == nn)
        return res

    def expressiveWords2(self, S: str, words) -> int:
        pattern = '^'  # '^' match the start of target word
        a = [i for i in range(len(S)) if i == 0 or S[i] != S[i - 1]] + [
            len(S)]  # record the start indexes of continuous same letter; e.g.'aaabbcc': [0, 3, 5]
        # print(a)
        for i in range(1, len(a)):
            cha, num = S[a[i] - 1], a[i] - a[i - 1]  # cha is the continuous same letters, num is length
            # print(cha, num)
            if num < 3:
                pattern += cha * num  # if num < 3, it means not stretchy, so the regular expression should be this 'cha' itself
            if num >= 3:
                pattern += '%s{1,%d}' % (cha,
                                         num)  # if num > 3, it means any length that not longer than num (but need to exist) could be OK, so the regular expression is '%s{1,%d}'
        pattern += '$'  # '$' match the end of target word
        # print(pattern)
        res = 0  # use res as a count
        for word in words:
            if re.match(pattern, word):
                res += 1  # use regular expression to match word in words one by one
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.expressiveWords2("heeellooo",
                             words=["hello", "hi", "helo"]))

