package com.pt;

public class Matrix {
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
        printByClockWise(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
    }
}