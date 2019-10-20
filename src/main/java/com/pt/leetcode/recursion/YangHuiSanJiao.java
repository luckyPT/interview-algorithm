package com.pt.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * leetCode 118 & 119
 *
 * @author panTeng
 */
public class YangHuiSanJiao {
    public List<List<Integer>> triangle = new ArrayList<List<Integer>>();

    /**
     * leetCode 118
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return triangle;
        }
        if (numRows == 1) {
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(1);
            triangle.add(tmp);
            return triangle;
        }
        generate(numRows - 1);
        List<Integer> lastList = triangle.get(triangle.size() - 1);
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        for (int i = 1; i < numRows - 1; i++) {
            tmp.add(lastList.get(i - 1) + lastList.get(i));
        }
        tmp.add(1);
        triangle.add(tmp);
        return triangle;
    }

    /**
     * leetCode 119
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex <= 0) {
            return null;
        }
        if (rowIndex == 1) {
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(1);
            return tmp;
        }
        List<Integer> lastRow = getRow(rowIndex - 1);
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        for (int i = 1; i < rowIndex - 1; i++) {
            tmp.add(lastRow.get(i - 1) + lastRow.get(i));
        }
        tmp.add(1);
        return tmp;
    }

    public static void main(String[] args) {
        YangHuiSanJiao yangHuiSanJiao = new YangHuiSanJiao();
        //List<List<Integer>> triangle = yangHuiSanJiao.generate(5);
        //triangle.forEach(System.out::println);
        List<Integer> row = yangHuiSanJiao.getRow(4);
        System.out.println(row);
    }
}
