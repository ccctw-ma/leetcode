import collections


class Node:
    def __init__(self, key="", count=0):
        self.pre = None
        self.next = None
        self.keys = {key}
        self.count = count

    def insert(self, node: 'Node'):
        node.pre = self
        node.next = self.next
        node.pre.next = node
        node.next.pre = node
        return node

    def remove(self):
        self.pre = self.next
        self.next = self.pre


class AllOne:

    def __init__(self):
        self.root = Node()
        self.root.pre = self.root
        self.root.next = self.root
        self.nodes = {}

    def inc(self, key: str) -> None:
        if key not in self.nodes:
            # 暂无节点 或者 第一个节点的数据大于 1
            if self.root.next is self.root or self.root.next.count > 1:
                self.nodes[key] = self.root.insert(Node(key, 1))
            else:
                self.root.next.keys.add(key)
                self.nodes[key] = self.root.next
        else:
            cur = self.nodes[key]
            nxt = cur.next
            if nxt is self.root or nxt.count > cur.count + 1:
                self.nodes[key] = cur.insert(Node(key, cur.count + 1))
            else:
                nxt.keys.add(key)
                self.nodes[key] = nxt
            cur.keys.remove(key)
            if len(cur.keys) == 0:
                cur.remove()

    def dec(self, key: str) -> None:
        cur = self.nodes[key]
        if cur.count == 1:
            del self.nodes[key]
        else:
            pre = cur.pre
            if pre is self.root or pre.count < cur.count - 1:
                self.nodes[key] = cur.pre.insert(Node(key, cur.count - 1))
            else:
                pre.keys.add(key)
                self.nodes[key] = pre
        cur.keys.remove(key)
        if len(cur.keys) == 0:
            cur.remove()

    def getMaxKey(self) -> str:
        return next(iter(self.root.pre.keys)) if self.root.pre is not self.root else ""

    def getMinKey(self) -> str:
        return next(iter(self.root.next.keys)) if self.root.next is not self.root else ""


# Your AllOne object will be instantiated and called as such:


if __name__ == '__main__':
    obj = AllOne()
    key = "Hello world"
    obj.inc(key)
    obj.dec(key)
    print(obj.getMaxKey())
    print(obj.getMinKey())
    keys = {key, 123}
    print(keys)
