package Match.match204;

public class match204_D {


    public int numWays(String s) {
        int sum=0;
        for (char c:s.toCharArray()){
            if(c=='1') sum++;
        }
        if(sum%3!=0) return 0;
        int n=sum/3;
        int mod=1000_000_007;
        if(sum==0){
            int t=s.length()-2;
            int res=0;
            for (int i=t;i>=1;i--){
                res=(res+i)%mod;
            }
            return res;
        }
        long n1=0,n2=0;
        int count=0,index=0;
        while (index<s.length()&&count<n){
            if(s.charAt(index)=='1'){
                count++;
            }
            index++;
        }
        count=0;
        while (index<s.length()&&s.charAt(index)!='1'){
            n1++;
            index++;
        }
        while (index<s.length()&&count<n){
            if(s.charAt(index)=='1'){
                count++;
            }
            index++;
        }
        while (index<s.length()&&s.charAt(index)=='0'){
            n2++;
            index++;
        }

        long res=(n1+1)*(n2+1)%mod;
        return (int) res;
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        int n=arr.length;
        int l=0,r=n-1;
        while (l+1<n&&arr[l]<=arr[l+1]) l++;
        if(l==n-1) return 0;
        while (r-1>=0&&arr[r-1]<=arr[r]) r--;
        int temp_left=l;
        while (temp_left>=0&&arr[temp_left]>arr[r]) temp_left--;
        int min=r-temp_left-1;
        int temp_right=r;
        while (temp_right<n&&arr[temp_right]<arr[l]) temp_right++;
        min=Math.min(min,temp_right-l-1);
        return min;
    }


    public static void main(String[] args) {

    }
}
