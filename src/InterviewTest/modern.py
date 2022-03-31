def test01(d, n, k, s, e, a):
    arr = [[] for _ in range(d + 1)]
    for i in range(n):
        start = s[i]
        end = e[i]
        for j in range(start, end + 1):
            arr[j].append(a[i])
    res = 0
    for temp in arr:
        if len(temp) != 0:
            count = 0
            temp.sort(reverse=True)
            for i in range(min(len(temp), k)):
                count += temp[i]
            res = max(res, count)
    return res


if __name__ == '__main__':
    print(test01(10, 4, 3, [2, 6, 4, 3], [8, 9, 7, 5], [900, 1600, 2000, 400]))
