from typing import List


class MagicDictionary:

    def __init__(self):
        self.buc = {}

    def buildDict(self, dictionary: List[str]) -> None:
        for s in dictionary:
            temp = self.buc
            for i, c in enumerate(s):
                if c not in temp:
                    temp[c] = {}

                temp = temp[c]
            temp['#'] = True

    def find(self, s: str) -> bool:
        temp = self.buc
        for c in s:
            if c in temp:
                temp = temp[c]
            else:
                return False
        return '#' in temp

    def search(self, searchWord: str) -> bool:
        temp = self.buc
        for i, c in enumerate(searchWord):
            for k in temp.keys():
                if k != c and k != '#':
                    t = searchWord[:i] + k + searchWord[i + 1:]
                    if self.find(t):
                        return True
            if c in temp:
                temp = temp[c]
            else:
                return False
        return False


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)


if __name__ == '__main__':
    m = MagicDictionary()
    m.buildDict(["hello", "leetcode"])
    # print(m.find("helloo"))
    # print(m.search("hhllo"))
    print(m.search("hello"))
    print(m.search("hhllo"))
    print(m.search("hell"))
    print(m.search("leetcoded"))