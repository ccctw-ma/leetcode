from typing import List


class TreeNode:

    def __init__(self, val="", depth=0):
        self.val = val
        self.children = {}
        self.depth = 0


def check(a, b) -> bool:
    for i in range(len(a)):
        if ord(a[i]) - ord(b[i]) > 0:
            return False
    return True


def longestWord(words: List[str]) -> str:
    max_length = 0
    res = ""
    words.sort(key=lambda c: len(c))
    word_set = set()
    for word in words:
        if len(word) == 1 or word[:-1] in word_set:
            word_set.add(word)
            if len(word) > max_length:
                max_length = len(word)
                res = word
            elif len(word) == max_length and word < res:
                res = word
    return res


if __name__ == '__main__':
    print("apple" < "apply")
    s = set()
    s.add('a')
    s.add("")
    ss = "hello kitty"
    print(ss[:-1])
    print(longestWord(["a", "banana", "app", "appl", "ap", "apply", "apple"]))
