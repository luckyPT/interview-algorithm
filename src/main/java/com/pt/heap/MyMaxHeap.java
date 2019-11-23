package com.pt.heap;

import java.util.Arrays;

public class MyMaxHeap {
    int[] data;
    int size;

    public MyMaxHeap(int[] data) {
        int i = data.length / 2;
        while (i >= 0) {
            adjustToBottom(data, i);
            i--;
        }
        this.data = data;
        this.size = data.length;
    }

    public void adjustToBottom(int[] array, int i) {
        while (i < array.length / 2) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int maxPos = i;
            if (left < array.length && array[left] > array[i]) maxPos = left;
            if (right < array.length && array[right] > array[maxPos]) maxPos = right;
            if (i == maxPos) {
                break;
            } else {
                swap(array, i, maxPos);
                i = maxPos;
            }
        }
    }

    public void insert(int v) {
        if (size >= data.length) {
            resize(data.length * 2);
        }
        data[size] = v;
        int i = size;
        while (i > 0 && data[i] > data[(i - 1) / 2]) {
            swap(data, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
        size++;
    }

    private void resize(int newSize) {
        int[] newData = new int[newSize];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void deleteTop() {
        data[0] = data[size - 1];
        size--;
        int i = 0;
        while (i * 2 + 1 < size) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            //下面的这种写法很不简洁
            /*boolean maxIsLeft = true;
            if (right < size && data[right] > data[left]) {
                maxIsLeft = false;
            }
            if (maxIsLeft) {
                if (data[left] > data[i]) {
                    swap(data, i, left);
                    i = left;
                } else {
                    break;
                }
            } else {
                if (data[right] > data[i]) {
                    swap(data, i, right);
                    i = right;
                } else {
                    break;
                }
            }*/
            //下面的写法比较简洁
            int maxPos = i;
            if (left < size && data[left] > data[i]) maxPos = left;
            if (right < size && data[right] > data[maxPos]) maxPos = right;
            if (maxPos == i) {
                break;
            } else {
                swap(data, maxPos, i);
                i = maxPos;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        MyMaxHeap maxHeap = new MyMaxHeap(new int[]{19, 39, 10, 37, 29, 34, 12, 23, 42});
        System.out.println(Arrays.toString(maxHeap.data));
    }
}
