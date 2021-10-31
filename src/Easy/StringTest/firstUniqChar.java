package Easy.StringTest;


/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *  
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 387. 字符串中的第一个唯一字符 */

public class firstUniqChar {


    public int firstUniqChar(String s) {
        if(s.length()==0){
            return -1;
        }else if(s.length()==1){
            return 0;
        }
        int[] caps=new int[26];
        for (char c:s.toCharArray()){
            caps[c-'a']++;
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<caps.length;i++){
            if(caps[i]==1){
                int temp=s.indexOf(i+'a');
                if(temp<min){
                    min=temp;
                }
            }
        }

        return min==Integer.MAX_VALUE?-1:min;
    }
    
    public static void main(String[] args) {

    }
}
