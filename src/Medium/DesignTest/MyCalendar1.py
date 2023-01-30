import sortedcontainers


class MyCalendar:

    def __init__(self):
        self.arr = []
        self.booked = sortedcontainers.SortedDict()

    def book(self, start: int, end: int) -> bool:
        # if len(self.arr) == 0:
        #     self.arr.append([start, 1])
        #     self.arr.append([end, -1])
        #     return True
        # else:
        #     temp = self.arr.copy()
        #     temp.append([start, 1])
        #     temp.append([end, -1])
        #     temp.Template()
        #     count = 0
        #     for _, b in temp:
        #         count += b
        #         if count == 2:
        #             return False
        #     self.arr = temp
        #     return True
        # if any(l < end and start < r for l, r in self.arr):
        #     return False
        # self.arr.append((start, end))
        # return True
        i = self.booked.bisect_left(end)
        if i == 0 or self.booked.items()[i - 1][1] <= start:
            self.booked[start] = end
            return True
        return False


class MyCalendar2:
    def __init__(self):
        self.tree = set()
        self.lazy = set()

    def query(self, start: int, end: int, l: int, r: int, idx: int) -> bool:
        if r < start or end < l:
            return False
        if idx in self.lazy:  # 如果该区间已被预订，则直接返回
            return True
        if start <= l and r <= end:
            return idx in self.tree
        mid = (l + r) // 2
        return self.query(start, end, l, mid, 2 * idx) or \
               self.query(start, end, mid + 1, r, 2 * idx + 1)

    def update(self, start: int, end: int, l: int, r: int, idx: int) -> None:
        if r < start or end < l:
            return
        if start <= l and r <= end:
            self.tree.add(idx)
            self.lazy.add(idx)
        else:
            mid = (l + r) // 2
            self.update(start, end, l, mid, 2 * idx)
            self.update(start, end, mid + 1, r, 2 * idx + 1)
            self.tree.add(idx)
            if 2 * idx in self.lazy and 2 * idx + 1 in self.lazy:
                self.lazy.add(idx)

    def book(self, start: int, end: int) -> bool:
        if self.query(start, end - 1, 0, 10 ** 9, 1):
            return False
        self.update(start, end - 1, 0, 10 ** 9, 1)
        return True


if __name__ == '__main__':
    obj = MyCalendar()
    l = sortedcontainers.SortedList()
