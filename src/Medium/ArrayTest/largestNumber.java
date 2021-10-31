package Medium.ArrayTest;

import java.util.Arrays;

public class largestNumber {


    public String largestNumber(int[] nums) {
        String[] strings=new String[nums.length];

        //为了便于比较，将数字全部转换为字符串
        for (int i=0;i<nums.length;i++){
            strings[i]=String.valueOf(nums[i]);
        }

        //对字符串数组进行排序
        Arrays.sort(strings, (a, b) -> {
            int index=0;
            while (index<a.length()&&index<b.length()){
                char aa=a.charAt(index);
                char bb=b.charAt(index);
                index++;
                if(aa==bb) continue;
                return aa>bb?-1:1;//数字越大的越靠前
            }

            //特殊情况，两个字符串有公共前缀
            if(a.length()==b.length()) return 0;

            //这里将两个按a-b 和b-a的方式进行组合，在进行比较
            //例121和12 可以拼接成12112 和 12121
            //很明显12121 应该放在最前面
            String s1=a+b;
            String s2=b+a;
            //这里的index跳过了公共前缀，因为之前比较过了
            while (index<a.length()+b.length()){
                char aa=s1.charAt(index);
                char bb=s2.charAt(index);
                index++;
                if(aa==bb) continue;
                return aa>bb?-1:1;
            }
            return 0;
        });
        StringBuilder builder=new StringBuilder();
        for (String s:strings){
            builder.append(s);
        }
        //排除特殊情况，这个很坑
        //比如0 0 0 应该返回0
        if(builder.charAt(0)=='0') return "0";
        return builder.toString();
    }

    public String largestNumber2(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .map(i -> Integer.toString(i))
                .sorted((s1, s2)->{
                    String sum1 = s1 + s2;
                    String sum2 = s2 + s1;

                    for(int i = 0; i < sum1.length(); i++){
                        if(sum1.charAt(i) != sum2.charAt(i)){
                            return sum2.charAt(i) - sum1.charAt(i);
                        }
                    }
                    return 0;
                })
                .reduce(String::concat)
                .filter(s->!s.startsWith("0"))
                .orElse("0");
    }


    public static void main(String[] args) {
        System.out.println(new largestNumber().largestNumber(new int[]{121,12}));
    }
}
