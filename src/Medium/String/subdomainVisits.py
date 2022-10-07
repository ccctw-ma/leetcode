from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
        buc = defaultdict(int)
        for s in cpdomains:
            num, address = s.split(' ')
            num = int(num)
            ss = address.split('.')
            ls = len(ss)
            for i in range(ls):
                temp = ".".join(ss[i:])
                buc[temp] += num
        return [str(num) + " " + address for address, num in buc.items()]


if __name__ == '__main__':
    s = Solution()
    print(s.subdomainVisits(cpdomains=["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
                            ))
