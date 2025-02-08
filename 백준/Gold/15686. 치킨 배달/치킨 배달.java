import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int N, M, answer;
    static ArrayList<int[]> chickens;
    static ArrayList<int[]> houses;
    static int[][] selected_chicken;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected_chicken = new int[M][2];

        map = new int[N][N];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        for(int c = 0 ; c < N ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                int now = Integer.parseInt(st.nextToken());
                if(now == 2){
                    chickens.add(new int[]{c, r});
                    map[c][r] = 0;
                    continue;
                }
                if(now == 1) houses.add(new int[]{c, r});
                map[c][r] = now;
            }
        }

        answer = Integer.MAX_VALUE;
        DFS(0, 0);
        System.out.println(answer);

    }

    public static void DFS(int depth, int start){
        if(depth == M){ // 치킨집 조합이 완성된 경우
            int min_chicken_distance = 0;
            for (int[] house : houses) {
                min_chicken_distance += BFS(house[0], house[1]);
                if(min_chicken_distance > answer) return;
            }
            answer = Math.min(answer, min_chicken_distance);
        }else{
            for(int i = start ; i < chickens.size() ; i++){
                int[] next = chickens.get(i);
                selected_chicken[depth] = next;
                map[next[0]][next[1]] = 2;
                DFS(depth + 1, i + 1);
                map[next[0]][next[1]] = 0;
                selected_chicken[depth] = null;
            }
        }
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static int BFS(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][N];
        visited[c][r] = true;
        q.offer(new int[]{c, r});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int n_c = now[0] + dc[i];
                int n_r = now[1] + dr[i];
                if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N || visited[n_c][n_r]) continue;
                if(map[n_c][n_r] == 2){ // find 가장 가까운 치킨집
                    return getDistance(c, r, n_c, n_r);
                }
                visited[n_c][n_r] = true;
                q.offer(new int[]{n_c, n_r});
            }
        }
        return 0;
    }

    public static int getDistance(int c1, int r1, int c2, int r2){
        return Math.abs(c1 - c2) + Math.abs(r1 - r2);
    }

}
