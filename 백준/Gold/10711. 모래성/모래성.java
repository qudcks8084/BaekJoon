import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int H,W;
    static int[][] map;
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                char tmp = line[r];
                if (tmp == '.'){ // 바다라면 -1로
                    q.offer(new int[]{c, r});
                    map[c][r] = -1;
                }
                else map[c][r] = tmp - '0';
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            int destroy = 0;
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();
                int c = cur[0];
                int r = cur[1];

                // 바다 기준으로 주위에 0보다 큰 모래성이 있다면 -1;
                for(int i = 0 ; i < 8 ; i++){
                    int n_c = c + dc[i];
                    int n_r = r + dr[i];
                    if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                    if(map[n_c][n_r] > 0){
                        map[n_c][n_r]--;
                        if(map[n_c][n_r] == 0){ // 만약 파도에 의해서 모래성이 0이 되었다면.
                            map[n_c][n_r] = -1;
                            q.offer(new int[]{n_c, n_r});
                            destroy++;
                        }
                    }
                }
            }

            // 부서진게 없음
            if(destroy == 0){
                System.out.println(time);
                return;
            }
            time++;
        }

        // 마지막에 다 부시고 끝남
        System.out.println(time);


    }
    

}
