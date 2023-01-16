package sort;

public class test01 {

    public static void main(String[] args) {
        System.out.println(solution(1,5,4));
    }


    public static int solution(int n, int a, int s){
        int res = 1;
        for(int i=1;i<=s;i++){
            res += (a-2)*(i+1) - (a-3);
        }
        return res;
    }



}
