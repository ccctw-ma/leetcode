package Medium.DesignTest;

import java.util.*;


/**
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 *
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 *
 * Twitter twitter = new Twitter();
 *
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 *
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 *
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 *
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/13  */

public class Twitter {

    /** Initialize your data structure here. */
    class Message{
        private int userId;
        private int tweetId;
        public Message(int userId,int tweetId){
            this.userId=userId;
            this.tweetId=tweetId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getTweetId() {
            return tweetId;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "userId=" + userId +
                    ", tweetId=" + tweetId +
                    '}';
        }

        public void setTweetId(int tweetId) {
            this.tweetId = tweetId;
        }
    }

    private List<Message> messages;
    private Map<Integer, Set<Integer>> map;//关注
    public Twitter() {
        this.messages=new ArrayList<>();
        this.map=new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!map.containsKey(userId)){
            map.put(userId,new HashSet<>());
        }
        messages.add(new Message(userId,tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list=new ArrayList<>();
        if(!map.containsKey(userId)) return list;
        Set<Integer> set=new HashSet<>(map.get(userId));
        set.add(userId);
        // System.out.print(messages);
        for (int i=messages.size()-1;i>=0;i--){
            if(set.contains(messages.get(i).getUserId())&&list.size()<10){
                list.add(messages.get(i).getTweetId());
            }
        }
        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!map.containsKey(followerId)){
            map.put(followerId,new HashSet<>());
        }
        Set<Integer> follows=map.get(followerId);
        follows.add(followeeId);
        map.put(followerId,follows);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(map.containsKey(followerId)){
            Set<Integer> follows=map.get(followerId);
            if(follows.contains(followeeId)){
                follows.remove(followeeId);
                map.put(followerId,follows);
            }
        }
    }




    /**还可以使用优先队列*/
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 1);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(1, 13);
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 94);
        twitter.postTweet(1, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(1, 22);
        twitter.postTweet(1, 11);
        System.out.println(twitter.getNewsFeed(1));
// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.


    }
}

/*
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
