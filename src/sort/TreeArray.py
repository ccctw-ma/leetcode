arr = [0] * 10000


def calc(i):
    res = 0
    while i > 0:
        res += arr[i]
        i -= i & (-i)
    return res


def update(i, k):
    while i <= 10000:
        arr[i] += k
        i += i & (-i)


i = 1000
while i > 0:
    print(i)
    i -= i & (-i)


