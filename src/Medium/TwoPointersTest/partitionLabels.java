package Medium.TwoPointersTest;

import java.util.ArrayList;
import java.util.List;

public class partitionLabels {


    public List<Integer> partitionLabels(String s) {
        int[] arr=new int[26];
        //首先，后序遍历找到每一字符在该字符串中最后一次出现的位置
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            arr[c-'a']=i;
        }
        List<Integer> res=new ArrayList<>();

        int index=0;
        int pre=0;

        //从第一个字符开始，
        while(index<s.length()){
            int max=arr[s.charAt(index)-'a'];
            while(index<=max){
                max=Math.max(max,arr[s.charAt(index++)-'a']);
            }
            res.add(index-pre);
            pre=index;

        }
        return res;
    }


    public static void main(String[] args) {

    }
}
