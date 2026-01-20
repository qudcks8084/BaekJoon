import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(dp[i], -1);
        }

        for(int c = 0 ; c < N ; c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                map[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                if(dp[c][r] > 0){
                    answer = Math.max(answer, dp[c][r]);
                }else {
                    answer = Math.max(answer, dfs(c, r));
                }
            }
        }

        System.out.println(answer);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static int dfs(int c, int r){

        // 더 큰 것만 갈 수 있다.
        int cur = map[c][r];

        // 4 방향을 보고 dp가 있으면 그걸로 리턴하고
        int max = 1;

        for(int d = 0 ; d < 4 ; d++){
            int nc = c + dc[d];
            int nr = r + dr[d];

            // 범위를 벗어난다면 넘어감
            if(nc < 0 || nc >= N || nr < 0 || nr >= N) continue;

            // 만약 대나무가 지금보다 적으면 안감
            if(cur >= map[nc][nr]) continue;

            // 만약 계산된 내용이 있다면 그것을 사용.
            if(dp[nc][nr] != -1){
                max = Math.max(max, dp[nc][nr] + 1);
            }

            // 없다면 계산한다.
            else{
                max = Math.max(max, dfs(nc, nr) + 1);
            }
        }

        return dp[c][r] = max;
    }
}
