import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 집의 상태를 나타내는 맵. true = 벽/장애물
        boolean[][] map = new boolean[N][N];

        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                // 1이면 벽, 0이면 빈 공간
                map[i][j] = st.nextToken().equals("1");
            }
        }

        // dp[i][j][k]에서 k는 파이프 방향을 나타냄
        // k=0: 가로, k=1: 세로, k=2: 대각선
        long[][][] dp = new long[N][N][3];
        
        // 초기 상태 - 파이프는 (0,1) 위치에 가로로 놓여있음
        dp[0][1][0] = 1;

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                // 현재 위치가 벽이면 건너뜀
                if(map[i][j]) continue;

                // 가로 파이프 (왼쪽에서 오는 경우)
                if(j > 0 && !map[i][j-1]) {
                    // 왼쪽에서 가로 파이프나 대각선 파이프로 올 수 있음
                    dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
                }

                // 세로 파이프 (위에서 오는 경우)
                if(i > 0 && !map[i-1][j]) {
                    // 위에서 세로 파이프나 대각선 파이프로 올 수 있음
                    dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
                }

                // 대각선 파이프 (왼쪽 위 대각선에서 오는 경우)
                // 현재 위치, 위쪽, 왼쪽 세 칸이 모두 벽이 아니어야 함
                if(i > 0 && j > 0 && !map[i-1][j] && !map[i][j-1] && !map[i-1][j-1]) {
                    // 왼쪽 위 대각선에서 어떤 방향의 파이프든 올 수 있음
                    dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }

        // 최종 답은 오른쪽 아래 모서리에 도달하는 모든 방법의 합
        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
  
    }
	
	public static void print(int N, int[][][] dp) {
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < N ; c++) {
			for(int d = 0 ; d < 3 ; d++) {
				for(int r = 0 ; r < N ; r++) {
					sb.append(dp[c][r][d]).append(" ");
				}
				sb.append("\t");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
