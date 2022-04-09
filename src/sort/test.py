import collections

if __name__ == '__main__':
    n = 10
    arr = [1, 2, 2, 3, 3, 4, 4, 5, 5, 9]
    cnt = collections.Counter(arr)
    cnt[2] += 1
    cnt[9] += 3
