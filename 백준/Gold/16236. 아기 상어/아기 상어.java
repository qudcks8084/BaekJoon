import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {

    static class Sea implements Comparable<Sea> {
        int c, r;

        public Sea(int c, int r) {
            this.c = c;
            this.r = r;
        }

        @Override
        public int compareTo(Sea o) {
            if(this.c == o.c) return this.r - o.r;
            return this.c - o.c;
        }
    }

    static int N, shark_size, shark_ex, time;
    static int[][] map;
    static Sea shark;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for(int c = 0 ; c < N ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                map[c][r] = Integer.parseInt(st.nextToken());
                if(map[c][r] == 9) shark = new Sea(c, r);
            }
        }

        time = 0;
        shark_size = 2;
        shark_ex = 0;
        while(true){
            int move = MoveNextFish();
            if(move == 0){ // 잡을 수 있는 물고기가 없는 경우
                break;
            }
            time += move;
            shark_ex++;
            if(shark_size == shark_ex) {
                shark_size++;
                shark_ex = 0;
            }
        }

        System.out.println(time);

    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static int MoveNextFish(){
        ArrayList<Sea> nextFish = new ArrayList<>();
        ArrayDeque<Sea> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(shark);
        visited[shark.c][shark.r] = true;
        boolean find = false;
        int findTime = 0;
        while (!q.isEmpty()){
            findTime++;
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                Sea now = q.poll();
                for(int j = 0 ; j < 4 ; j++){
                    int nc = now.c + dc[j];
                    int nr = now.r + dr[j];
                    if(nc < 0 || nc >= N || nr < 0 || nr >= N ) continue;
                    if(visited[nc][nr] || map[nc][nr] > shark_size ) continue;
                    Sea fish = new Sea(nc, nr);
                    visited[nc][nr] = true;
                    if(map[nc][nr] != 0 && map[nc][nr] < shark_size){ // 잡을 수 있는 생선인 경우
                        find = true;
                        nextFish.add(fish);
                    }else{ // 비어있는 경우
                        q.offer(fish);
                    }
                }
            }
            if(find) break;
        }

        if(!find){ // 잡을 수 있는 물고기가 없음
            return 0;
        }else if (nextFish.size() == 1) { // 잡을 수 있는 물고기가 1마리일때
            Sea catchFish = nextFish.get(0);
            map[catchFish.c][catchFish.r] = 0;
            map[shark.c][shark.r] = 0;
            shark = catchFish;
            return findTime;
        }else{ // 잡을 수 있는 물고기가 여러마리인 경우
            Collections.sort(nextFish);
            Sea catchFish = nextFish.get(0);
            map[catchFish.c][catchFish.r] = 0;
            map[shark.c][shark.r] = 0;
            shark = catchFish;
            return findTime;
        }
    }

}
