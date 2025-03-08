import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[H][W];

        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                map[c][r] = line[r] == '1';
            }
        }
        
        boolean[][][] morning = new boolean[H][W][K + 1];
        boolean[][][] night = new boolean[H][W][K + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        morning[0][0][K] = true; // 시작은 낮이다.
        q.offer(new int[]{0, 0, K});

        int time = 0;
        while (!q.isEmpty()) {

            // 현재 시간을 미리 측정
            boolean isDay = time % 2 == 0;
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();
                int c = cur[0];
                int r = cur[1];
                int k = cur[2];

                // 정답 처리
                if(c == H - 1 && r == W - 1){
                    System.out.println(time + 1);
                    return;
                }

                // 낮이라면 다음은 밤으로 / 벽을 부실 수 있고 / 4방향 이동
                if(isDay){
                    // 아직 부실 수 있는 횟수가 남아 있다면?
                    if(k > 0){
                        for(int i = 0 ; i < 4 ; i++){
                            int n_c = c + dc[i];
                            int n_r = r + dr[i];
                            if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                            // 벽이며, 벽을 부시는 경우를 방문하지 않은 경우에만 갈 수 있음
                            if(!map[n_c][n_r] || night[n_c][n_r][k-1]) continue;
                            night[n_c][n_r][k-1] = true;
                            q.offer(new int[]{n_c, n_r, k-1, 1});
                        }
                    }

                    // 부실 수 있는 횟수가 없다면? -> 일반 4방 이동
                    for(int i = 0 ; i < 4 ; i++){
                        int n_c = c + dc[i];
                        int n_r = r + dr[i];
                        if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                        // 벽이 아니고, 방문하지 않은 경우에 갈 수 있음
                        if(map[n_c][n_r] || night[n_c][n_r][k]) continue;
                        night[n_c][n_r][k] = true;
                        q.offer(new int[]{n_c, n_r, k, 1});
                    }
                }

                // 밤이라면 다음은 낮으로 / 벽을 부시지 못하고 4방향 + 가만히 있기를 시전
                else{
                    // 가만히 있기
                    if(!morning[c][r][k]){ // 아직 낮을 방문하지 않았다면
                        morning[c][r][k] = true;
                        q.offer(new int[]{c, r, k, 0});
                    }

                    // 4방향 이동하기
                    for(int i = 0 ; i < 4 ; i++){
                        int n_c = c + dc[i];
                        int n_r = r + dr[i];
                        if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                        if(map[n_c][n_r] || morning[n_c][n_r][k]) continue;
                        morning[n_c][n_r][k] = true;
                        q.offer(new int[]{n_c, n_r, k, 0});
                    }
                }
            }
            time++;
        }

        System.out.println(-1);
    }
}
