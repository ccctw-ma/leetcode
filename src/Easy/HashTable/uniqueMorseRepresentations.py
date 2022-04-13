import collections
from typing import List


class Solution:
    def uniqueMorseRepresentations(self, words: List[str]) -> int:
        passwords = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
                     "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."]
        return len(set(["".join(map(lambda x: passwords[ord(x) - 97], word)) for word in words]))
    # cnt = dict([(chr(i + 97), p) for i, p in enumerate(passwords)])


if __name__ == '__main__':
    print(Solution().uniqueMorseRepresentations(words=["gin", "zen", "gig", "msg"]))
    # print(ord('a'), chr(98))
