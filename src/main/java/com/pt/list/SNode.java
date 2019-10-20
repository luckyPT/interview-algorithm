package com.pt.list;

/**
 * 单链表结点
 *
 * @param <T>
 * @author panteng
 */
public class SNode<T> {
    public SNode(T data) {
        this.data = data;
    }

    public T data;
    public SNode<T> next;

    @Override
    public String toString() {
        return String.format("SNode{data = %s}", data.toString());
    }
}
