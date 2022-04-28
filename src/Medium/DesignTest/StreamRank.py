import sortedcontainers


class StreamRank:

    def __init__(self):
        self.buc = [0] * 50005
        self.maxN = 50005

    def track(self, x: int) -> None:
        x += 1
        while x < self.maxN:
            self.buc[x] += 1
            x += x & (-x)

    def getRankOfNumber(self, x: int) -> int:
        x += 1
        res = 0
        while x > 0:
            res += self.buc[x]
            x -= x & (-x)
        return res


if __name__ == '__main__':
    obj = StreamRank()
    print(obj.getRankOfNumber(1))
    obj.track(0)
    print(obj.getRankOfNumber(0))
    obj.track(1)
    obj.track(2)
    obj.track(3)
    obj.track(4)
    obj.track(5)
    obj.track(6)
    obj.track(7)
    par2 = obj.getRankOfNumber(10000)
    print(par2)


    sortList = sortedcontainers.SortedList()
    sortList.add()
# Your StreamRank object will be instantiated and called as such:
# obj = StreamRank()
# obj.track(x)
# param_2 = obj.getRankOfNumber(x)
