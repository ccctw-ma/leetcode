from typing import List

import numpy as np
import matplotlib.pyplot as plt
import math

from numpy import ndarray


def normal_distribution(x, mu, sigma):
    return np.exp(-1 * ((x - mu) ** 2) / (2 * (sigma ** 2))) / (math.sqrt(2 * np.pi) * sigma)


# 绘制正态分布图
def plot_normal_distribution(dataSet: List[List[int]]) -> None:
    rates = [c / b * 1000 for a, b, c in dataSet]
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


# 绘制U图
def plot_u(dataSet: List[List[int]]) -> None:
    dataSet = np.array(dataSet)
    As = np.sum(dataSet[:, 1])  # 代码行数的总和
    Us = np.sum(dataSet[:, 2])  # 缺陷数的总和
    CL = Us / As * 1000  # 缺陷数每千行
    print(f'中心线为{CL}缺陷数/KSLOC')
    # 计算每个模块的控制上限和下限
    UCL, LCL = [], []
    abnormal_module_set = []
    for i, a, u in dataSet:
        UCL_i = CL + 3 * np.sqrt(CL / (a / 1000))
        LCL_i = CL - 3 * np.sqrt(CL / (a / 1000))
        UCL.append(UCL_i)
        LCL.append(LCL_i)
        cur = u / a * 1000
        if cur < LCL_i or cur > UCL_i:
            print(f'模块{i}为异常模块')
            abnormal_module_set.append([i, cur])
    abnormal_module_set = np.array(abnormal_module_set)
    xs = [i + 1 for i in range(len(dataSet))]
    plt.plot(xs, UCL, label='UCL')
    plt.plot(xs, LCL, label='LCL')
    plt.plot(xs, [c / b * 1000 for a, b, c in dataSet])
    plt.hlines(CL, 0, len(dataSet), colors='pink', label='CL', linestyles='dashed')
    plt.scatter(abnormal_module_set[:, 0], abnormal_module_set[:, 1], c='red')
    plt.legend()
    plt.grid()
    plt.show()


# 绘制XmR图
def plot_XmR(dataSet: ndarray) -> None:
    # 每个模块的每千行代码的缺陷数
    defects = [c / b * 1000 for _, b, c in dataSet]
    print(defects)
    defect_hat = np.mean(defects)
    print(f'平均缺陷数为{defect_hat}')
    mRs = []
    for i in range(1, len(defects)):
        mR_i = abs(defects[i] - defects[i - 1])
        mRs.append(mR_i)
    mR_hat = np.mean(mRs)
    print(f'平均滑动范围为{mR_hat}')
    CL = defect_hat
    print(f'中心线为{CL}')
    UNPL = CL + 2.660 * mR_hat
    LNPL = CL - 2.660 * mR_hat
    print(f'自然过程的上限{UNPL}')
    print(f'自然过程的下限{LNPL}')
    for i, d in enumerate(defects):
        if d > UNPL or d < LNPL:
            print(f'模块{i + 1}为异常模块')
    print(f'平均滑动范围的中心线{mR_hat}')
    UCL = 3.269 * mR_hat
    print(f'滑动范围的上限{UCL}')
    print(f'滑动范围的下限不存在')
    xs = [i + 1 for i in range(len(dataSet))]
    plt.plot(xs, defects)
    plt.hlines(defect_hat, 0, len(defects), linestyles='dashed', colors='black',
               label=f'CL{round(float(defect_hat), 2)}')
    plt.hlines(UNPL, 0, len(defects), linestyles='dashed', colors='blue', label=f'UCL{round(UNPL, 2)}')
    plt.hlines(LNPL, 0, len(defects), linestyles='dashed', colors='red', label=f'LCL{round(LNPL, 2)}')
    plt.grid()
    plt.legend()
    plt.show()
    xs = xs[1:]
    plt.plot(xs, mRs, label='mR')
    plt.hlines(mR_hat, 2, len(xs) + 1, linestyles='dashed', colors='blue', label=f'CL={round(float(mR_hat), 2)}')
    plt.hlines(UCL, 2, len(xs) + 1, linestyles='dashed', colors='red', label=f'UCL={round(float(UCL), 2)}')
    plt.grid()
    plt.legend()
    plt.show()
    for i, mr in enumerate(mRs):
        if mr > UCL:
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
    # plot_normal_distribution(dataSet)
    # plot_u(dataSet)
    plot_XmR(np.array(dataSet))
