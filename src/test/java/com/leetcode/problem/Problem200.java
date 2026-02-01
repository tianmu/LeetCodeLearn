package com.leetcode.problem;

public class Problem200 {

    public static void main(String[] args) {
        // [3,9,20,15,7], inorder = [9,3,15,20,7]
//        System.out.println(new Solution().maxPathSum(null));
    }

    static class Solution {
        public int numIslands(char[][] grid) {
            int num = 0;
            for(int i=0;i<grid.length;i++) {
                for(int j=0;j<grid[i].length;j++) {
                    if(grid[i][j] == '1') {
                        num++;
                        dfs(grid, i, j);
                    }
                }
            }
            return num;
        }

        private void dfs(char[][] grid, int n, int m) {
            if(n<0||n>=grid.length) {
                return;
            }
            if(m<0||m>=grid[n].length) {
                return;
            }
            if(grid[n][m] != '1') {
                return;
            }

            grid[n][m] = 2;
            dfs(grid, n-1, m);
            dfs(grid, n+1, m);
            dfs(grid, n, m-1);
            dfs(grid, n, m+1);
        }
    }
}
