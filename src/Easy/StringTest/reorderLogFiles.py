from typing import List


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        def compare(log: str) -> tuple:
            a, b = log.split(' ', 1)
            return (0, b, a) if b[0].isalpha() else (1,)

        return sorted(logs, key=compare)


if __name__ == '__main__':
    print(Solution().reorderLogFiles(
        logs=["dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]))

    print("dig1 8 1 5 1".split(" ", 1))
