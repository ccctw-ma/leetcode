package Match.match192;

import java.util.Arrays;

public class BrowserHistory {

    private String[] strings;
    private int index;
    private int count;
    public BrowserHistory(String homepage) {
        strings=new String[5002];
        Arrays.fill(strings,"");
        index=0;
        strings[index]=homepage;
        count=0;
    }

    public void visit(String url) {
        strings[++index]=url;
        int i=index+1;
        count=index;
        while(strings[i].length()!=0){
            strings[i]="";
            i++;
        }
    }

    public String back(int steps) {
        if(index==0) return null;
        if(steps>index){
            index=0;
            return strings[0];
        }

        index-=steps;
        return strings[index];
    }

    public String forward(int steps) {
        if(index==count) return null;
        if(index+steps>count){
            index=count;
            return strings[count];
        }
        index+=steps;
        return strings[index];
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        browserHistory.back(2);                   // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        browserHistory.back(7);
    }
}
