import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int H, W;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        dp = new int[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int c = 0; c < H; c++) {
            st = new StringTokenizer(br.readLine());
            for(int r = 0; r < W; r++) {
                map[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 0);

        System.out.println(result);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};

    public static int dfs(int c, int r) {
        // 오른쪽 아래에 도착하는 경우 1을 리턴
        if (c == H-1 && r == W-1) {
            return 1;
        }

        // 이미 계산된 값이라면 그 값을 반환
        if (dp[c][r] != -1) {
            return dp[c][r];
        }

        // 새로 계산해야됨으로 0으로 초기화
        dp[c][r] = 0;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nc = c + dc[i];
            int nr = r + dr[i];
            if (nc < 0 || nc >= H || nr < 0 || nr >= W) continue;

            if (map[c][r] > map[nc][nr]) {
                dp[c][r] += dfs(nc, nr);
            }
        }

        return dp[c][r];
    }
}