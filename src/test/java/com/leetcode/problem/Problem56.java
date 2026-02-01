package com.leetcode.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem56 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(new Solution().merge(new int[][]{{1, 4}, {2, 3}})));
    }

    static class Solution {

        public int[][] merge(int[][] intervals) {
            // 先排序，再遍历，只要值在后面区间的都可以合并
            Comparator<int[]> objectComparator = Comparator.comparing(t -> t,
                    Comparator.comparingInt(interval -> interval[0]));

            Arrays.sort(intervals, objectComparator.thenComparing(t -> t,
                    Comparator.comparingInt(interval -> interval[1])));

            List<int[]> tempRt = new ArrayList<>();
            int[] temp = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (temp[1] >= intervals[i][0] && temp[1] <= intervals[i][1]) {
                    temp[1] = intervals[i][1];
                } else if (temp[1] >= intervals[i][1]) {
                    continue;
                } else {
                    int[] temp1 = new int[2];
                    temp1[0] = temp[0];
                    temp1[1] = temp[1];
                    tempRt.add(temp1);
                    temp[0] = intervals[i][0];
                    temp[1] = intervals[i][1];
                }
            }
            tempRt.add(temp);
            int[][] finalRt = new int[tempRt.size()][2];
            for (int i = 0; i < tempRt.size(); i++) {
                finalRt[i] = tempRt.get(i);
            }
            return finalRt;
        }
    }
}
