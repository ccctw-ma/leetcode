import re
from math import ceil


class Solution:
    def solveEquation(self, equation: str) -> str:
        leftStr, rightStr = equation.split("=")
        leftArr = list(filter(lambda x: len(x), re.split(r'([+\-])', leftStr)))
        rightArr = list(filter(lambda x: len(x), re.split(r'([+\-])', rightStr)))

        def parseStr(arr):
            if arr[0] != '-':
                arr = ['+'] + arr
            lx, lv = 0, 0
            for i in range(0, len(arr), 2):
                o = arr[i]
                v = arr[i + 1]
                if v.count('x') == 1:
                    if len(v) == 1:
                        lx += int(o + '1')
                    else:
                        lx += int(o + v[0: -1])
                else:
                    lv += int(o + v)
            return lx, lv

        lx, lv = parseStr(leftArr)
        rx, rv = parseStr(rightArr)
        if lx == rx and lv != rv:
            return "No solution"
        if lx == rx and lv == rv:
            return "Infinite solutions"
        x, v = lx - rx, rv - lv
        x = ceil(v / x)
        return f'x={str(x)}'


if __name__ == '__main__':
    s = Solution()
    # print(s.solveEquation(equation="x+55-3+x=6+x-2"))
    print(s.solveEquation("-x=-1"))
    # arr = ['', '+', '10']
    # print(list(filter(lambda x: len(x), arr)))
