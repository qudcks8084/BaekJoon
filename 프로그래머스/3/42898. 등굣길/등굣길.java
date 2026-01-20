import java.util.*;

class Solution {
    
    /* 
        핵심 해결 방법
        - dp 배열을 만들어서 경우의 수를 점점 채워가면 된다.
        
        1. int[][] dp = new int[m][n];
        
        2. puddles는 -1로 표시한다.
        
        3. dp[0][0] = 1;
        
        4. dp[c][r] = dp[c-1][r] + dp[c][r-1];
        
        5. 정답 : dp[m-1][n-1];
    */
    
    public int solution(int m, int n, int[][] puddles) {
        
        // 모든 경우의 수를 저장할 2차원 dp 배열을 만들어
        long[][] dp = new long[n][m];
        
        // 그리고 웅덩이를 전부 -1로 표현해
        for(int[] puddle : puddles){
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
      
        // 집은 항상 1로 설정
        dp[0][0] = 1;
        
        
        for(int c = 0 ; c < n ; c++){
            for(int r = 0 ; r < m ; r++){
                
                // 웅덩이면 건더뜀.
                if(dp[c][r] == -1) continue;
                
                if(c-1 >= 0 && dp[c-1][r] != -1){
                    dp[c][r] = (dp[c][r] + dp[c-1][r]) % 1_000_000_007;
                }
                
                if(r-1 >= 0 && dp[c][r-1] != -1){
                    dp[c][r] = (dp[c][r] + dp[c][r-1]) % 1_000_000_007;
                }
                
            }
        }
        
        if(dp[n-1][m-1] == -1) return 0;
        return (int) dp[n-1][m-1] % 1_000_000_007;
    }
}