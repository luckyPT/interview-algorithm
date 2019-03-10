package com.pt;

import java.util.Arrays;

public class Matrix {
    /**
     * 二维矩阵搜索
     * matrix从上到下递增，从左到右递增
     * 从matrix中寻找value，并返回下标，如果不存在，返回[-1,-1]
     *
     * @param matrix
     * @param value
     * @return
     */
    static int[] search(int[][] matrix, int value) {
        int row = 0;
        int cols = matrix[0].length - 1;
        while (row < matrix.length && cols >= 0) {
            if (matrix[row][cols] == value) {
                return new int[]{row, cols};
            }
            if (matrix[row][cols] > value) {
                cols--;
                continue; //如果缺失，下面便可能越界
            }
            if (matrix[row][cols] < value) {
                row++;
            }
        }
        return new int[]{-1, -1};
    }

    static void printByClockWise(int[][] array) {
        int circleCount = 0;
        int cols = -1;
        int row = 0;
        while (circleCount < array[0].length / 2 + 1 && circleCount < array.length / 2 + 1) {
            for (cols = cols + 1; cols < array[row].length - circleCount; cols++) {
                System.out.print(array[row][cols] + ",");
            }
            cols--;
            for (row = row + 1; row < array.length - circleCount; row++) {
                System.out.print(array[row][cols] + ",");
            }
            row--;
            for (cols = cols - 1; cols >= circleCount; cols--) {
                System.out.print(array[row][cols] + ",");
            }
            cols++;
            for (row = row - 1; row >= circleCount + 1; row--) {
                System.out.print(array[row][cols] + ",");
            }
            row++;
            circleCount++;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printByClockWise(matrix);
        System.out.println(Arrays.toString(search(matrix,12)));
    }
}