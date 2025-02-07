import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int N;
    static int s_c, s_r, t_c, t_r;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T ; t++){
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            s_c = Integer.parseInt(st.nextToken());
            s_r = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            t_c = Integer.parseInt(st.nextToken());
            t_r = Integer.parseInt(st.nextToken());

            if(s_c == t_c && s_r == t_r) { // 시작 위치와 같은 경우
                sb.append("0\n");
                continue;
            }

            Knight(s_c, s_r);

        }

        System.out.println(sb);
    }

    static int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void Knight(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[c][r] = true;
        q.offer(new int[]{c, r});
        int movement = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                int[] now = q.poll();
                for(int j = 0 ; j < 8 ; j++){
                    int n_c = now[0] + dc[j];
                    int n_r = now[1] + dr[j];
                    if(n_c == t_c && n_r == t_r){
                        sb.append(movement).append("\n");
                        return;
                    }
                    if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N) continue;
                    if(!visited[n_c][n_r]){
                        visited[n_c][n_r] = true;
                        q.offer(new int[]{n_c, n_r});
                    }
                }
            }
            movement++;
        }
    }

}
