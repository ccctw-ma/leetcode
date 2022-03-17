class Solution:
    def convertToBase7(self, num: int) -> str:
        res = []
        positive = True if num >= 0 else False
        num = abs(num)
        while num >= 7:
            res.append(num % 7)
            num = num // 7
        res.append(num)
        if not positive:
            res.append("-")
        return "".join(map(str, res[::-1]))


if __name__ == '__main__':
    solution = Solution()
    print(solution.convertToBase7(100))
