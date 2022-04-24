class Solution:
    def strongPasswordChecker(self, password: str) -> int:
        length = len(password)
        if length < 6:
            return 6 - length
        return 0


if __name__ == '__main__':
    solution = Solution()
    print(solution.strongPasswordChecker("aA1"))
