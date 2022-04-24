from typing import List


class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        # if n < 10:
        #     return [i for i in range(1, n + 1)]
        #
        # def dfs(pre: int):
        #     nonlocal res
        #     res.append(pre)
        #     for i in range(10):
        #         num = pre * 10 + i
        #         if num <= n:
        #             dfs(num)
        #         else:
        #             break
        #
        # res = []
        # for i in range(1, 10):
        #     dfs(i)
        # return res
        res, num = [], 1
        for i in range(n):
            res.append(i)
            if num * 10 <= n:
                num *= 10
            else:
                while num % 10 == 9 or num + 1 > n:
                    num //= 10
                num += 1
        return res


if __name__ == '__main__':
    print(Solution().lexicalOrder(1000))
