import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 각 집별 색을 칠하는데 필요한 비용을 저장
        int[][] paint = new int[3][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                paint[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int INF = Integer.MAX_VALUE / 10; // 충분히 큰 수
        int answer = INF;

        // 첫 집의 색상을 고정하고 3번의 DP 실행
        for(int firstColor = 0; firstColor < 3; firstColor++) {
            // dp[color][house] = house번 집을 color로 칠했을 때의 최소 비용
            int[][] dp = new int[3][N];

            // 초기화: 첫 집은 firstColor로 고정, 나머지 색상은 INF로 설정
            for(int c = 0; c < 3; c++) {
                if(c == firstColor) {
                    dp[c][0] = paint[c][0];
                } else {
                    dp[c][0] = INF;
                }
            }

            // DP 진행
            for(int i = 1; i < N; i++){
                for(int c = 0; c < 3; c++){
                    dp[c][i] = Math.min(dp[(c + 1) % 3][i - 1], dp[(c + 2) % 3][i - 1]) + paint[c][i];
                }
            }

            // 마지막 집은 첫 집과 색이 달라야 함
            for(int c = 0; c < 3; c++) {
                if(c != firstColor) {
                    answer = Math.min(answer, dp[c][N - 1]);
                }
            }
        }

        System.out.println(answer);
    }
}