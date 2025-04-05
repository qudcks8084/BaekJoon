import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int max;
    static int H, W;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                map[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        visited = new boolean[H][W];

        // DFS로 모든 위치에서 탐색 시작
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                visited[c][r] = true;
                dfs(1, map[c][r], c, r);
                visited[c][r] = false;

                // ㅜ, ㅓ, ㅏ, ㅗ 탐색
                nkjh(c, r);
            }
        }

        System.out.println(max);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void dfs(int depth, int sum, int c, int r){
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            int nc = c + dc[i];
            int nr = r + dr[i];
            if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
            if(visited[nc][nr]) continue;
            visited[nc][nr] = true;
            dfs(depth + 1, sum + map[nc][nr], nc, nr);
            visited[nc][nr] = false;
        }
    }
    
    public static void nkjh(int c, int r) {
        // ㅜ 
        if (c + 1 < H && r - 1 >= 0 && r + 1 < W) {
            int sum = map[c][r] + map[c+1][r] + map[c][r-1] + map[c][r+1];
            max = Math.max(max, sum);
        }

        // ㅏ 
        if (c - 1 >= 0 && c + 1 < H && r + 1 < W) {
            int sum = map[c][r] + map[c-1][r] + map[c+1][r] + map[c][r+1];
            max = Math.max(max, sum);
        }

        // ㅗ 
        if (c - 1 >= 0 && r - 1 >= 0 && r + 1 < W) {
            int sum = map[c][r] + map[c-1][r] + map[c][r-1] + map[c][r+1];
            max = Math.max(max, sum);
        }

        // ㅓ 모양 
        if (c - 1 >= 0 && c + 1 < H && r - 1 >= 0) {
            int sum = map[c][r] + map[c-1][r] + map[c+1][r] + map[c][r-1];
            max = Math.max(max, sum);
        }
    }
}