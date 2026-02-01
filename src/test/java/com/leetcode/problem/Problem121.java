package com.leetcode.problem;

public class Problem121 {

    public static void main(String[] args) {
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            // 因为只能交易1次，因此比我小的就可以直接替换，比我大的就卖出试试
            int max = 0;
            int buyPrices = Integer.MAX_VALUE;
            for(int i=0;i<prices.length;i++) {
                if(buyPrices > prices[i]) {
                    buyPrices = prices[i];
                } else if(prices[i] - buyPrices > max) {
                    max = prices[i] - buyPrices;
                }
            }
            return max;
        }
    }
}
