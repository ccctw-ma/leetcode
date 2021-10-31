package BUPT;

import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.*;

public class test100_409 {

    /**
     * @apiNote 101. IP地址
     * @// TODO: 2021/1/25  */
    public void test101(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            String[] strings = in.next().trim().replace('.',' ').split(" ");
            if(strings.length!=4){
                System.out.println("No");
                continue;
            }
            boolean flag = true;
            for (String s:strings){
                try {
                    int num = Integer.parseInt(s);
                    if(num>=0&&num<=255){
                        flag = true;
                    }else {
                        flag = false;
                        System.out.println("No");
                        break;
                    }
                }catch (Exception e){
                    flag = false;
                    System.out.println("No");
                    break;
                }
            }
            if(flag){
                System.out.println("Yes");
            }
        }
    }

    /**
     * @apiNote 103. 反转单词
     * @// TODO: 2021/1/27  */
    public void test103(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            String[] temp = in.nextLine().trim().split(" ");
            int l = 0, r = temp.length-1;
            while (l<r){
                String s = temp[l];
                temp[l] = temp[r];
                temp[r] = s;
                l++;
                r--;
            }
            StringBuilder builder = new StringBuilder();
            for (String s : temp) {
                builder.append(s).append(" ");
            }
            builder.deleteCharAt(builder.length()-1);
            System.out.println(builder.toString());
        }
    }

    public void test102(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int num = in.nextInt();
            in.nextLine();
            int positive = 0;
            int negative = 0;
            List<Integer> list = new ArrayList<Integer>();
            while ((--num)>=0){
                String[] strings = in.nextLine().trim().split(" ");
                String temp =strings[0];
                int number = Integer.parseInt(strings[1]);
                if(temp.equals("left")) {
                    list.add(360-number);
                }else if(temp.equals("right")) {
                    list.add(number);
                }else if(temp.equals("forward")) {
                    positive += Integer.parseInt(strings[1]);
                }else {
                    negative += Integer.parseInt(strings[1]);
                }
            }
            Set<Integer> set = new HashSet<Integer>();
            for (Integer integer : list) {
                Set<Integer> tempSet = new HashSet<Integer>();
                for (int j : set) {
                    tempSet.add((integer + j) % 360);
                }
                tempSet.add(integer);
                set.addAll(tempSet);
            }
            int min = 180;
            for (int key:set){
                min = Math.min(Math.abs(key-180),min);
            }
            double res = Math.sqrt(positive*positive+negative*negative-2*positive*negative*Math.cos(Math.toRadians(180-min)));
            System.out.println(String.format("%.3f",res));
        }
    }

    /**
     * @apiNote 104. 912星球的研究生
     * @// TODO: 2021/1/29  */
    public void test104(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int n = in.nextInt();
            in.nextLine();
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            while ((--n)>=0){
                String[] strings = in.nextLine().trim().split(" ");
                if(strings[0].equals("Insert")){
                    int id = Integer.parseInt(strings[1]);
                    int score = Integer.parseInt(strings[2]);
                    map.put(id,score);
                }else {
                    int id = Integer.parseInt(strings[1]);
                    if(map.containsKey(id)){
                        System.out.println("name:"+id+" score:"+map.get(id));
                    }else {
                        System.out.println("No result!");
                    }
                }
            }
        }
    }

    public void test105(){
        class TreeNode{
            private int val;
            private int parent;
            private List<Integer> children;
            public TreeNode(int val){
                this.val = val;
                this.parent = Integer.MAX_VALUE;
                this.children = new ArrayList<Integer>();
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int N = in.nextInt();
        int M = in.nextInt();
        int L = in.nextInt();
        TreeNode[] treeNodes = new TreeNode[N];
        for (int i=0;i<N;i++){
            treeNodes[i] = new TreeNode(i);
        }
        while ((--M)>=0){
            int a = in.nextInt();
            int b = in.nextInt();
            treeNodes[b].parent = a;
            treeNodes[a].children.add(b);
        }
        double res = 1.0;
        int parent = treeNodes[L].parent;
        while (parent!=Integer.MAX_VALUE){
            int n = treeNodes[parent].children.size();
            res/=n;
            parent = treeNodes[parent].parent;
        }
        System.out.println(String.format("%.6f",res));
    }


    public void test107(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            String s = in.next().trim();
            int t = in.nextInt();
            in.nextLine();
            while ((--t)>=0){
                String[] temp = in.nextLine().trim().split(" ");
                int index = Integer.parseInt(temp[1]);
                int len = Integer.parseInt(temp[2]);
                String a = s.substring(0,index);
                String c = s.substring(index+len);
                String b = temp[0].equals("0") ? new StringBuilder(s.substring(index,index+len)).reverse().toString() :temp[3];
                s = a+b+c;
                System.out.println(s);
            }
        }
    }

    /**
     * @apiNote 108. 虚数
     * @// TODO: 2021/1/30  */
    public void test108(){
        class Node {
            private int x;
            private int y;
            private int val;
            public Node(int x, int y) {
                this.x = x;
                this.y = y;
                val = x*x + y*y;
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int num = in.nextInt();
            in.nextLine();
            List<Node> list = new ArrayList<Node>();
            while ((--num)>=0){
                String[] temp = in.nextLine().trim().split(" ");
                if(temp[0].equals("Pop")){
                    if(list.isEmpty()){
                        System.out.println("Empty!");
                    }else {
                        int max = list.get(0).val;
                        int index = 0;
                        for (int i=1;i<list.size();i++){
                            if(list.get(i).val>max){
                                max = list.get(i).val;
                                index = i;
                            }
                        }
                        Node node = list.remove(index);
                        System.out.println(node.x+"+i"+node.y);
                        if(list.isEmpty()){
                            System.out.println("Empty!");
                        }else {
                            System.out.println("Size: "+list.size());
                        }
                    }
                }else if(temp[0].equals("Insert")){
                    String s =temp[1];
                    int x = Integer.parseInt(s.substring(0,s.indexOf('+')));
                    int y = Integer.parseInt(s.substring(s.indexOf('+')+2));
                    list.add(new Node(x,y));
                    System.out.println("Size: "+list.size());
                }
            }
        }
    }

    public void test109(){
        class Node{
            int val;
            char c;
            List<Integer> list ;
            public Node(int val,char c){
                this.val = val;
                this.c = c;
                list = new ArrayList<Integer>();
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            int n = in.nextInt();
            String s = in.next().trim();
            Map<Character,Integer> map = new HashMap<Character,Integer>();
            for (int i=0;i<n;i++){
                map.put(s.charAt(i),i);
            }
            Node[] nodes = new Node[n];
            for (int i=0;i<n;i++){
                nodes[i] = new Node(i,s.charAt(i));
            }
            while ((--n)>=1){
                int a = in.nextInt();
                int b = in.nextInt();
                nodes[a].list.add(b);
                nodes[b].list.add(a);
                Collections.sort(nodes[a].list);
                Collections.sort(nodes[b].list);
            }
            String res = null;
            for (int i=0;i<n;i++){
                StringBuilder builder = new StringBuilder();

            }
        }
    }

    public void test110(){
        class Symbol{
            char c;
            Set<Character> terminals;
            Set<Character> subSymbols;
            boolean hasNull;

            public Symbol(char c){
                this.c = c;
                terminals = new HashSet<Character>();
                subSymbols = new HashSet<Character>();
                hasNull = false;
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            int n = in.nextInt();
            in.nextLine();
            Map<Character,Symbol> map = new HashMap<Character,Symbol>();
            while ((--n)>=0){
                String[] strings = in.nextLine().trim().split(" ");
                char nonTerminal = strings[0].charAt(0);
                if(!map.containsKey(nonTerminal)){
                    map.put(nonTerminal,new Symbol(nonTerminal));
                }
                String production = strings[1];
                char c = production.charAt(0);
                if(c=='#'){
                    map.get(nonTerminal).hasNull = true;
                }else if(c>='a'&&c<='z'){
                    map.get(nonTerminal).terminals.add(c);
                }else if(c>='A'&&c<='Z'){
                    map.get(nonTerminal).subSymbols.add(c);
                    //均为非终结符
                    boolean flag1 = true;
                    //first集里均有'#'
                    boolean flag2 = true;
                    for (int i=1;i<production.length();i++){
                        char temp = production.charAt(i);
                        if(temp>='A'&&temp<='Z'&&map.containsKey(temp)){
                            if(!map.get(temp).hasNull){
                                flag2 = false;
                            }
                        }else {
                            flag1 = false;
                            flag2 = false;
                            break;
                        }
                    }
                    if(flag1&&flag2){
                        map.get(nonTerminal).hasNull = true;
                    }else if(flag1&&!flag2){
                        for (int i=0;i<production.length();i++){
                            char temp = production.charAt(i);
                            if(map.get(temp).hasNull){
                                map.get(nonTerminal).subSymbols.add(temp);
                            }
                        }
                    }

                }
            }
        }
    }

    public void test130(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        long n = in.nextLong();
        long r = (long) Math.sqrt(n);
        boolean find = false;
        for (long i = 81;i>=0;i--){
            long x = r - i;
            if(x>0&&compute(x)==n){
                System.out.println(x);
                find = true;
                break;
            }
        }
        if(!find) System.out.println(-1);
    }

    private long compute(long x){
        long ans = 0;
        long t = x;
        while (x!=0){
            ans+=x%10;
            x/=10;
        }
        return t*t + ans*t;
    }

    /**
     * @apiNote 407. BLOCKS
     * @// TODO: 2021/2/1
     * */
    public void test407(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            char[][] graph = new char[n][m];
            boolean[][] visited = new boolean[n][m];
            for (int i=0;i<n;i++){
                String s = in.next().trim();
                for (int j=0;j<m;j++){
                    graph[i][j] = s.charAt(j);
                }
            }
            int count = 0;
            boolean flag = true;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    if(graph[i][j]=='#'&&!visited[i][j]){
                        int num = 0;
                        //左上角
                        int ltx = i;
                        int lty = j;
                        //右下角
                        int rbx = i;
                        int rby = j;
                        int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
                        //bfs遍历获取能到达的所有节点
                        LinkedList<int[]> list = new LinkedList<int[]>();
                        list.add(new int[]{i,j});
                        visited[i][j] = true;
                        while (!list.isEmpty()){
                            int size = list.size();
                            num+=size;
                            for (int k=0;k<size;k++){
                                int[] node = list.poll();
                                int x = node[0];
                                int y = node[1];
                                ltx = Math.min(ltx,x);
                                lty = Math.min(lty,y);
                                rbx = Math.max(rbx,x);
                                rby = Math.max(rby,y);
                                for (int[] arr:dirs){
                                    int nx = x+arr[0];
                                    int ny = y+arr[1];
                                    if(nx>=0&&nx<graph.length&&ny>=0&&ny<graph[0].length&&graph[nx][ny]=='#'&&!visited[nx][ny]){
                                        visited[nx][ny] = true;
                                        list.add(new int[]{nx,ny});
                                    }
                                }
                            }

                        }
                        if((rbx-ltx+1)*(rby-lty+1)==num){
                            count++;
                        }else{
                            flag= false;
                            break;
                        }
                    }
                }
                if(!flag) break;
            }
            if(!flag){
                System.out.println("So Sad");
            }else {
                System.out.println("There are "+count+" ships.");
            }
        }
    }

    /**
     * @apiNote 408. 字符串
     * @// TODO: 2021/2/2  */
    public void test408(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        Map<String,Integer> map = new HashMap<String,Integer>();
        while (in.hasNext()){
            String t = in.next().trim();
            if(map.containsKey(t)){
                map.put(t,map.get(t)+1);
            }else {
                map.put(t,1);
            }
            System.out.println(map.get(t));
        }
    }


    /**
     * @apiNote 409. 数的关系
     * @// TODO: 2021/2/22*/
    public void test409(){
        Scanner cin=new Scanner(System.in);
        /**
         * one thing you have to know:
         * f[i]=i!;
         * dp[i][j]=dp[i-1][j]*(j)+(dp[i-1][j-1]);
         * 当我们有i个数j个取值时，dp[i][j]就是此时数值的组合种类；
         * the end result is ans[i]={dp[i][k]*f[k](k is from one to i)}
         */
        BigInteger[] f = new BigInteger[101];
        BigInteger[][] dp = new BigInteger[101][101];
        BigInteger[] ans = new BigInteger[101];
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                dp[i][j]=BigInteger.ZERO;
            }
            ans[i]=BigInteger.ZERO;
        }
        f[1]=BigInteger.ONE;
        f[2]=BigInteger.valueOf(2);
        f[3]=BigInteger.valueOf(6);
        dp[1][1]=BigInteger.ONE;dp[2][1]=BigInteger.ONE;
        dp[2][2]=BigInteger.ONE;dp[3][1]=BigInteger.ONE;
        dp[3][2]=BigInteger.valueOf(3);
        dp[3][3]=BigInteger.ONE;
        for(int i=4;i<101;i++){
            /**
             we use f[i] to record i!
             */
            dp[i][1]=dp[i][i]= BigInteger.ONE;
            f[i]=f[i-1].multiply(BigInteger.valueOf(i));
        }
        for(int i=4;i<101;i++){
            for(int j=2;j<101;j++){
                dp[i][j]=dp[i-1][j].multiply(BigInteger.valueOf(j)).add(dp[i-1][j-1]);
            }
        }
        for(int i=1;i<101;i++){
            for(int j=1;j<=i;j++){
                ans[i]=ans[i].add(dp[i][j].multiply(f[j]));
            }
        }
        while(cin.hasNext()){
            int n=cin.nextInt();
            System.out.println(ans[n]);
        }
        cin.close();
    }

    public static void main(String[] args) {

    }
}
