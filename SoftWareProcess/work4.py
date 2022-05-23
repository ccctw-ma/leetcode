from typing import List

import numpy as np
import matplotlib.pyplot as plt
import math


def normal_distribution(x, mu, sigma):
    return np.exp(-1 * ((x - mu) ** 2) / (2 * (sigma ** 2))) / (math.sqrt(2 * np.pi) * sigma)


def plot_normal_distribution(dataSet: List[List[int]]) -> None:
    rates = [c / b for a, b, c in dataSet]
    mean_rate = np.mean(rates)  # 中线cl
    var_rate = np.var(rates)
    std_rate = np.std(rates)
    print(rates)
    print(mean_rate)
    print(var_rate)
    print(std_rate)
    ucl = mean_rate + 3 * std_rate  # 上线
    lcl = mean_rate - 3 * std_rate  # 下线
    print(ucl, lcl)
    x = np.linspace(mean_rate - 6 * std_rate, mean_rate + 6 * std_rate, 100)
    y = normal_distribution(x, mean_rate, std_rate)
    plt.plot(x, y, 'b')
    plt.vlines(mean_rate, 0, normal_distribution(mean_rate, mean_rate, std_rate), colors='r', linestyles='dashed',
               label='CL')
    plt.vlines(ucl, 0, normal_distribution(ucl, mean_rate, std_rate), colors='y', linestyles='dashed',
               label='UCL')
    plt.vlines(lcl, 0, normal_distribution(lcl, mean_rate, std_rate), colors='black', linestyles='dashed',
               label='LCL')
    plt.grid()
    plt.legend()
    plt.show()
    for i, rate in enumerate(rates):
        if rate < lcl or rate > ucl:
            print(f'模块{i + 1}为异常模块')


if __name__ == '__main__':
    dataSet = [
        [1, 370, 8],
        [2, 350, 6],
        [3, 140, 15],
        [4, 500, 3],
        [5, 112, 1],
        [6, 420, 4],
        [7, 160, 9],
        [8, 320, 8],
        [9, 250, 6],
        [10, 180, 13],
        [11, 204, 10],
        [12, 132, 3],
        [13, 200, 30],
        [14, 249, 4],
        [15, 330, 5],
        [16, 230, 6],
        [17, 230, 15],
        [18, 290, 12],
        [19, 180, 10],
        [20, 160, 5]
    ]
    plot_normal_distribution(dataSet)
