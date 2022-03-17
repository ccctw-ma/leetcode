from typing import List


def platesBetweenCandles(s: str, queries: List[List[int]]) -> List[int]:
    candles, plates = [0] * (len(s) + 1), [0] * (len(s) + 1)
    for i in range(len(s)):
        c = s[i]
        candles[i + 1] = candles[i] + 1 if c == '|' else candles[i]
        plates[i + 1] = plates[i] + 1 if c == '*' else plates[i]
    res = []
    # print(candles, plates)
    for query in queries:
        left, right = query
        # range too small or there are no more than two candles to fit the condition
        if right - left <= 1 or candles[right + 1] - candles[left] < 2:
            res.append(0)
        # use binary search
        else:
            # find the most left candle
            l, r = left, right
            while l < r:
                mid = (l + r) // 2
                if candles[mid + 1] - candles[left] == 0:
                    l = mid + 1
                elif (candles[mid + 1] - candles[left] > 1) or (
                        candles[mid + 1] - candles[left] == 1 and s[mid] == '*'):
                    r = mid - 1
                elif candles[mid + 1] - candles[left] == 1 and s[mid] == '|':
                    l = mid
                    break
            tar_left = l
            # find the most right candle
            l, r, num = left, right, candles[right + 1] - candles[left]
            while l < r:
                mid = (l + r) // 2
                if candles[mid + 1] - candles[left] < num:
                    l = mid + 1
                elif candles[mid + 1] - candles[left] == num and s[mid] == '*':
                    r = mid - 1
                elif candles[mid + 1] - candles[left] == num and s[mid] == '|':
                    l = mid
                    break
            tar_right = l
            count = plates[tar_right + 1] - plates[tar_left]
            res.append(count)
    return res


def platesBetweenCandles2(s: str, queries: List[List[int]]) -> List[int]:
    n = len(s)
    preSum, sum = [0] * n, 0
    left, l = [0] * n, -1
    for i, ch in enumerate(s):
        if ch == '*':
            sum += 1
        else:
            l = i
        preSum[i] = sum
        left[i] = l

    right, r = [0] * n, -1
    for i in range(n - 1, -1, -1):
        if s[i] == '|':
            r = i
        right[i] = r

    ans = [0] * len(queries)
    for i, (x, y) in enumerate(queries):
        x, y = right[x], left[y]
        if 0 <= x < y and y >= 0:
            ans[i] = preSum[y] - preSum[x]
    return ans





if __name__ == '__main__':
    print(platesBetweenCandles(s="***|**|*****|**||**|*", queries=[[1, 17], [4, 5], [14, 17], [5, 11], [15, 16]]))
