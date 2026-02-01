package com.leetcode.problem;

public class Problem621 {

    public static void main(String[] args) {
        // 8
        System.out.println(new Solution().leastInterval(new char[]{'A','A','A','B','B','B'},2));
        // 16
        System.out.println(new Solution().leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'},2));
        // 12
        System.out.println(new Solution().leastInterval(new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'},2));
    }

    static class Solution {
        /**
         * 贪心算法
         * 每次选满足空闲时间，且剩余任务最多的
         */
        public int leastInterval(char[] tasks, int n) {
            // 初始化，只有大写字母
            int[] taskTimes = new int[26];
            int[] taskWaiteTimes = new int[26];
            for(char task : tasks) {
                taskTimes[task-'A']++;
            }
            for(int i=0;i< taskWaiteTimes.length; i++) {
                taskWaiteTimes[i] = 0;
            }

            int times = 0;
            while(isNotEmptyTask(taskTimes)) {

                int selectWaite = -1;
                int maxTask = -1;
                for(int i=0;i< taskTimes.length; i++) {
                    if(taskTimes[i] <= 0) {
                        continue;
                    }

                    if(taskWaiteTimes[i] <= 0 && taskTimes[i] > maxTask) {
                        maxTask = taskTimes[i];
                        selectWaite = i;
                    }
                }

                if(selectWaite >= 0) {
                    taskTimes[selectWaite] = taskTimes[selectWaite] -1;
                    taskWaiteTimes[selectWaite] = n + 1;
                }

                for(int i=0;i< taskWaiteTimes.length; i++) {
                    taskWaiteTimes[i] = taskWaiteTimes[i]-1;
                }
                times++;
            }
            return times;
        }

        private boolean isNotEmptyTask(int[] taskTimes) {
            for(int taskTime : taskTimes) {
                if(taskTime>0) {
                    return true;
                }
            }
            return false;
        }
    }
}
