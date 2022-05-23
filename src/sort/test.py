def beita(be):
    return be / (be + 0.95)


be = 1
print(0, be)
for i in range(100):
    be = beita(be)

    print(i, be)
