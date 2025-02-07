import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] map;
    static int W, H, numOfIsland;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0){
                System.out.println(sb);
                return;
            }

            map = new boolean[H][W];
            for(int c = 0 ; c < H ; c++){
                st = new StringTokenizer(br.readLine());
                for(int r = 0 ; r < W ; r++){
                    map[c][r] = st.nextToken().equals("1");
                }
            }

            numOfIsland = 0;
            for(int c = 0 ; c < H ; c++){
                for(int r = 0 ; r < W ; r++){
                   if(map[c][r]){
                       numOfIsland++;
                       walk(c, r);
                   }
                }
            }

            sb.append(numOfIsland).append("\n");
        }
    }

    static int[] dc = {-1, -1, 0, 1, 1, 1, -1, 0};
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void walk(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        map[c][r] = false;
        q.offer(new int[]{c, r});
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i = 0 ; i < 8 ; i++){
                int n_c = now[0] + dc[i];
                int n_r = now[1] + dr[i];
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                if(map[n_c][n_r]){
                    map[n_c][n_r] = false;
                    q.offer(new int[]{n_c, n_r});
                }
            }
        }
    }
}
