/* 
    담고 안담고가 가능한가?
    
    담는다면 연속으로는 담지 못한다.
    dp[N] = Math.max(dp[N-1], dp[N-2][1] + money[N]);
    
    그러면 최대가 되려면 [넣고, 안넣고], [안넣고, 넣고] 부터 시작하면 된다.
*/

class Solution {
    public int solution(int[] money) {
        
        int N = money.length;
        int[] dp = new int[N];
        int[] dp_2 = new int[N];
        
        // 첫번째껄 넣는 경우
        dp[0] = money[0];
        dp[1] = money[0];
    
        for(int i = 2 ; i < N ; i++)
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);  
        
        // 두번째껄 넣는 경우
        dp_2[0] = 0;
        dp_2[1] = money[1];
        for(int i = 2 ; i < N ; i++)
            dp_2[i] = Math.max(dp_2[i-1], dp_2[i-2] + money[i]);
        
        return Math.max(dp[N-2], dp_2[N-1]);
    }
}