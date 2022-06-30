import math

import matplotlib.pyplot as plt
import numpy as np


class Work1:

    def __init__(self):
        self.p = 0
        self.q = 0
        self.r = 0



    def Jia_compute(self):
        min_d = 100
        for p in np.arange(3.0, 4.01, 0.01):
            for q in np.arange(1.0, 2.01, 0.01):
                for r in np.arange(4.0, 5.01, 0.01):
                    delta = 0.0
                    for c in np.arange(1.0, 2.01, 0.01):
                        x0 = (p * c + q) / (c + r)
                        d = ((x0 - c ** 0.5) / (x0 + c ** 0.5)) ** 2
                        if d > delta:
                            delta = d
                    if delta < min_d:
                        min_d = delta
                        self.p = p
                        self.q = q
                        self.r = r
                        print(f'p: {p}, q: {q}, r: {r}, d: {delta}')

        return min_d

    # 画图
    def Jia_plot_res(self):
        cs = np.linspace(1.0, 2.0, 100)
        p, q, r = 3.57, 1.64, 4.21
        Y_s = (p * cs + q) / (cs + r)
        Y_s2 = cs ** 0.5
        plt.plot(cs, Y_s, label="pc + q / c + r")
        plt.plot(cs, Y_s2, label="c ^ 0.5")
        plt.legend()
        plt.show()
        ds = abs(Y_s2 - Y_s)
        plt.plot(cs, ds, label="delta")
        plt.legend()
        plt.show()

    def Yi_compute(self, c):
        def fx(x):
            return 1 / x ** 2 - c

        xs = []
        ys = []
        if c <= 0:
            print("c必须为正数")
            return
        x0, x1 = 1 / c if c > 1 else c, 0
        xs.append(x0)
        ys.append(fx(x0))
        while True:
            x1 = (3 * x0 - c * (x0 ** 3)) / 2
            print(x1)
            xs.append(x1)
            ys.append(fx(x1))
            if abs(x1 - x0) < 1e-6:
                print(x1)
                break
            x0 = x1

        plt.plot(xs, ys, label=f'c = {c}')
        plt.hlines(0, 0, max(x1, 1 / c), colors='red')
        plt.legend()
        plt.show()
        return



    def q_sqrt(self, num):
        t = num
        t = 0x5fe6eb50c7aa19f9 - (t >> 1)
        j = 0
        while not (t * t <= num - 9e-6 and (t + 1) * (t + 1) > num + 9e-6):
            t = (num / t + t) / 2
            # 对于部分数字,所求结果将非常接近num但会有细微差别.
            # 对运算次数进行限制,避免死循环.
            if j == t:
                return t
            else:
                j = t
        return t


if __name__ == "__main__":
    w = Work1()
    # w.Jia_compute()
    # w.Jia_plot_res()
    # w.Yi_compute(0.15)
    print(w.q_sqrt(16))
