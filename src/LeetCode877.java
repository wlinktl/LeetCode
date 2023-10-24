import java.util.*;

public class LeetCode877 {
    public static void main(String[] args) {
      Solution877 solution= new Solution877();
      solution.solution();
  }
}


class Solution877 {
  
  public static Pair[][] dp;

  void solution(){
    int[] piles = {2,8,3,5, 7};
    int diff = stoneGame(piles);
    System.out.println("diff=" + diff);
    if (diff>0) System.out.println(true);
    else System.out.println(false);
    
  }
  
  /* 返回游戏最后先手和后手的得分之差 */
 static int stoneGame(int[] piles) {
    int n = piles.length;
    static Pair[][] dp = new Pair[n][n];

      // 初始化 dp 数组

      for (int i = 0; i < n; i++) 
          for (int j = i; j < n; j++)
              dp[i][j] = new Pair(0, 0);
      // 填入 base case
      for (int i = 0; i < n; i++) {
          dp[i][i].fir = piles[i];
          dp[i][i].sec = 0;
      }
  
      // 倒着遍历数组
      for (int i = n - 2; i >= 0; i--) {
          for (int j = i + 1; j < n; j++) {
              // 先手选择最左边或最右边的分数
              int left = piles[i] + dp[i+1][j].sec;
              int right = piles[j] + dp[i][j-1].sec;
              // 套用状态转移方程
              // 先手肯定会选择更大的结果，后手的选择随之改变
              if (left > right) {
                  dp[i][j].fir = left;
                  dp[i][j].sec = dp[i+1][j].fir;
              } else {
                  dp[i][j].fir = right;
                  dp[i][j].sec = dp[i][j-1].fir;
              }
          }
      }
      Pair res = dp[0][n-1];
      return res.fir - res.sec;
  }


class Pair {
    int fir, sec;
    Pair(int fir, int sec) {
        this.fir = fir;
        this.sec = sec;
    }
}

  
  
  
}