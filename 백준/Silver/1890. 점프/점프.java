import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];

        for (int c = 0; c < N; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int r = 0; r < N; r++) {
                map[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
                if (dp[c][r] == 0 || map[c][r] == 0) continue;

                int gap = map[c][r];

                if (c + gap < N) dp[c + gap][r] += dp[c][r];
                if (r + gap < N) dp[c][r + gap] += dp[c][r];
            }
        }

        System.out.println(dp[N-1][N-1]);
    }
}