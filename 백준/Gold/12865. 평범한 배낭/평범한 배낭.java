import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int[] weight = new int[N+1];
        int[] profit = new int[N+1];
        
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N+1][W+1];
        for(int i = 1 ; i <= N ; i++) {
            for(int w = 1 ; w <= W ; w++) {
                if(weight[i] <= w) {
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + profit[i]);
                }else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        
        System.out.println(dp[N][W]);
    }
}
