import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    static int W, H;
    static char[][] map;
    static ArrayDeque<int[]> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int testcase = 0 ; testcase < T ; testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            q = new ArrayDeque<>();
            for(int c = 0 ; c < H ; c++){
                char[] line = br.readLine().toCharArray();
                for(int r = 0 ; r < W ; r++){
                    map[c][r] = line[r];
                    // 사람은 일단 맨 뒤에 넣어
                    if(map[c][r] == '@') q.addLast(new int[]{c, r});
                    // 불을 큐의 맨 앞에 다 넣어.
                    if(map[c][r] == '*') q.addFirst(new int[]{c, r});
                }
            }

            int result = find();
            if(result > 0) sb.append(result).append("\n");
            else sb.append("IMPOSSIBLE\n");

        }

        System.out.println(sb);
    }

    public static int find(){
        boolean[][] visited = new boolean[H][W];
        int time = 1;
        while (!q.isEmpty()) {
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();
                int c = cur[0];
                int r = cur[1];
                if(map[c][r] == '@' && (c == 0 || c == H-1 || r == 0 || r == W-1))
                    return time;
                for(int i = 0 ; i < 4 ; i++){
                    int n_c = c + dc[i];
                    int n_r = r + dr[i];
                    if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                    if(map[n_c][n_r] == '.' && !visited[n_c][n_r]){
                        visited[n_c][n_r] = true;
                        map[n_c][n_r] = map[c][r];
                        q.offer(new int[]{n_c, n_r});
                    }
                }
            }
            time++;
        }
        return -1;
    }
}
