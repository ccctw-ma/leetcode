"""
作业1
"""
import numpy as np

class Work:

    def problem1(self):
        return [i ** 2 for i in range(10)]


if __name__ == '__main__':

    a = np.array([1, 2, 3])
    a.sort()
    print(a)