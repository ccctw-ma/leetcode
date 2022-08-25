import re
from math import gcd

from fontTools.misc.arrayTools import pairwise


class Solution:
    def fractionAddition(self, expression: str) -> str:
        arr1, arr2 = [], []
        n = len(expression)
        l = 0
        while l < n:
            r = l + 1
            while r < n and expression[r] != '+' and expression[r] != '-':
                r += 1
            s = expression[l: r]
            if len(s) != 0:
                a, b = s.split('/')
                arr1.append(int(a))
                arr2.append(int(b))
            l = r

        m = arr2[0]
        for i in range(1, len(arr2)):
            m = m * arr2[i] // gcd(m, arr2[i])
        for i in range(len(arr1)):
            arr1[i] = arr1[i] * (m // arr2[i])
        a = sum(arr1)
        if a == 0:
            return '0/1'
        c = gcd(a, m)
        a //= c
        m //= c
        return str(a) + '/' + str(m)


if __name__ == '__main__':
    S = Solution()
    # print(S.fractionAddition(expression="-1/2+1/2"))
    # print(S.fractionAddition("-1/2+1/2+1/3"))
    print(S.fractionAddition("1/3-1/2"))
    print(S.fractionAddition("-5/2+10/3+7/9"))
    # print(gcd(100, 50))
    expression = "-5/2+10/3+7/9";
    print(re.findall('([-\+]*[0-9]+)/([0-9]+)', expression))
