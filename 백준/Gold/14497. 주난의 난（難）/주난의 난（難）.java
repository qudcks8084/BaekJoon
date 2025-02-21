import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s_c = Integer.parseInt(st.nextToken()) - 1;
        int s_r = Integer.parseInt(st.nextToken()) - 1;
        int t_c = Integer.parseInt(st.nextToken()) - 1;
        int t_r = Integer.parseInt(st.nextToken()) - 1;

        char[][] map = new char[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // visited[row][col][time]
        boolean[][][] visited = new boolean[H][W][H + W - 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // 시작점에서 4방향으로 큐에 삽입
        pq.offer(new int[]{s_c, s_r, 0});
        visited[s_c][s_r][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int c = cur[0];
            int r = cur[1];
            int time = cur[2];

            for (int d = 0; d < 4; d++) {
                int nc = c + dc[d];
                int nr = r + dr[d];

                if (nc < 0 || nc >= H || nr < 0 || nr >= W) continue;

                // 목표지점 도달
                if (nc == t_c && nr == t_r) {
                    System.out.println(time + 1);
                    return;
                }

                // 다음 위치가 '1'인 경우
                if (map[nc][nr] == '1') {
                    if (time + 1 < H + W - 1 && !visited[nc][nr][time + 1]) {
                        visited[nc][nr][time + 1] = true;
                        pq.offer(new int[]{nc, nr, time + 1});
                    }
                }
                // 다음 위치가 '0'인 경우
                else if (map[nc][nr] == '0') {
                    if (!visited[nc][nr][time]) {
                        visited[nc][nr][time] = true;
                        pq.offer(new int[]{nc, nr, time});
                    }
                }
            }
        }
    }
}
