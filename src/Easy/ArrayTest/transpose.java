package Easy.ArrayTest;

public class transpose {


    public int[][] transpose(int[][] A) {
        int a=A.length,b=A[0].length;
        int[][] arr=new int[b][a];
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                arr[j][i]=A[i][j];
            }
        }
        return arr;
    }


    public static void main(String[] args) {

    }
}
