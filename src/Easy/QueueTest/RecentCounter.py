import collections


class RecentCounter:

    def __init__(self):
        self.queue = collections.deque()

    def ping(self, t: int) -> int:
        self.queue.append(t)
        while t - self.queue[0] > 3000:
            self.queue.popleft()
        return len(self.queue)



if __name__ == '__main__':
    r = RecentCounter()
    print(r.ping(1))
