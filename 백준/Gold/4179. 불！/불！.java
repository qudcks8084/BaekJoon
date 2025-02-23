import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int W, H;
    static char[][] map;
    static boolean[][] visited;
    static int j_c, j_r;
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new boolean[H][W];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        for(int c = 0 ; c < H ; c++){
            char[] input = br.readLine().toCharArray();
            for (int r = 0; r < W; r++) {
                map[c][r] = input[r];
                if(map[c][r] == 'J'){
                    j_c = c;
                    j_r = r;
                }
                if(map[c][r] == 'F'){ // 먼저 불을 넣어
                    q.offer(new int[]{c, r});
                    visited[c][r] = true;
                }
            }
        }

        q.offer(new int[]{j_c, j_r}); // 다음에 지훈이가 가
        visited[j_c][j_r] = true;

        int time = 1;
        while(!q.isEmpty()){
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();
                int c = cur[0];
                int r = cur[1];
                // 정답 확인 - 경계값에 존재하는지
                if(map[c][r] == 'J' && (c == 0 || c == H-1 || r == 0 || r == W-1)) {
                    System.out.println(time);
                    return;
                }
                for(int i = 0 ; i < 4 ; i++){
                    int n_c = c + dc[i];
                    int n_r = r + dr[i];
                    if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                    if (!visited[n_c][n_r] && map[n_c][n_r] == '.') {
                        visited[n_c][n_r] = true;
                        map[n_c][n_r] = map[c][r];
                        q.offer(new int[]{n_c, n_r});
                    }
                }
            }
            time++;
        }

        System.out.println("IMPOSSIBLE");
    }

}
