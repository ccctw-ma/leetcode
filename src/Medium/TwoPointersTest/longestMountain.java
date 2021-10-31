package Medium.TwoPointersTest;


/*
*
* 845. Longest Mountain in Array
Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain.

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?*/


/**
 * @author 马世臣
 * @// TODO: 2020/10/25  */



public class longestMountain {


    public int longestMountain(int[] A) {

        int n=A.length;
        if(n<=1) return 0;

        boolean available=false;
        int status=0;// 1 refers  to ascent, 0 refers to flat and -1 refers to descent
        int l=0;// l indicates start
        int max=0;//the longest mountains
        int pre=A[0];
        for (int i=1;i<n;i++){
            if(A[i]==pre){
                available=false;
                status=0;
                l=i;
            } else if(A[i]>pre){
                if(status==-1){
                    if(available){
                        max=Math.max(max,i-l);
                    }
                    l=i-1;
                }
                status=1;
                available=false;
            }else {
                if(status==1&&!available){
                    available=true;
                }
                if(available){
                    max=Math.max(max,i-l+1);
                }
                status=-1;
            }
            pre=A[i];
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new longestMountain().longestMountain(new int[]{0,0,1,0,0,1,1,1,1,1}));
    }
}
