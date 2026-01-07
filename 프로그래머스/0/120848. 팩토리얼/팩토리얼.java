import java.util.*;

class Solution {
    public int solution(int n) {
        
        int[] dp = new int[11];
        dp[1] = 1;
        for(int i = 2 ; i < 11 ; i++){
            dp[i] = dp[i-1] * i;
        }
        
        System.out.println(Arrays.toString(dp));
        
        int idx = Arrays.binarySearch(dp, n);
        System.out.println(idx);
        
        if(idx >= 0) return idx;
        else return -idx -2;

    }
}