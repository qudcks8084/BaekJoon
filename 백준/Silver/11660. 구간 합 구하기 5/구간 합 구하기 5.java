import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][N];

        for(int c = 0 ; c < N ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                int tmp = Integer.parseInt(st.nextToken());
                if(r == 0){
                    if(c == 0) dp[c][r] = tmp;
                    else dp[c][r] = dp[c-1][r] + tmp;
                }else{
                    if(c == 0) dp[c][r] = dp[c][r-1] + tmp;
                    else dp[c][r] = dp[c][r-1] + dp[c-1][r] - dp[c-1][r-1] + tmp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            if(x1 > 0 && y1 > 0) sb.append(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]);
            else if(x1 > 0) sb.append(dp[x2][y2] - dp[x1-1][y2]);
            else if(y1 > 0) sb.append(dp[x2][y2] - dp[x2][y1-1]);
            else sb.append(dp[x2][y2]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
