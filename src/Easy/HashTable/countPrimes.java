package Easy.HashTable;


/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 *
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。*/

/**
 * @author 马世臣
 * @// TODO: 2020/2/1  */
public class countPrimes {


    //进行筛选
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new countPrimes().countPrimes(100));
    }
}
