package com.leetcode.problem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem904 {

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().totalFruit(new int[]{0, 1, 2, 2}));
        // 4
        System.out.println(new Solution().totalFruit(new int[]{1,2,3,2,2}));
        // 17
        System.out.println(new Solution().totalFruit(new int[]{0, 1, 1, 0, 1, 1, 1, 0, 130, 130, 0, 0, 130, 130, 0, 0, 57, 57, 57, 57, 57, 0, 68, 142, 68, 160, 160, 68, 62, 68, 115, 68, 141, 68, 68, 141, 141, 68, 141, 68, 141, 141, 117, 269, 117, 2, 117, 117, 117, 2, 0, 0, 2, 0, 0, 172, 27, 32, 27, 27, 27, 248, 199, 154, 199, 199, 154, 154, 199, 114, 114, 284, 24, 240, 229, 277, 109, 247, 247, 247, 247, 160, 247, 247, 247, 160, 160, 247, 247, 247, 247, 160, 160, 160, 201, 81, 109, 81, 81, 109, 81, 36, 81, 81, 36, 139, 210, 139, 139, 75, 139, 131, 131, 131, 131, 131, 139, 131, 139, 131, 139, 139, 139, 139, 131, 282, 269, 126, 269, 126, 269, 126, 126, 94, 94, 94, 94, 168, 241, 241, 241, 59, 68, 256, 256, 256, 256, 256, 68, 256, 68, 256, 256, 286, 256, 256, 286, 286, 182, 182, 11, 11, 63, 246, 1, 1, 1, 1, 45, 45, 1, 45, 133, 133, 45, 109, 168, 109, 109, 168, 109, 168, 168, 168, 1, 57, 194, 57, 194, 272, 194, 194, 51, 51, 51, 194, 51, 123, 123, 51, 26, 15, 226, 167, 271, 167, 271, 271, 167, 167, 167, 271, 71, 263, 178, 263, 66, 263, 263, 66, 20, 20, 66, 66, 20, 20, 295, 295, 113, 295, 113, 113, 113, 113, 113, 113, 113, 113, 113, 207, 113, 113, 207, 221, 207, 221, 221, 207, 193, 193, 207, 207, 207, 207, 193, 207, 193, 207, 207, 193, 287, 193, 287, 287, 193, 296, 296, 193, 296, 266, 266, 296, 166, 296, 166, 251, 166, 166, 251, 251, 251, 166, 49, 49, 65, 65, 65, 34, 120, 34, 120, 120, 120, 120, 122, 120, 120, 122, 120, 120}));
        //
        System.out.println(new Solution().totalFruit(new int[]{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1}));
    }

    static class Solution {
        // 方法2 dp
        // 0, 1, 2, 2
        // 123456789343456789
        // bbccbbbccaabaaabbb
        // dp[i] 以i为结尾的序列长度。dp[i] = (dp[i-1] && fruits[i] in (fruits) + 1。那么dp[0]=1
        public int totalFruit(int[] fruits) {
            if (fruits.length <= 0) {
                return 0;
            }
            if (fruits.length == 1) {
                return 1;
            }
            if (fruits.length == 2) {
                return 2;
            }

            int[] dp = new int[fruits.length];
            dp[0] = 1;
            dp[1] = 2;
            int switchSum = fruits[0]==fruits[1]?2:1, switchLast = fruits[1];
            int xx=fruits[0],xxx=fruits[1];
            for (int i = 2; i < fruits.length; i++) {
                if (fruits[i] == xx || fruits[i] == xxx) {
                    dp[i] = dp[i-1] +1;
                    if (switchLast == fruits[i]) {
                        switchSum++;
                    } else {
                        switchLast = fruits[i];
                        switchSum = 1;
                    }
                } else {
                    if (xx==switchLast) {
                        xxx = fruits[i];
                    } else if (xxx==switchLast) {
                        xx = fruits[i];
                    }


                    dp[i] = 1 + switchSum;
                    switchLast = fruits[i];
                    switchSum=1;
                }
            }

            System.out.println(Arrays.toString(dp));

            int max = 0;
            for (int i : dp) {
                if (i>max) {
                    max = i;
                }
            }
            return max;
        }

        // 方法1 回溯法  超出时间限制
        // 1将水果树合并
        // 设dg[i] 是以ds[i]为种类的水果数量。ds[i]为i的种类
        // max(for i then dg[i]+dg[i-1]) 若 ds[i-2] == ds[i] 继续加
        public int totalFruit1(int[] fruits) {
            if (fruits.length <= 0) {
                return 0;
            }
            List<Integer> dg = new LinkedList<>();
            List<Integer> ds = new LinkedList<>();

            // 1将水果树合并
            int cat = fruits[0], sum = 0;
            for (int i = 0; i < fruits.length; i++) {
                if (fruits[i] == cat) {
                    sum++;
                } else {
                    dg.add(sum);
                    ds.add(cat);
                    cat = fruits[i];
                    sum = 1;
                }
            }
            dg.add(sum);
            ds.add(cat);


            int max = 0;
            for (int i = 0; i < dg.size(); i++) {
                Integer m = null;
                if (i + 1 < dg.size()) {
                    m = ds.get(i + 1);
                }
                // int val = taskFruits(dg, ds, i, ds.get(i), null);

                int toI = 0;
                int val = 0;
                for(int j = i; j<dg.size();j++) {
                    if (!ds.get(j).equals(ds.get(i)) && !ds.get(j).equals(m)) {
                        toI = j-i-2;
                        break;
                    }
                    toI++;
                    val += dg.get(j);
                }
                for(int j=i-1; j>=0;j--) {
                    if (!ds.get(j).equals(ds.get(i)) && !ds.get(j).equals(m)) {
                        break;
                    }
                    val += dg.get(j);
                }

                if (val > max) {
                    max = val;
                }
                i+=Math.max(0, toI-2);
            }

            return max;
        }

        int taskFruits(List<Integer> dg, List<Integer> ds, int nn, int n, Integer m) {
            int sum = 0;
            for(int i = nn; i<dg.size();i++) {
                if (ds.get(i) != n && !ds.get(i).equals(m)) {
                    break;
                }
                sum += dg.get(i);
            }
            for(int i=nn-1; i>=0;i--) {
                if (ds.get(i) != n && !ds.get(i).equals(m)) {
                    break;
                }
                sum += dg.get(i);
            }
            return sum;
        }
    }
}
