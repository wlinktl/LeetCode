// LeetCode 322 Coins Change

import java.util.*;

public class LeetCode {

    public static void main(String[] args) {
      System.out.println("Hello, World!");
      
      Solution2 solution2 = new Solution2();
      int[] coins = new int[]{1,2,5};
      
      int res = solution2.coinChange(coins, 11);
      System.out.println("res=" + res);
      
  }
}

// LeetCode 322 Coins Change  https://leetcode.com/problems/coin-change/

//recursive
class Solution1 {
      int[] memo;
  
      int coinChange(int[] coins, int amount) {
          memo = new int[amount + 1];
          // 备忘录初始化为一个不会被取到的特殊值，代表还未被计算
          Arrays.fill(memo, -666);
  
          return dp(coins, amount);
      }
  
      int dp(int[] coins, int amount) {
          if (amount == 0) return 0;
          if (amount < 0) return -1;
          // 查备忘录，防止重复计算
          if (memo[amount] != -666)
              return memo[amount];
  
          int res = Integer.MAX_VALUE;
          for (int coin : coins) {
              // 计算子问题的结果
              int subProblem = dp(coins, amount - coin);
              // 子问题无解则跳过
              if (subProblem == -1) continue;
              // 在子问题中选择最优解，然后加一
              res = Math.min(res, subProblem + 1);
          }
          // 把计算结果存入备忘录
          memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
          return memo[amount];
      }
}

//iterative

class Solution2 {
     int[] memo;

     int coinChange(int[] coins, int amount) {
      int[] dp = new int[amount + 1];
      // 数组大小为 amount + 1，初始值也为 amount + 1
      Arrays.fill(dp, amount + 1);
  
      // base case
      dp[0] = 0;
      // 外层 for 循环在遍历所有状态的所有取值
      for (int i = 0; i < dp.length; i++) {
          // 内层 for 循环在求所有选择的最小值
          for (int coin : coins) {
              // 子问题无解，跳过
              if (i - coin < 0) {
                  continue;
              }
              dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
          }
      }
      return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

}

