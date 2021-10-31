package Medium.DesignTest;


/*
* 146. LRU缓存机制
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。



进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？*/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/5/25  */

public class LRUCache extends LinkedHashMap<Integer,Integer>{


//    private Map<Integer,Integer> map;
//    private int capacity;
//    public LRUCache(int capacity) {
//        this.map=new LinkedHashMap<>();
//        this.capacity=capacity;
//    }
//
//    public int get(int key) {
//        if(map.containsKey(key)){
//            int value = map.get(key);
//            map.remove(key);
//            // 保证每次查询后，都在末尾
//            map.put(key, value);
//            return value;
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        if(map.containsKey(key)){
//            map.remove(key);
//        }else if(map.size()==capacity){
//             int firstKey = map.entrySet().iterator().next().getKey();
//             map.remove(firstKey);
//        }
//        map.put(key,value);
//
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//        return size()>capacity;
//    }


    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    /*
    * private Map<Integer,Integer> map;
    private int capacity;
    private Deque<Integer> deque;
    public LRUCache(int capacity) {
        this.map=new HashMap<>();
        this.capacity=capacity;
        this.deque=new ArrayDeque<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            deque.remove(key);
            deque.addFirst(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            deque.remove(key);
            deque.addFirst(key);
            map.put(key,value);
        }else if(map.size()==capacity){
            int end=deque.getLast();
            map.remove(end);
            deque.removeLast();
            map.put(key,value);
            deque.addFirst(key);
        }else{
            deque.addFirst(key);
            map.put(key,value);
        }
    }*/

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));     // 返回  3
        System.out.println(cache.get(4));      // 返回  4

    }
}
