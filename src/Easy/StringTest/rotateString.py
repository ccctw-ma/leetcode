class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        if len(s) != len(goal):
            return False
        for i in range(1, len(s)):
            temp = s[i:] + s[0: i]
            if temp == goal:
                return True
        return False


if __name__ == '__main__':
    print(Solution().rotateString("gcmbf", "fgcmb"))
    print("a" in "abc")
