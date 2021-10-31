package BUPT;

import java.io.BufferedInputStream;
import java.util.*;

public class test84_100 {
    /**
     * @apiNote Single Number
     * @// TODO: 2021/1/17  */
    public void test84(){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            long num = in.nextLong();
            int[] arr=new int[64];
            for (int i=0;i<num;i++){
                long temp=in.nextLong();
                for (int j=0;j<64;j++){
                    arr[j]+=(temp>>j)&1;
                    arr[j]%=3;
                }
            }
            long sum=0;
            for (int i=0;i<64;i++){
                sum+=(arr[i]<<i);
            }
            System.out.println(sum);
        }

         /*
    * #include<stdio.h>

main()
{
    long long x, n, app_1, app_2, not_three;
    while(scanf("%lld",&n)!=EOF){
        app_1=0, app_2=0;
        while(n--){
            scanf("%lld",&x);
            app_2 |= app_1&x;
            app_1 ^= x;
            not_three = ~(app_1&app_2);
            app_1 = not_three&app_1;
            app_2 = not_three&app_2;
        }
        printf("%lld\n",app_1);
    }
    return 0;
}*/
    }

    /**
     * @apiNote Three Points On A Line
     * @// TODO: 2021/1/18  */
    public void test85(){
        Scanner in=new Scanner(System.in);
        int num = in.nextInt();
        for (int i=0;i<num;i++){
            int n =in.nextInt();
            double[][] arr=new double[n][2];
            for (int j=0;j<n;j++){
                double a=in.nextDouble();
                double b=in.nextDouble();
                arr[j][0]=a;
                arr[j][1]=b;
            }
            if(n<=2){
                System.out.println("No");
            }else {
                boolean flag=false;
                for (int m=0;m<n-2;m++){
                    for (int k=m+1;k<n-1;k++){
                        for (int l=k+1;l<n;l++){
                            double a = arr[k][0]-arr[m][0];
                            double b = arr[l][0]-arr[k][0];
                            double c = arr[k][1]-arr[m][1];
                            double d = arr[l][1]-arr[k][1];
                            if(a*d==b*c){
                                System.out.println("Yes");
                                flag=true;
                                break;
                            }
                        }
                        if(flag) break;
                    }
                    if(flag) break;
                }
                if(!flag){
                    System.out.println("No");
                }
            }

        }
    }

    /**
     * @apiNote 日期
     * @// TODO: 2021/1/19
     * */
    public void test87(){
        /*日期问题，简单题*/
        Scanner in =new Scanner(System.in);
        int n =in.nextInt();
        for (int i=0;i<n;i++){
            String temp = in.next();
            String[] arr=temp.trim().split(":");
            int year = Integer.parseInt(arr[0]);
            int month = Integer.parseInt(arr[1]);
            int day = Integer.parseInt(arr[2]);
            int[] m =new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
            if((year%100==0&&year%400==0)||(year%100!=0&&year%4==0)){
                m[1]=29;
            }
            int sum=0;
            for (int j=0;j<month-1;j++){
                sum+=m[j];
            }
            sum+=day;
            System.out.println(sum);
        }
    }

    /**
     * @apiNote 最值问题
     * @// TODO: 2021/1/19  */
    public void test88(){
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            int len = in.nextInt();
            int max =Integer.MIN_VALUE;
            int subMax = Integer.MIN_VALUE;
            for (int j=0;j<len;j++){
                int temp = in.nextInt();
                if(temp>max) {
                    subMax = max;
                    max = temp;
                }
            }
            System.out.println(max+" "+subMax);
        }
    }

    /**
     * @apiNote 89. 统计时间间隔
     * @// TODO: 2021/1/20  */
    public void test89(){
        Scanner scan = new Scanner(new BufferedInputStream(System.in));
        int T = scan.nextInt();
        int h1,m1,h2,m2,time11,time22;
        String time1,time2;
        scan.nextLine();
        int time;
        for(int i = 0;i<T ;i++){
            time1 = scan.nextLine();
            h1 = Integer.parseInt(time1.trim().split(":")[0]);
            m1 = Integer.parseInt(time1.trim().split(":")[1]);
            time11 = h1*60 + m1;
            time2 = scan.nextLine();
            h2 = Integer.parseInt(time2.trim().split(":")[0]);
            m2 = Integer.parseInt(time2.trim().split(":")[1]);
            time22 = h2*60 + m2;
            if(time11<=time22){
                time = time22-time11;
                System.out.println(time);
            }else{
                time = 24*60-(time11-time22);
                System.out.println(time);
            }

        }
    }

    /**
     *  @apiNote  字符串转换
     *  @// TODO: 2021/1/20  */
    public void test90(){
        Scanner in =new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            String temp = in.next().trim();
            int[] arr=new int[26];
            Set<Character> set=new HashSet<>();
            for (char c:temp.toCharArray()){
                set.add(c);
                arr[c-'a']++;
            }
            int min =Integer.MAX_VALUE;
            for (char c:set){
                int sum=0;
                int index=c-'a';
                for (int j=0;j<26;j++){
                    sum+=arr[j]*(Math.min((j-index+26)%26,(index-j+26)%26));
                }
                min = Math.min(min,sum);
            }
            System.out.println(min);
        }
    }

    /**
     * @apiNote 91. 文件系统
     * @// TODO: 2021/1/20  */
    public void test91(){
        class Node{
            int type;
            String name;
            public Node(int type,String name){
                this.type = type;
                this.name = name;
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            int n = in.nextInt();
            in.nextLine();
            Map<String, LinkedList<Node>> map=new HashMap<String, LinkedList<Node>>();
            map.put("root",new LinkedList<Node>());
            for (int j=0;j<n;j++){
                String[] strings = in.nextLine().trim().split(" ");
                if(strings[0].equals("CREATEFILE")){
                    LinkedList<Node> list = map.get(strings[2]);
                    list.add(new Node(1,strings[1]));
                }else if(strings[0].equals("CREATEDIR")){
                    LinkedList<Node> list = map.get(strings[2]);
                    list.add(new Node(2,strings[1]));
                    map.put(strings[1],new LinkedList<Node>());
                }else if(strings[0].equals("LISTFILE")){
                    LinkedList<Node> list = map.get(strings[1]);
                    for (Node node:list){
                        if(node.type==1){
                            System.out.println(node.name);
                        }
                    }
                }else {
                    LinkedList<Node> list = map.get(strings[1]);
                    for (Node node:list){
                        if(node.type==2){
                            System.out.println(node.name);
                        }
                    }
                }

            }
        }
    }

    /**
     * @apiNote 92. 统计节点个数
     * @// TODO: 2021/1/20  */
    public void test92(){
        class TreeNode{
            //度
            int degree;
            int parent=Integer.MAX_VALUE;
            List<Integer> sons = new ArrayList<Integer>();
            public TreeNode(){
                degree = 0;
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            int num = in.nextInt();
            TreeNode[] nodes=new TreeNode[1001];
            for (int j=0;j<1001;j++){
                nodes[j] = new TreeNode();
            }
            for (int j=0;j<num-1;j++){
                int x = in.nextInt();
                int y = in.nextInt();
                nodes[x].sons.add(y);
                nodes[x].degree++;
                nodes[y].degree++;
                nodes[y].parent = x;
            }
            int sum = 0;
            for (int j=0;j<1001;j++){
                TreeNode temp = nodes[j];
                int degree = temp.degree;
                if(degree!=0){
                    boolean flag = true;
                    if(temp.parent!=Integer.MAX_VALUE&&degree<nodes[temp.parent].degree){
                        flag = false;
                    }
                    for (int k :temp.sons){
                        if(degree<nodes[k].degree){
                            flag = false;
                            break;
                        }
                    }
                    if(flag) sum++;
                }
            }
            System.out.println(sum);
        }
    }

    /**
     * @apiNote 93. 中序遍历序列
     * @// TODO: 2021/1/20  */
    public void test93(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            int num = in.nextInt();
            boolean flag = true;
            int p = in.nextInt();
            for(int j=1;j<num;j++){
                int cur = in.nextInt();
                if(p<cur){
                    p = cur;
                }else {
                    flag = false;
                }
            }
            if(flag){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }
    }

    public void test94(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T =in.nextInt();
        while ((--T)>=0){
            StringBuilder s = new StringBuilder(in.next().trim());
            int n = in.nextInt();
            for (int j=0;j<n;j++){
                String temp = in.next().trim();
                if(temp.equals("INSERT")){
                    String c = in.next().trim();
                    s.append(c);
                }else {
                    String c = in.next().trim();
                    int index = Integer.parseInt(c);
                    char tar = s.charAt(index);
                    int left = index-1;
                    int right = index+1;
                    for (;left>=0;left--){
                        if(s.charAt(left)==tar){
                            break;
                        }
                    }
                    for (;right<s.length();right++){
                        if (s.charAt(right) == tar) {
                            break;
                        }
                    }
                    if(left==-1&&right==s.length()){
                        System.out.println(-1);
                    }else if(left==-1&&right!=s.length()){
                        System.out.println(right-index);
                    }else if(left!=-1&&right==s.length()){
                        System.out.println(index-left);
                    }else {
                        int ans = index-left<=right-index?index-left:right-index;
                        System.out.println(ans);
                    }

                }

            }
        }
    }


    /**
     * @apiNote 95. 二进制数
     * @// TODO: 2021/1/22  */
    public void test95(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int num = in.nextInt();
        for (int i=0;i<num;i++){
            int temp = in.nextInt();
            if(temp == 0) {
                System.out.println("0");
            }else {
                StringBuilder builder =new StringBuilder();
                while (temp!=0){
                    if((temp&1)==1){
                        builder.append('1');
                    }else {
                        builder.append('0');
                    }
                    temp>>=1;
                }
                System.out.println(builder.reverse());
            }
        }
    }

    /**
     * @apiNote 96. 矩阵幂
     * @// TODO: 2021/1/22 */
    public void test96(){
        Scanner in=new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        int[][] a=new int[11][11];
        int[][] b=new int[11][11];
        int[][] c=new int[11][11];
        while ((--T)>=0){
            int n =in.nextInt();
            int k =in.nextInt();
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    a[i][j] = in.nextInt();
                    b[i][j]=a[i][j];
                    c[i][j]=a[i][j];
                }
            }
            while (--k!=0){
                for (int i=0;i<n;i++){
                    for (int j=0;j<n;j++){
                        int val = 0;
                        for (int t=0;t<n;t++){
                            val+=b[i][t]*a[t][j];
                        }
                        c[i][j]=val;
                    }
                }
                for (int i=0;i<n;i++){
                    for (int j=0;j<n;j++){
                        b[i][j]=c[i][j];
                    }
                }
            }
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    System.out.print(c[i][j]);
                    if(j==n-1){
                        System.out.println();
                    }else {
                        System.out.print(" ");
                    }
                }
            }
        }
    }

    /**
     * @apiNote 97. 二叉排序树
     * @// TODO: 2021/1/25  */
    public void test97(){
        class TreeNode{
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int val){
                this.val = val;
            }
        }

        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        TreeNode root = null;
        if(T>0) {
            root = new TreeNode(in.nextInt());
            System.out.println(-1);
        }
        while ((--T)!=0){
            TreeNode temp = root;
            int val = in.nextInt();
            while (temp!=null){
                if(temp.val>val){
                    if(temp.left!=null){
                        temp = temp.left;
                    }else {
                        temp.left = new TreeNode(val);
                        System.out.println(temp.val);
                        break;
                    }
                }else {
                    if(temp.right!=null){
                        temp = temp.right;
                    }else{
                        temp.right = new TreeNode(val);
                        System.out.println(temp.val);
                        break;
                    }
                }
            }
        }
    }

    /**
     * @apiNote 99. 打印字符串
     * @// TODO: 2021/1/25  */
    public void test99(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            String tar=in.next().trim();
            String s = in.next().trim();
            int[] arr = new int[26];
            for(char c:s.toCharArray()){
                arr[c-'a']++;
            }
            System.out.println(arr[tar.charAt(0)-'a']);
        }
    }

    /**
     * @apiNote 100. 二叉树的层数
     * @// TODO: 2021/1/25*/
    public void test100(){
        class TreeNode{
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int val){
                this.val = val;
            }
        }
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for (int k=1;k<=T;k++){
            int n = in.nextInt();
            int m = in.nextInt();
            TreeNode[] nodes = new TreeNode[m];
            for (int i=0;i<m;i++){
                nodes[i] = new TreeNode(i);
            }
            for (int i=0;i<n;i++){
                int a = in.nextInt()-1;
                int b = in.nextInt()-1;
                TreeNode temp = nodes[b];
                if(temp.left==null){
                    temp.left = nodes[a];
                }else {
                    temp.right = nodes[a];
                }
            }
            System.out.println("Q"+k+":");
            Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
            deque.add(nodes[0]);
            while (!deque.isEmpty()){
                int size = deque.size();
                for (int i=0;i<size;i++){
                    TreeNode temp = deque.pop();
                    System.out.print(temp.val+1);
                    if(i<size-1){
                        System.out.print(" ");
                    }
                    if(temp.left!=null){
                        deque.add(temp.left);
                    }
                    if(temp.right!=null){
                        deque.add(temp.right);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int a , b, c;
        a=b=c=1;
        System.out.println(a+" "+b+" "+c);
    }
}
