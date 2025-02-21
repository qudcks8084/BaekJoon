import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static int s_c, s_r;
    static int t_c, t_r;
    static boolean[][] map, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        s_c = Integer.parseInt(st.nextToken()) - 1;
        s_r = Integer.parseInt(st.nextToken()) - 1;
        t_c = Integer.parseInt(st.nextToken()) - 1;
        t_r = Integer.parseInt(st.nextToken()) - 1;

        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                map[c][r] = line[r] == '1';
            }
        }



        int time = 1;
        while(true){
            visited = new boolean[H][W];
            boolean killed = attack();
            if(killed) break;
            else time++;
        }

        System.out.println(time);
    }

    static int[] dc = {-1, 0, 1 ,0};
    static int[] dr = {0, 1, 0, -1};
    static int[] dd = {0, 1, 2, 3};
    public static boolean attack(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[s_c][s_r] = true;
        for (int direction : dd) { // 방향별로 공격을 넣어
            q.offer(new int[]{s_c, s_r, direction});
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int r = cur[1];
            int d = cur[2];
            while(true){ // 방향에 맞춰서 사람을 찾거나 or 끝지점까지 공격을 전달
                int n_c = c + dc[d];
                int n_r = r + dr[d];
                if(n_c == t_c && n_r == t_r){ // 종료 조건
                    return true;
                }
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) break; // 경계값을 넘어가면 종료
                if(visited[n_c][n_r]) break; // 이미 공격을 받았던 곳이여도 종료
                if(map[n_c][n_r]){ // 사람을 찾은 경우
                    visited[n_c][n_r] = true;
                    map[n_c][n_r] = false;
                    break;
                }

                // 사람을 못찾은 경우
                visited[n_c][n_r] = true;
                for (int direction : dd) {
                    q.offer(new int[]{n_c, n_r, direction});
                }
                // 사람을 못찾았다면 더 이동
                c = n_c;
                r = n_r;
            }
        }

        // 해당 공격에서는 못잡음
        return false;
    }
}
