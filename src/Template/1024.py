from functools import cache
from itertools import permutations


def test(nums, ops):
    @cache
    def check(a, b, c):
        if (c == '/' or c == '//') and b == 0:
            return -1
        return eval(f'{int(a)}{c}{int(b)}')

    res = []
    for a, b, c, d in permutations(nums, 4):
        for i, j, k in permutations(ops, 3):
            temp = check(a, b, i)
            temp = check(temp, c, j)
            temp = check(temp, d, k)
            if int(temp) == 1024:
                res.append((temp, f'(({a}{i}{b}){j}{c}){k}{d}'))
    print('total: ', len(res))
    for r in res:
        print(r)


nums = [17, 2, 5, 7, 25, 7, 4, 4, 6, 2, 2, 2, 2, 2, 2, 30, 0]
ops = ['*', '&', '//']
test(nums, ops)
