package com.pt.leetcode.list;

import com.pt.list.SNode;

/**
 * 基于单链表实现LRU缓存
 *
 * @author panteng
 */
public class LruCatcheBySNode<T> {
    private SNode<T> head;
    private SNode<T> tail;
    private int maxCount;
    private int count;

    public LruCatcheBySNode(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * 最新元素插入到队尾
     *
     * @param data
     * @return
     */
    public boolean insert(T data) {
        if (get(data) != null) {//需要避免重复元素插入
            return true;
        }
        SNode<T> newNode = new SNode<T>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            count = 1;
            return true;
        }
        if (count < maxCount) {
            tail.next = newNode;
            tail = newNode;
            count++;
        } else {
            tail.next = newNode;
            tail = newNode;
            head = head.next;//这里是删除头结点，最好是新结点插入完成之后，再删除
        }
        return true;
    }

    public SNode<T> get(T data) {
        SNode<T> tmp = head;
        SNode<T> pre = null;
        while (tmp != null) {
            if (tmp.data.equals(data)) {
                tail.next = tmp;
                tail = tmp;
                if (pre == null) {//头结点就匹配到了
                    // 这句放到tail.next=tmp之前，就可能出现head=null的情况
                    head = head.next;
                } else {
                    //这个语句放到tail.next = tmp之前，如果恰好匹配到最后一个元素，就会导致指针丢失
                    pre.next = pre.next.next;
                }
                tail.next = null;//没有这句话可能会出现循环指向
                return tmp;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LruCatcheBySNode<Integer> cache = new LruCatcheBySNode<Integer>(2);

        cache.insert(1);
        cache.insert(2);
        System.out.println(cache.get(1));       // 返回  1
        cache.insert(3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.insert(4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
