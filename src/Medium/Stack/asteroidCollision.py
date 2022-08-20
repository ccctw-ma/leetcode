from typing import List


class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        res = []
        for a in asteroids:
            if not res or not (a < 0 < res[-1]):
                res.append(a)
            else:
                f = True
                while res:
                    if a < 0 < res[-1]:
                        if res[-1] < -a:
                            res.pop()
                        elif res[-1] == -a:
                            res.pop()
                            f = False
                            break
                        else:
                            f = False
                            break
                    else:
                        break
                if f:
                    res.append(a)

        return res

    def asteroidCollision2(self, asteroids: List[int]) -> List[int]:
        st = []
        for aster in asteroids:
            alive = True
            while alive and aster < 0 and st and st[-1] > 0:
                alive = st[-1] < -aster
                if st[-1] <= -aster:
                    st.pop()
            if alive:
                st.append(aster)
        return st


if __name__ == '__main__':
    print(Solution().asteroidCollision(asteroids=[-2, -2, 1, -2]))
