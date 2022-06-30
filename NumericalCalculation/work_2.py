import numpy as np
import time


class Work2:

    def __init__(self):
        self.i = 1

    def gauss_ch1(self, f, n: int) -> float:
        x = np.cos((np.linspace(1, n, n) * 2 - 1) * np.pi / (2 * n))
        y = f(x)
        res = np.sum(y) * np.pi / n
        return res

    def gauss_ch2(self, f, n: int) -> float:
        k = (np.linspace(1, n, n) * np.pi) / (n + 1)
        x = np.cos(k)
        fx = f(x)
        sin_2_fx = np.power(np.sin(k), 2)
        res = np.sum(np.multiply(sin_2_fx, fx)) * np.pi / (n + 1)
        return res

    def gauss_leg_9(self, f) -> float:
        x = np.array([0.00, -0.8360311073266358, 0.8360311073266358,
                      -0.9681602395076261, 0.9681602395076261, -0.3242534234038089,
                      0.3242534234038089, -0.6133714327005904, 0.6133714327005904])

        A = np.array([0.3302393550012598, 0.1806481606948574, 0.1806481606948574,
                      0.0812743883615744, 0.0812743883615744, 0.3123470770400029,
                      0.3123470770400029, 0.2606106964029354, 0.2606106964029354])

        fx = f(x)
        res = np.sum(np.multiply(fx, A))
        return res

    def gauss_leg_2(self, f, a: float, b: float) -> float:
        l = (b - a) / (2 * np.sqrt(3))
        r = (a + b) / 2
        p1 = -l + r
        p2 = l + r
        return (f(p1) + f(p2)) * (b - a) / 2

    def comp_gauss_leg(self, f, a: float, b: float) -> float:
        res_a = self.gauss_leg_2(f, a, b)
        res_b = self.gauss_leg_2(f, a, (a + b) / 2) + self.gauss_leg_2(f, (a + b) / 2, b)
        n = 2
        err = np.abs(res_a - res_b)
        while err > 1e-6:
            res_a = res_b
            res_b = 0
            n *= 2
            h = (b - a) / n
            ps = np.arange(a, b + h, h)
            for i in range(0, n):
                res_b += self.gauss_leg_2(f, ps[i], ps[i + 1])
            err = np.abs(res_b - res_a)
        return res_b

    def comp_trep(self, f, a: float, b: float) -> float:
        d, n = 0, 1
        h = (b - a) / n
        temp = h * (f(a) + f(b)) / 2
        k = np.arange(0, n, 1)
        xk = a + (k + 0.5) * h
        fxk = f(xk)
        temp2 = temp / 2 + h * (np.sum(fxk)) / 2
        d += 1
        n *= 2
        err = np.abs(temp2 - temp)
        while err > 1e-6:
            h /= 2
            temp = temp2
            k = np.arange(0, n, 1)
            xk = a + (k + 0.5) * h
            fxk = f(xk)
            temp2 = temp / 2 + h * (np.sum(fxk)) / 2
            d += 1
            n *= 2
            err = np.abs(temp2 - temp)
        return temp2

    def romberg(self, f, a: float, b: float) -> float:
        sw1, sw2 = 4.0 / 3.0, - 1.0 / 3.0
        cw1, cw2 = 16.0 / 15.0, -1.0 / 15.0
        rw1, rw2 = 64.0 / 63.0, -1.0 / 63.0
        ww1, ww2 = 256.0 / 255.0, - 1.0 / 255.0

        n = 1
        h = (b - a) / n
        temp = (b - a) * (f(a) + f(b)) / 2
        temp2 = np.nan
        sn = np.nan
        sn2 = np.nan
        cn4 = np.nan
        cn2 = np.nan
        rn4 = np.nan
        rn8 = np.nan
        wn8 = np.nan
        wn16 = np.nan
        err = 100.0
        while True:
            k = np.arange(0, n, 1)
            xk = a + (k + 0.5) * h
            fxk = f(xk)
            temp2 = temp / 2 + h * (np.sum(fxk)) / 2
            if wn16 is not np.nan:
                err = np.abs(temp2 - wn16)
            elif rn8 is not np.nan:
                err = np.abs(temp2 - rn8)
            elif cn4 is not np.nan:
                err = np.abs(temp2 - cn4)
            elif sn2 is not np.nan:
                err = np.abs(temp2 - sn2)
            else:
                err = np.abs(temp2 - temp)
            if err < 1e-6:
                return temp2

            if temp is not np.nan and temp2 is not np.nan:
                sn = sw1 * temp2 + sw2 * temp
                err = np.abs(sn - temp2)
                if err < 1e-6:
                    return sn

            if sn is not np.nan and sn2 is not np.nan:
                cn2 = cw1 * sn + cw2 * sn2
                err = np.abs(cn2 - sn)
                if err < 1e-6:
                    return cn2

            if cn4 is not np.nan and cn2 is not np.nan:
                rn4 = rw1 * cn2 + rw2 * cn4
                err = np.abs(rn4 - cn2)
                if err < 1e-6:
                    return rn4

            if rn8 is not np.nan and rn4 is not np.nan:
                wn8 = ww1 * rn4 + ww2 * rn8
                err = np.abs(wn8 - rn4)
                if err < 1e-6:
                    return wn8
            temp = temp2
            sn2 = sn
            cn4 = cn2
            rn8 = rn4
            wn16 = wn8
            n *= 2
            h *= 0.5
        return 0.0


# f(x) = x^2
def func(input: np.ndarray):
    return np.power(input, 2)


# f(x) = e ^ x
def exp_x(x: np.ndarray):
    return np.exp(x)


# f(x) = x * exp(x)
def x_mul_exp_x(x: np.ndarray):
    return np.multiply(x, np.exp(x))


# f(x) = e^x * sqrt(1 - x^2)
def exp_x_sqrt_x(x: np.ndarray):
    return np.multiply(np.exp(x), np.sqrt(1 - np.power(x, 2)))


if __name__ == '__main__':
    w = Work2()
    ts = time.time()
    gauss_chi_1_result = w.gauss_ch1(x_mul_exp_x, 5)
    te = time.time()
    print("gauss_chi_1: ", gauss_chi_1_result, "   t: ", (te - ts) * 1000, "ms")

    ts = time.time()
    gauss_chi_2_result = w.gauss_ch2(exp_x, 5)
    te = time.time()
    print("gauss_chi_2: ", gauss_chi_2_result, "   t: ", (te - ts) * 1000, "ms")

    ts = time.time()
    gauss_leg_9_result = w.gauss_leg_9(exp_x_sqrt_x)
    te = time.time()
    print("gauss_leg_9: ", gauss_leg_9_result, "   t: ", (te - ts) * 1000, "ms")

    ts = time.time()
    comp_gauss_leg_result = w.comp_gauss_leg(exp_x_sqrt_x, -1.0, 1.0)
    te = time.time()
    print("comp_gauss_leg: ", comp_gauss_leg_result, "   t: ", (te - ts) * 1000, "ms")

    ts = time.time()
    comp_trep_result = w.comp_trep(exp_x_sqrt_x, -1.0, 1.0)
    te = time.time()
    print("comp_trep: ", comp_trep_result, "   t: ", (te - ts) * 1000, "ms")

    ts = time.time()
    romberg_result = w.romberg(exp_x_sqrt_x, -1.0, 1.0)
    te = time.time()
    print("romberg: ", romberg_result, "   t: ", (te - ts) * 1000, "ms")
