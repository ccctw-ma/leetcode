package Match.AutumnCodingMatch;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/9/12  */



public class Singles {


    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(drinks);
        int mod=1000000007;
        long sum=0;
        for (int a : staple) {
            if (a >= x) continue;
            int b = Arrays.binarySearch(drinks, x - a);
            if (b >= 0) {
                int temp = drinks[b];
                while (b < drinks.length && temp == drinks[b]) b++;
                sum = (sum + b) % mod;
            } else {
                sum = (-b - 1 + sum) % mod;
            }
        }
        return (int)sum;
    }

    public int minimumOperations(String leaves) {
        char[] chars=leaves.toCharArray();
        int n=leaves.length();
        int left=0,right=n-1;
        int count=0;
        if(chars[left]=='y'){
            chars[left]='r';
            count++;
        }
        while (left<n&&chars[left]=='r') left++;
        if(left==n) return count+1;
        if(chars[right]=='y'){
            chars[right]='r';
            count++;
        }
        while (right>=0&&chars[right]=='r') right--;
        if(right==-1) return count+1;
        if(right==left) return count;


        List<Integer> list=new ArrayList<>();
        while (left<=right){
            int num=0;
            while (left<=right&&chars[left]=='y'){
                left++;
                num--;
            }
            list.add(num);
            if(left>right) break;
            int res=0;
            while (left<=right&&chars[left]=='r'){
                left++;
                res++;
            }
            list.add(res);
        }

        int length=list.size();
        int[] arr=new int[list.size()];
        int[] arr2=new int[list.size()];
        for (int i=0;i<list.size();i++){
            arr[i]=list.get(i);
            arr2[i]=Math.abs(list.get(i));
        }

        int[] sums1=new int[arr.length+1];
        int[] sums2=new int[arr2.length+1];
        for (int i=1;i<=length;i++){
            sums1[i]=sums1[i-1]+arr[i-1];
            sums2[i]=sums2[i-1]+arr2[i-1];
        }
        int min=Integer.MAX_VALUE;

        for (int i=length;i>=1;i--){

            for (int j=0;j<=length-i;j++){
                int a=costA(sums1,sums2,0,j);
                int b=costB(sums1,sums2,j,j+i);
                int c=costA(sums1,sums2,j+i,length);
                min=Math.min(min,a+b+c);
            }

        }

        return count;
    }

    private int costA(int[] sums1,int[] sums2,int l,int r){
        if(l==r) return 0;
        int a=sums2[r]-sums2[l];
        int b=sums1[r]-sums1[l];
        return (b-a)/2;
    }

    private int costB(int[] sums1,int[] sums2,int l,int r){
        int a=sums1[r]-sums1[l];
        int b=sums2[r]-sums2[l];
        return (a+b)/2;
    }



    public static void main(String[] args) {
        int[] arr=new int[]{2,5,5};
        System.out.println(new Singles().minimumOperations("rrryyyrrrrrryyyrryyyrr") );
    }
}
