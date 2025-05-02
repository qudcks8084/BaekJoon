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

        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        char[][] map = new char[H][W];
        for (int c = 0; c < H; c++) {
            char[] line = br.readLine().toCharArray();
            for (int r = 0; r < W; r++) {
                map[c][r] = line[r];
                if(line[r] == 'I'){
                    q.offer(new int[]{c, r});
                    visited[c][r] = true;
                }
            }
        }

        int friend = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int r = cur[1];

            for (int i = 0; i < 4; i++) {
                int nc = c + dc[i];
                int nr = r + dr[i];

                if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                if(visited[nc][nr] || map[nc][nr] == 'X') continue;
                if(map[nc][nr] == 'P') friend++;
                visited[nc][nr] = true;
                q.offer(new int[]{nc, nr});
            }
        }

        if(friend > 0) System.out.println(friend);
        else System.out.println("TT");


    }
}
