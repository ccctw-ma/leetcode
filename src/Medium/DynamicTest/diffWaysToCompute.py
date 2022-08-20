from functools import lru_cache
from typing import List


class Solution:

    @lru_cache(None)
    def diffWaysToCompute(self, expression: str) -> List[int]:
        res = []
        for i in range(len(expression)):
            c = expression[i]
            if c == '+' or c == '-' or c == '*':
                res1 = self.diffWaysToCompute(expression[:i])
                res2 = self.diffWaysToCompute(expression[i + 1:])
                for a in res1:
                    for b in res2:
                        if c == '+':
                            res.append(a + b)
                        elif c == '-':
                            res.append(a - b)
                        else:
                            res.append(a * b)
        if len(res) == 0:
            return [int(expression)]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.diffWaysToCompute(expression="2*3-4*5*1-2+3"))
