from typing import Optional


class Trie:

    def __init__(self):
        self.children = [None] * 26
        self.isEnd = False

    def insert(self, s: str) -> None:
        node = self
        for ch in s:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                node.children[index] = Trie()

            node = node.children[index]
        node.isEnd = True

    def searchPrefix(self, prefix: str) -> Optional["Trie"]:
        node = self
        for ch in prefix:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                return None
            node = node.children[index]
        return node
