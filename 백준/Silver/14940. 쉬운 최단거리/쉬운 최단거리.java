import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                int tmp = Integer.parseInt(st.nextToken());
                if( tmp == 2 ) q.offer(new int[]{c, r}); // 2는 시작 지점
                else if(tmp == 0) map[c][r] = 0; // 0은 갈 수 없음
                else map[c][r] = -1; // 아직 거리가 측정되지 않은 공간
            }
        }

        int[] dc = {-1, 0, 1, 0};
        int[] dr = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int r = cur[1];
            for(int i = 0 ; i < 4 ; i++){
                int n_c = c + dc[i];
                int n_r = r + dr[i];
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                if(map[n_c][n_r] == -1){
                    map[n_c][n_r] = map[c][r] + 1;
                    q.offer(new int[]{n_c, n_r});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                sb.append(map[c][r]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
