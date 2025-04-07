import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int H = K + N + 1;
        // N : 시간 - K : 높이ㅇㅈ
        long[][] dp = new long[H][N + 1];
        dp[K][0] = 1;

        // 이제 시작
        for(int r = 0 ; r < N ; r++){
            for(int c = 1 ; c < H ; c++ ){
                if(dp[c][r] == 0) continue;
                dp[c - 1][r + 1] += dp[c][r];
                if(c + 1 < H) dp[c + 1][r + 1] += dp[c][r];
            }
        }

        long cnt = 0;
        for(int c = 1 ; c < H ; c++ ){
            cnt += dp[c][N];
        }
        System.out.println(cnt);
    }
}
