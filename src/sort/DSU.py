class DSU:
    def __init__(self, dataList):
        self.parent = {}
        self.size = {}
        self.setsCount = len(dataList)

        for x in dataList:
            self.parent[x] = x
            self.size[x] = 1

    def isExit(self, x):
        return x in self.parent

    def add(self, x):
        self.parent[x] = x
        self.size[x] = 1
        self.setsCount += 1

    def find(self, x):
        while x != self.parent[x]:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        return x

    def union(self, x, y):
        px = self.find(x)
        py = self.find(y)
        if px != py:
            sx = self.size[px]
            sy = self.size[py]
            if sx < sy:
                px, py = py, px
            self.parent[py] = px
            self.size[px] += self.size[py]
            self.setsCount -= 1

    def isConnected(self, x, y):
        return self.find(x) == self.find(y)
