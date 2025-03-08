import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Move implements Comparable<Move>{
        int c, r, skill;

        public Move(int c, int r, int skill) {
            this.c = c;
            this.r = r;
            this.skill = skill;
        }

        @Override
        public int compareTo(Move o) {
            return Integer.compare(this.skill, o.skill);
        }

    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        // 벽과 공간을 분리하기 위해서 boolean으로 선언
        boolean[][] map = new boolean[H][W];
        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                map[c][r] = line[r] == '1';
            }
        }

        // 스킬에 따라 재방문하지 않도록 설정 - 최대 벽을 부실 수 있는 횟수는 H + W
        boolean[][][] visited = new boolean[H][W][H + W];

        // 벽을 적게 부신것부터 탐색하도록 수정
        PriorityQueue<Move> pq = new PriorityQueue<>();
        visited[0][0][0] = true;
        pq.offer(new Move(0, 0, 0));

        while (!pq.isEmpty()) {
            Move cur = pq.poll();
            int c = cur.c;
            int r = cur.r;
            int s = cur.skill;

            // 정답 처리
            if(c == H-1 && r == W-1){
                System.out.println(s);
                return;
            }
            for(int i = 0 ; i < 4 ; i++){
                int n_c = c + dc[i];
                int n_r = r + dr[i];
                
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;

                // 벽인 경우
                if(map[n_c][n_r]){
                    if(visited[n_c][n_r][s+1]) continue;
                    visited[n_c][n_r][s+1] = true;
                    pq.offer(new Move(n_c, n_r, s + 1));
                }

                // 빈 방인 경우
                else{
                    if(visited[n_c][n_r][s]) continue;
                    visited[n_c][n_r][s] = true;
                    pq.offer(new Move(n_c, n_r, s));
                }
            }
        }
    }
}
