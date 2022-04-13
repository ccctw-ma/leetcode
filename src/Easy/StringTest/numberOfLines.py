from typing import List


class Solution:
    def numberOfLines(self, widths: List[int], s: str) -> List[int]:
        line, count = 1, 0
        for c in s:
            if count + widths[ord(c) - 97] > 100:
                count = widths[ord(c) - 97]
                line += 1
            else:
                count += widths[ord(c) - 97]
        return [line, count]


if __name__ == '__main__':
    print(Solution().numberOfLines(
        widths=[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]
        , s="abcdefghijklmnopqrstuvwxyz"))
