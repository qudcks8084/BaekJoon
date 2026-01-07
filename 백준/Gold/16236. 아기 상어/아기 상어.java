import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;

    static int shark_c;
    static int shark_r;
    static int shark_w;
    static int shark_e;
    static int shark_t;

    public static class Fish implements Comparable<Fish>{
        int c;
        int r;
        int w;

        @Override
        public int compareTo(Fish o) {
            // 가장 위, 가장 왼쪽임으로
            if(this.c == o.c) return Integer.compare(this.r, o.r);
            return Integer.compare(this.c, o.c);
        }

        public Fish(int c, int r, int w){
            this.c = c;
            this.r = r;
            this.w = w;
        }
    }

    static int[] dc = {-1, 0 ,1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int c = 0 ; c < N ; c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                map[c][r] = Integer.parseInt(st.nextToken());

                if(map[c][r] == 9){
                    shark_c = c;
                    shark_r = r;
                    shark_w = 2; // 초기 사이즈는 2
                    shark_e = 0;
                    shark_t = 0;
                }
            }
        }

        while(true){
            PriorityQueue<Fish> pq = new PriorityQueue<>();

            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{shark_c, shark_r});

            boolean[][] visited = new boolean[N][N];
            visited[shark_c][shark_r] = true;

            int time = 1;

            // 물고기 찾기
            while(!q.isEmpty()){
                int len = q.size();

                for(int l = 0 ; l < len ; l++){
                    int[] cur = q.poll();

                    for(int i = 0 ; i < 4 ; i++){
                        int nc = cur[0] + dc[i];
                        int nr = cur[1] + dr[i];

                        if(nc < 0 || nc >= N || nr < 0 || nr >= N) continue;
                        if(visited[nc][nr]) continue;
                        if(map[nc][nr] > shark_w) continue;

                        visited[nc][nr] = true;

                        // 지나갈 수 있는 경우
                        if(map[nc][nr] == 0 || map[nc][nr] == shark_w){
                            q.offer(new int[]{nc, nr});
                        }

                        // 먹을 수 있는 경우
                        else if(map[nc][nr] < shark_w){
                            pq.offer(new Fish(nc, nr, map[nc][nr]));
                        }
                    }
                }

                if(!pq.isEmpty()) break;
                time++;
            }

            // 먹을 수 있는 물고기를 찾았는지, 끝난건지 파악
            if(pq.isEmpty()){
                System.out.println(shark_t);
                break;
            }

            // 먹을 수 있는 물고기가 있다면
            else{
                Fish target = pq.poll();
                map[target.c][target.r] = 9;
                map[shark_c][shark_r] = 0;
                shark_c = target.c;
                shark_r = target.r;
                shark_e++;
                shark_t += time;
                if(shark_w == shark_e){
                    shark_w++;
                    shark_e = 0;
                }

            }

//            print();
        }

    }

    // 디버깅용
    public static void print(){
        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                sb.append(map[c][r]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
