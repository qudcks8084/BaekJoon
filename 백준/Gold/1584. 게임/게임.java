import java.io.*;
import java.util.*;
public class Main {

    static class Move implements Comparable<Move>{
        int c;
        int r;
        int w; // 지나친

        Move(int c, int r, int w){
            this.c = c;
            this.r = r;
            this.w = w;
        }

        @Override
        public int compareTo(Move o){
            return Integer.compare(this.w, o.w);
        }
    }

    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dc = {-1, 0, 1 ,0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 501;
        map = new int[N][N];

        // 위헙한 구역을 입력받음
        int D = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < D ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 위험 구역 등록
            dangerPlace(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), 1);
        }

        // 금지 구역을 입력받음.
        D = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < D ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 위험 구역 등록
            dangerPlace(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), 2);
        }

        visited = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE / 10);
        }

        // pq를 만들어서 0,0을 넣는다.
        PriorityQueue<Move> pq = new PriorityQueue<>();
        pq.offer(new Move(0, 0, 0));
        visited[0][0] = 0;

        while(!pq.isEmpty()){
            Move cur = pq.poll();;

            if(visited[cur.c][cur.r] < cur.w) continue;

            if(cur.c == N-1 && cur.r == N-1){
                System.out.println(cur.w);
                return;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nc = cur.c + dc[i];
                int nr = cur.r + dr[i];

                if(nc < 0 || nc >= N || nr < 0 || nr >= N) continue;

                // 만약 해당 구역이 금지 구역이면 못감
                if(map[nc][nr] == 2) continue;

                // 만약 그 위치보다 까인 체력이 적다면 갈 수 있음.
                // 안전구역이면 비용 안듬.
                // 위험 구역이면 1씩 까임.
                if(visited[nc][nr] > cur.w + map[nc][nr]){
                    visited[nc][nr] = cur.w + map[nc][nr];
                    pq.offer(new Move(nc, nr, visited[nc][nr]));
                }
            }
        }
        System.out.println(-1);
    }

    // 위헙 및 금지 구역을 등록하는 함수
    public static void dangerPlace(int x1, int y1, int x2, int y2, int value){
        int min_x = Math.min(x1, x2);
        int max_x = Math.max(x1, x2);

        int min_y = Math.min(y1, y2);
        int max_y = Math.max(y1, y2);

        for(int c = min_y ; c <= max_y ; c++){
            for(int r = min_x ; r <= max_x ; r++){
                map[c][r] = Math.max(map[c][r], value);
            }
        }
    }
}