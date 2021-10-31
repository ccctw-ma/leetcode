package Easy.StringTest;



/**
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 383. 赎金信 */
public class canConstruct {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] barrel=new int[26];
        for (char ch:magazine.toCharArray()){
            barrel[ch-'a']++;
        }
        for (char ch:ransomNote.toCharArray()){
            if(barrel[ch-'a']>0){
                barrel[ch-'a']--;
            }else {
                return false;
            }
        }
        return true;
    }


    //caps里记录的是ransom里的这个字母在magazine应该出现的位置，如果不存在就返回-1即false
    public boolean canConstruct2(String ransom, String magazine) {
        if (magazine.length() < ransom.length()) return false;//这一步干掉好多用例大大节省时间
        int caps[] = new int[26];
        for (char c : ransom.toCharArray()) {
            int index = magazine.indexOf(c, caps[c - 'a']);
            if (index == -1)
                return false;
            caps[c - 97] = index + 1;
        }
        return true;
    }

    
    public static void main(String[] args) {


    }
}
