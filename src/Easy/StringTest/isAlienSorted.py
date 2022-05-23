from typing import List


class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        buc = dict((c, i) for i, c in enumerate(order))
        for i in range(1, len(words)):
            a, b = words[i - 1], words[i]
            l, r = 0, min(len(a), len(b))
            while l < r:
                if buc[a[l]] < buc[b[l]]:
                    break
                elif buc[a[l]] == buc[b[l]]:
                    l += 1
                else:
                    return False
            if l == r and len(a) > len(b):
                return False
        return True


if __name__ == '__main__':
    print(Solution().isAlienSorted(words=["app", "app"], order="hlabcdefgijkmnopqrstuvwxyz"))
