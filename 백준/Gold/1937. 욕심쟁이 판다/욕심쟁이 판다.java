import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0}; // 4방향 이동을 위한 배열 (상,하,좌,우)
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1); // dp 배열 초기화
        }

        int maxPath = 0;

        // 모든 시작점에서 DFS 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxPath = Math.max(maxPath, find(i, j));
            }
        }

        // 최종 결과 출력
        System.out.println(maxPath);
    }

    // 현재 위치 (x, y)에서의 최대 이동 거리를 반환하는 함수
    public static int find(int x, int y) {
        // 이미 계산된 경우 저장된 값 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 시작 위치만으로 경로 길이는 1
        dp[x][y] = 1;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크 및 더 큰 값을 가진 칸만 이동 가능
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > map[x][y]) {
                // 이동 가능한 경우 최대 경로 갱신
                dp[x][y] = Math.max(dp[x][y], find(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }
}