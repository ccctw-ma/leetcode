package Medium.String;


/*
* 468. 验证IP地址
编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。

如果是有效的 IPv4 地址，返回 "IPv4" ；
如果是有效的 IPv6 地址，返回 "IPv6" ；
如果不是上述类型的 IP 地址，返回 "Neither" 。
IPv4 地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；

同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

IPv6 地址由 8 组 16 进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。

然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。

同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。



示例 1：

输入：IP = "172.16.254.1"
输出："IPv4"
解释：有效的 IPv4 地址，返回 "IPv4"
示例 2：

输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
输出："IPv6"
解释：有效的 IPv6 地址，返回 "IPv6"
示例 3：

输入：IP = "256.256.256.256"
输出："Neither"
解释：既不是 IPv4 地址，又不是 IPv6 地址
示例 4：

输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
输出："Neither"
示例 5：

输入：IP = "1e1.4.5.6"
输出："Neither"


提示：

IP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
通过次数16,719提交次数70,768*/

import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * @author 马世臣
 * @// TODO: 2021/3/14  */


public class validIPAddress {

    public String validIPAddress(String IP) {
        if(isIPv4(IP)) return "IPv4";
        if(isIPv6(IP)) return "IPv6";
        return "Neither";
    }

    private boolean isIPv4(String IP){
        String[] strings = IP.trim().split("\\.");
        if(strings.length!=4||IP.charAt(IP.length()-1)=='.') return false;
        for (String s:strings){
            if(s.length()==0||s.length()>3) return false;
            char[] chars = s.toCharArray();
            if(chars[0]=='0'&&chars.length!=1) return false;
            int sum = 0;
            for (char c:chars){
                if(c>='0'&&c<='9'){
                    int t = c-'0';
                    sum = sum*10+t;
                }else {
                    return false;
                }
            }
            if(sum>255) return false;
        }
        return true;
    }

    private boolean isIPv6(String IP){
        String[] strings = IP.trim().split(":");
        if(strings.length!=8||IP.charAt(IP.length()-1)==':') return false;
        for (String s:strings){
            char[] chars = s.toCharArray();
            if(s.length()<1||s.length()>4) return false;
            for (char c:chars){
                if((c<'0')||(c>'9'&&c<'A')||(c>'F'&&c<'a')||(c>'f')){
                    return false;
                }
            }
        }
        return true;
    }

    public String validIPAddress2(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
        } catch(Exception ignored) {}
        return "Neither";
    }


    public static void main(String[] args) {
//        System.out.println(new validIPAddress().validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334"));

        char c = 'G';
        if((c<'0')||(c>'9'&&c<'A')||(c>'F'&&c<'a')||(c>'f')){
            System.out.println((int)('G'));
        }

    }


}
