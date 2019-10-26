package com.pt.leetcode.list;

/**
 * leetCode 146
 * 此种解法时间复杂度很高，在第18个测试case中会超出时间限制
 *
 * @author panTeng
 */
class LRUCache {
    int maxCount;
    int count;
    SNode head;
    SNode tail;

    public LRUCache(int capacity) {
        this.maxCount = capacity;
    }

    public int get(int key) {
        SNode pre = null;
        SNode tmp = head;
        while (tmp != null) {
            if (tmp.key == key) {//特殊考虑tmp时头结点和尾结点时
                tail.next = tmp;
                tail = tmp;
                if (pre == null) {//头节点
                    head = head.next;//考虑head == tail，仅有一个结点
                } else {
                    pre.next = pre.next.next;
                }
                tail.next = null;
                return tmp.value;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        return -1;
    }

    public void put(int key, int value) {
        int exitValue = get(key);
        if (exitValue == value) {
            return;
        }
        if (exitValue != -1) {//处理插入已存在的key，但value不同，需要更新value的情况。
            SNode tmp = head;
            while (tmp != null) {
                if (tmp.key == key) {
                    tmp.value = value;
                }
                tmp = tmp.next;
            }
            return;
        }

        SNode newNode = new SNode(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            count = 1;
            return;
        }

        if (count < maxCount) {
            tail.next = newNode;
            tail = newNode;
            count++;
        } else {
            tail.next = newNode;
            tail = newNode;
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

        LRUCache cache1 = new LRUCache(1);
        cache1.put(1, 1);
        System.out.println(cache1.get(1)); //返回  1
        cache1.put(2, 2);
        System.out.println(cache1.get(1)); //返回  -1
        System.out.println(cache1.get(2)); //返回  2

    }

    static class SNode {
        int key;
        int value;
        SNode next;

        public SNode() {
        }

        public SNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}