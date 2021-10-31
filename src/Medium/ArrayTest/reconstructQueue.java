package Medium.ArrayTest;


/*
* 406. 根据身高重建队列
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/11/16*/


public class reconstructQueue {


    public int[][] reconstructQueue(int[][] people) {
        int n=people.length;
        Arrays.sort(people, (o1, o2) -> o1[0]==o2[0]? o1[1]-o2[1] : o1[0]-o2[0]);
        int[][] res=new int[n][2];
        int[] bucket=new int[n];
        for (int[] arr:people){
            int temp=arr[1];
            for (int i=0;i<n;i++){
                if(bucket[i]==0||(bucket[i]==1&&res[i][0]==arr[0])){
                    if(temp==0){
                        res[i][0]=arr[0];
                        res[i][1]=arr[1];
                        bucket[i]=1;
                        break;
                    }else {
                        temp--;
                    }
                }
            }
        }
        return res;
    }

    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            //相当于插入
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }



    public static void main(String[] args) {
        int[][] arr=new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res=new int[arr.length][2];
        System.out.println(Arrays.deepToString(new reconstructQueue().reconstructQueue2(arr)));
    }
}
