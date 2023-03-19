def count_substrings(s):
    n = len(s)
    count = [0] * 8
    res = 0
    for i in range(n):
        for j in range(i + 1, n):
            if s[i] != s[j]:
                mask = (1 << (ord(s[i]) - ord('h'))) | (1 << (ord(s[j]) - ord('h')))
                count[mask] += 1
                if count[1] > 0 and count[2] > 0 and count[4] > 0:
                    x = min(count[1], count[2], count[4])
                    y = min(count[2], count[4], count[5])
                    z = min(count[4], count[5], count[3])
                    res += min(x + y + z, x + y + y - x, x + z + z - x, y + z + z - x - y)
            elif s[i] == s[j] and s[i] != 'y':
                count[1 << (ord(s[i]) - ord('h'))] = 0
    return res if res > 0 else -1

