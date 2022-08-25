from typing import List


class OrderedStream:

    def __init__(self, n: int):
        self.arr = [""] * (n + 1)
        self.index = 1

    def insert(self, idKey: int, value: str) -> List[str]:
        self.arr[idKey] = value
        if len(self.arr[self.index]) == 0:
            return []
        else:
            res = []
            while self.index < len(self.arr) and len(self.arr[self.index]) != 0:
                res.append(self.arr[self.index])
                self.index += 1
            return res


if __name__ == '__main__':
    pass
