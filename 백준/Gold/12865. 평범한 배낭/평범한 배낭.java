
import java.util.*;
import java.io.*;
public class Main {

    /*
        0-1 knapsack 문제

        즉 해당 물품을 담거나, 담지 않음으로서 최대 가치의 물건을 담는 방법.

        즉 앞에서부터 물건을 담거나, 담지 않는 경우 무게별 최대 가치를 갱신하면서 가면 됨.

        dp[n][k] -> n개의 물건까지 반영한 경우 k무게에서의 최대가치

        dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-weight[n]]);

    */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // 입력을 받는다.
        int[] weight = new int[N + 1];
        int[] profit = new int[N + 1];
        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken()); // 무게
            profit[i] = Integer.parseInt(st.nextToken()); // 가치
        }

        // dp 배열을 만든다.
        int[][] dp = new int[N+1][W+1];

        // 앞으로 진행하면서 갱신
        for(int i = 1 ; i <= N ; i++){

            for(int w = 1 ; w <= W ; w++){

                if(weight[i] <= w){
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + profit[i]);
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println(dp[N][W]);
    }
}
