import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    static char[][] map;
    static boolean[][] visited;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        // 입력을 받아.
        for(int c = 0 ; c < N ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < N ; r++){
                map[c][r] = line[r];
            }
        }

        int RGB_AREA = 0;
        visited = new boolean[N][N];
        // 다음 모든 좌포에 대해서 RGB 구역을 찾기
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                if(!visited[c][r]){ // 아직 탐색하지 않는 구역이라면 입장
                    RGB_AREA++;
                    RGB(c, r);
                }
            }
        }
        sb.append(RGB_AREA).append(" ");

        // G를 R로 다 만들어
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                if(map[c][r] == 'G') map[c][r] = 'R';
            }
        }

        // 적록색약 버전을 돌려
        int RG_B_AREA = 0;
        visited = new boolean[N][N];
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                if(!visited[c][r]){ // 아직 탐색하지 않는 구역이라면 입장
                    RG_B_AREA++;
                    RGB(c, r);
                }
            }
        }
        sb.append(RG_B_AREA);
        System.out.println(sb);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void RGB(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        char color = map[c][r];
        visited[c][r] = true;
        q.offer(new int[]{c, r});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int n_c = now[0] + dc[i];
                int n_r = now[1] + dr[i];
                if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N) continue;
                if (!visited[n_c][n_r] && map[n_c][n_r] == color) {
                    visited[n_c][n_r] = true;
                    q.offer(new int[]{n_c, n_r});
                }
            }
        }
    }
}
