T = int(input())

maxn = 20000
maxv = 100000


# 树状数组
def query(buc, t):
    res = 0
    while t > 0:
        res += buc[t]
        t -= t & (-t)
    return res


def update(buc, t):
    while t <= maxv:
        buc[t] += 1
        t += t & (-t)


for _ in range(T):
    row = list(map(int, input().split(" ")))

    n = row[0]
    arr = row[1:]
    xx = [0] * maxn
    yy = [0] * maxn
    buc = [0] * (maxv + 5)
    # print(row, xx, yy, buc)
    for i in range(n):
        xx[i] = query(buc, arr[i] - 1)
        update(buc, arr[i])
    buc = [0] * (maxv + 5)
    for i in range(n - 1, -1, -1):
        yy[i] = query(buc, arr[i] - 1)
        update(buc, arr[i])
    res = 0
    for i in range(n):
        res += (i - xx[i]) * yy[i] + xx[i] * (n - i - 1 - yy[i])
    print(res)
