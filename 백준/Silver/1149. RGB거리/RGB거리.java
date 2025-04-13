import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 각 집별 색을 칠하는데 필요한 비용을 저장
        int[][] paint = new int[3][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3 ; j++){
                paint[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열에 어떤 것을 저장해야하는가?
        // 각 시간과 색깔에 대하여 최소 값이 얼마인지 저장해야함
        int[][] dp = new int[3][N];

        // 첫 집의 색을 지정
        for(int i = 0 ; i < 3 ; i++){
            dp[i][0] = paint[i][0];
        }

        // 0초일때의 최대값 -> 각자의 (idx+1)%3. (idx+2)%3의 색의 값을 확인하면 댐
        for(int i = 1 ; i < N ; i++){
            // 각각의 색을 칠하는 경우의 최소값을 갱신
            for(int c = 0 ; c < 3 ; c++){
                // 이전 색에서 사용하지 않은 2개를 칠하는 최소값에 칠하는 값을 더해서 갱신
                dp[c][i] = Math.min(dp[(c + 1) % 3][i - 1], dp[(c + 2) % 3][i - 1]) + paint[c][i];
            }
        }

        int min = Integer.MAX_VALUE / 10;
        for(int i = 0 ; i < 3 ; i++){
            min = Math.min(min, dp[i][N - 1]);
        }
        System.out.println(min);
    }
}
