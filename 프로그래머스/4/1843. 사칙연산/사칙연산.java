/* 
    핵심은 -뒤에 오는 것들에 대해서 가장 작게 만들어야하는데... 어떻게 할 수 있을까?
    
    그렇다면 누적합을 해....? - 만나면 뒤를 가장 작게 만들어...

*/

class Solution {
    public int solution(String[] arr) {
        
        int N = arr.length;
        
        int num = (N+1) / 2;
        int[] nums = new int[num];
        char[] opers = new char[num-1];
        
        for(int i = 0 ; i < N ; i++){
            if(i % 2 == 0) nums[i/2] = Integer.parseInt(arr[i]);
            else opers[i/2] = arr[i].charAt(0);
        }
        
        int n = nums.length;
        
        // dp[i][j][0] : i에서 j까지의 최대값.
        // dp[i][j][1] : i에서 j까지의 최소값.
        int[][][] dp = new int[n][n][2];
        
        // 길이가 1개는 자기 자신이 최대최소
        for(int i = 0 ; i < n ; i++){
            dp[i][i][0] = nums[i];
            dp[i][i][1] = nums[i];
        }
        
        // 길이를 늘려가며 최대 최소 갱신
        for(int l = 2 ; l <= n ; l++){
            for(int i = 0 ; i <= n - l ; i++){
                int j = i + l - 1;
                
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                
                for(int k = i ; k < j ; k++){
                    int lf_max = dp[i][k][0];
                    int lf_min = dp[i][k][1];
                    int rf_max = dp[k+1][j][0];
                    int rf_min = dp[k+1][j][1];
                    
                    // 더하기면 끼리끼리 더하면됨.
                    if(opers[k] == '+'){
                        max = Math.max(max, lf_max + rf_max);
                        min = Math.min(min, lf_min + rf_min);
                    }
                    // 빼기면 최소는 최소 - 최대 / 최대는 최대 - 최소
                    else{
                        max = Math.max(max, lf_max - rf_min);
                        min = Math.min(min, lf_min - rf_max);
                    }
                }
                
                dp[i][j][0] = max;
                dp[i][j][1] = min;
                
            }
        }
        
        return dp[0][n-1][0];
    }
}