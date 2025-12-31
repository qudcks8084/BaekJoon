import java.util.*;
import java.io.*;

public class Main {

    static int N;

    static int[] heart;
    static int[] happiness;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        heart = new int[N + 1];
        happiness = new int[N + 1];

        // heart를 입력받음
        StringTokenizer st_heart = new StringTokenizer(br.readLine());

        // Happiness를 입력받음
        StringTokenizer st_happiness = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            heart[i] = Integer.parseInt(st_heart.nextToken());
            happiness[i] = Integer.parseInt(st_happiness.nextToken());
        }

        // dp[w] = 체력 w를 사용했을 때 얻을 수 있는 최대 행복
        // 체력은 100 미만이어야 하므로 0~99까지 사용 가능
        dp = new int[100];

        // 각 사람마다 선택/비선택 결정 (0-1 배낭 문제)
        for(int i = 1; i <= N; i++){

            // 역순으로 순회하여 각 사람을 중복 선택하지 않도록 함
            // 순방향으로 하면 같은 사람을 여러 번 선택할 수 있음
            for(int w = 99; w >= heart[i]; w--){

                // 현재 체력 w에서
                // 1) i번째 사람을 선택하지 않는 경우: dp[w]
                // 2) i번째 사람을 선택하는 경우: dp[w - heart[i]] + happiness[i]
                dp[w] = Math.max(dp[w], dp[w - heart[i]] + happiness[i]);
            }
        }

        // 체력이 100 미만인 모든 경우 중 최대 행복을 찾음
        int max_happiness = 0;
        for(int w = 0; w < 100; w++){
            max_happiness = Math.max(max_happiness, dp[w]);
        }

        System.out.println(max_happiness);
    }
}