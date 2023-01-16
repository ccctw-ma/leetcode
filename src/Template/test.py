ff = open("test.txt", 'w')
ff.write('[' + '\n')

with open('./算法平台数据.txt', 'r') as f:
    for line in f.readlines():
        ff.write(line + ',')

ff.write(']')
ff.close()
