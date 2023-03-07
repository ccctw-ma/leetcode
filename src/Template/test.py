# ff = open("test.txt", 'w')
# ff.write('[' + '\n')
#
# with open('./算法平台数据.txt', 'r') as f:
#     for line in f.readlines():
#         ff.write(line + ',')
#
# ff.write(']')
# ff.close()


res = 0
for i in range(1, 1001):
    if '0' in str(i):
        res += 1
print(res)