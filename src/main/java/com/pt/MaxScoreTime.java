package com.pt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 输入多个以空格分隔的整数，每 4 个整数为一组（组数< 10^7），表示一个连接情况，
 * 这 4 个整数分别代表 id, start_time, end_time, score，其值均小于 10^6
 * total_score 为 t 时刻所有处于连接状态的服务分数之和，找最大的total_score
 * <p>
 * eg:input:1 6 10 4 2 3 8 3 3 7 9 1 4 5 6 2
 * output:8
 */
public class MaxScoreTime {
    public static void main(String[] args) {
        String line = "1 6 10 4 2 3 8 3 3 7 9 1 4 5 6 2";
        long start = System.currentTimeMillis();
        String[] data = line.split(" ");
        int count = data.length / 4;
        Map<Integer, Integer> time2score = new HashMap<>();
        int startTime;
        int endTime;
        int score;
        int maxScore = -1;
        int tmpScore;
        Set<Integer> timeSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            startTime = Integer.parseInt(data[i * 4 + 1]);
            endTime = Integer.parseInt(data[i * 4 + 2]);
            timeSet.add(startTime);
            timeSet.add(endTime - 1);
        }
        for (int i = 0; i < count; i++) {
            startTime = Integer.parseInt(data[i * 4 + 1]);
            endTime = Integer.parseInt(data[i * 4 + 2]);
            score = Integer.parseInt(data[i * 4 + 3]);
            for (int j = startTime; j < endTime; j++) {
                if (!timeSet.contains(j)) {
                    continue;
                }
                tmpScore = time2score.getOrDefault(j, 0) + score;
                time2score.put(j, tmpScore);
                if (tmpScore > maxScore) {
                    maxScore = tmpScore;
                }
            }

        }

        System.out.println(maxScore);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
