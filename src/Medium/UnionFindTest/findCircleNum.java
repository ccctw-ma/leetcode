package Medium.UnionFindTest;


/*
* 547. 朋友圈
班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

示例 1:

输入:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
输出: 2
说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回2。
示例 2:

输入:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
输出: 1
说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
注意：

N 在[1,200]的范围内。
对于所有学生，有M[i][i] = 1。
如果有M[i][j] = 1，则有M[j][i] = 1。*/

import java.math.BigInteger;

/**
 * @author 马世臣
 * @// TODO: 2020/6/4  */


public class findCircleNum {


    public int findCircleNum(int[][] M) {
        int a=M.length;
        for (int i=0;i<a;i++){
            for (int j=0;j<a;j++){
                for (int k=0;k<a;k++){
                    if(M[j][k]==1) continue;
                    if(M[j][i]==1&&M[i][k]==1) M[j][k]=1;
                }
            }
        }
        int[] arr=new int[a];
        int count=0;
        for (int i=0;i<a;i++){
            if(arr[i]==0){
                arr[i]=1;
                count++;
                for (int j=0;j<a;j++){
                    if(M[i][j]==1) arr[j]=1;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        BigInteger integer=new BigInteger("2133333333333333");
        System.out.println();
        System.out.println(Math.log(Double.MAX_VALUE)/Math.log(2));
    }
}
