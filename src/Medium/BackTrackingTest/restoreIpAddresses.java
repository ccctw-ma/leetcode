package Medium.BackTrackingTest;

import java.util.ArrayList;
import java.util.List;

/*
* 93. 复原IP地址
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。



示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]*/

/**
 * @author 马世臣
 * @// TODO: 2020/8/9  */


public class restoreIpAddresses {

    private List<String> res;
    private int min;
    public List<String> restoreIpAddresses(String s) {
        res=new ArrayList<>();
        min=s.length()/4;
        trace(s,new ArrayList<>(),0,0);
        return res;
    }

    private void trace(String s,List<String> list,int index,int n){
        if(n==4&&index==s.length()){
            StringBuilder builder=new StringBuilder();
            for (int i=0;i<4;i++){
                builder.append(list.get(i));
                if(i<3) builder.append('.');
            }
            res.add(builder.toString());
        }
        if(n==3&&s.length()-index>3){
            return;
        }
        for (int i=min;i<=3;i++){
            if(index+i<=s.length()){
                String temp=s.substring(index,index+i);
                if(Integer.parseInt(temp)<=255){
                    list.add(temp);
                    trace(s,list,index+i,n+1);
                    list.remove(list.size()-1);
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new restoreIpAddresses().restoreIpAddresses("25525511135"));
    }
}
