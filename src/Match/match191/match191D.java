package Match.match191;

import java.io.IOException;
import java.util.*;

public class match191D {

    public boolean hasAllCodes(String s, int k) {
        Set<Integer> set=new HashSet<>();
        for (int i=k;i<=s.length();i++){
            String temp=s.substring(i-k,i);
            int n=0;
            for (int j=0;j<k;j++){
                n*=2;
                n+=temp.charAt(j)-'0';
            }
            set.add(n);
        }
        int sum= (int) Math.pow(2,k);
        for (int i=0;i<sum;i++){
            if(!set.contains(i)) return false;
        }
        return true;
    }


    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
//        node[] nodes=new node[n];
//        for (int i=0;i<n;i++)   nodes[i]=new node(i);
//        for (int[] prerequisite : prerequisites) {
//            nodes[prerequisite[0]].nexts.add(prerequisite[1]);
//        }
//        List<Boolean> list=new ArrayList<>();
//        for (int[] que:queries){
//            if(traverse(nodes,que[0],que[1])){
//                list.add(true);
//            }else {
//                list.add(false);
//            }
//        }

        Map<Integer,Set<Integer>> map=new HashMap<>();
        for (int i=0;i<n;i++){
            map.put(i,new HashSet<>());
        }
        for (int[] prerequisite : prerequisites) {
            map.get(prerequisite[0]).add(prerequisite[1]);
        }
        visited=new int[n];
        for (int i=0;i<n;i++){
            if(map.get(i).size()==0){
                traverse(map,i);
            }
        }
        List<Boolean> list=new ArrayList<>();
        for (int[] que:queries){
            if(map.get(que[0]).contains(que[1])){
                list.add(true);
            }else {
                list.add(false);
            }
        }
        return list;
    }


    int[] visited;
    private void traverse(Map<Integer,Set<Integer>> map,int key){
       if(visited[key]==0){

       }else {
           return ;
       }
    }


    class node{
        int id;
        Set<Integer> nexts;
        node(int id){
            this.id=id;
            nexts=new HashSet<>();
        }
    }


    public List<Boolean> checkIfPrerequisite2(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] G = new boolean[n][n];
        for (int[] prerequisite : prerequisites) {
            G[prerequisite[0]][prerequisite[1]] = true;
        }


        //第一个for循环使用i作为中转进行运算
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(G[j][i] && G[i][k])
                        G[j][k] = true;
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(G[query[0]][query[1]]);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(new match191D().hasAllCodes("00110",2));


        /*int[] ints=new int[]{1,2,3,4,5,6};
        int[] news=Arrays.stream(ints).filter(a->(a&1)==0).toArray();
        System.out.println(Arrays.toString(news));*/

        int num1 = 111, num2 = 899;
        while (num2 != 0) {
            // 让num2变成进位，num1就是不考虑进位的值，一直到num2为0，num1就是结果
            int temp = num1;
            num1 = num1 ^ num2;
            num2 = (temp & num2) << 1;
            System.out.println("num1:" + num1 + "num2" + num2);
        }
        System.out.println(num1);

        int n= 1_000_000_00;
        int[] arr=new int[n];
        Random random=new Random();
        for (int i=0;i<n;i++){
            arr[i]=random.nextInt(10);
        }
        long pre=System.currentTimeMillis();
        System.out.println(Arrays.stream(arr).sum());
        System.out.println(System.currentTimeMillis()-pre);

        System.out.println();


        int sum=0;
        long time=System.currentTimeMillis();
        for (int i:arr) sum+=i;
        System.out.println(sum);
        System.out.println(System.currentTimeMillis()-time);
    }


}
