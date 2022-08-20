import itertools
import re


class Solution:
    def reformat2(self, s: str) -> str:
        digitals = []
        alphas = []
        for c in s:
            if c.isdigit():
                digitals.append(c)
            else:
                alphas.append(c)
        if abs(len(digitals) - len(alphas)) > 1:
            return ""
        res = [""] * (len(digitals) + len(alphas))
        n = min(len(digitals), len(alphas))
        if len(digitals) < len(alphas):
            digitals, alphas = alphas, digitals
        for i in range(n):
            res[2 * i] = digitals[i]
            res[2 * i + 1] = alphas[i]
        if len(digitals) > n:
            res[-1] = digitals[-1]
        return "".join(res)

    def reformat(self, s: str) -> str:
        a = re.findall(r'\d', s)
        b = re.findall(r'[a-z]', s)
        if abs(len(a) - len(b)) > 1:
            return ''
        a, b = sorted([a, b], key=len)
        return ''.join(map(''.join, itertools.zip_longest(b, a, fillvalue='')))


if __name__ == '__main__':
    s = Solution()
    # print(s.reformat("a0b1c2"))
    # print(s.reformat("covid2019"))
    a = ['1', '2', '3']
    b = ['a', 'b', 'c', 'd']
    for item in itertools.zip_longest(b, a, fillvalue='1'):
        print(item)
