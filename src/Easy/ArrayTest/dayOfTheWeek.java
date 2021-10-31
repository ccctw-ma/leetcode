package Easy.ArrayTest;


/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 *
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 *
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 *
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 *
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 *  
 *
 * 提示：
 *
 * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 1185. 一周中的第几天 */

public class dayOfTheWeek {


    public String dayOfTheWeek(int day, int month, int year) {
        String[] days=new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        int[] md=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int sum=0;
        for (int i=1971;i<year;i++){
            if(i%4==0){
                sum+=366;
            }else {
                sum+=365;
            }
        }
        for (int i=0;i<month-1;i++){
            sum+=md[i];
        }
        if(month>2&&year%4==0) sum+=1;
        sum+=day-1;
        return days[(sum+5)%7];
    }


    //这用的是公式
    public String dayOfTheWeek2(int day, int month, int year) {
        String[] week = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if(month<=2){
            year--;
            month += 12;
        }
        int y = year%100;
        int c = (year-y)/100;
        int w = ((y+(y/4)+(c/4)-(2*c)+((26*(month+1))/10)+day-1)%7+7)%7;
        return week[w];
    }
    
    public static void main(String[] args) {
        System.out.println(new dayOfTheWeek().dayOfTheWeek(2,2,2020));
    }
}
