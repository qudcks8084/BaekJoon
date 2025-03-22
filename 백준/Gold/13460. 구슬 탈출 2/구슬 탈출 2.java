import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static int[] O;
    static final int UNIT = 10;
    static boolean[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new boolean[H][W];

        int[] B = new int[2];    // 파랑 구슬
        int[] R = new int[2];    // 빨간 구슬
        O = new int[2];    // 구멍
        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                char tmp = line[r];
                if(tmp == '#'){
                    map[c][r] = true;
                }

                else if(tmp == 'B'){
                    B[0] = c;
                    B[1] = r;
                }

                else if(tmp == 'R'){
                    R[0] = c;
                    R[1] = r;
                }

                else if(tmp == 'O'){
                    O[0] = c;
                    O[1] = r;
                }
            }
        }


        // 최종 목표 -> 빨간 구슬을 구멍에 넣는 것
        // 경우의 수를 관리할 visited 배열을 생성
        // 각 구슬의 좌표는 0~9로 이루어져 있음으로 2차원 배열로 인덱싱해서 풀기
        visited = new boolean[UNIT * UNIT][UNIT * UNIT];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{B[0], B[1], R[0], R[1]});
        visited[B[0] * UNIT + B[1]][R[0] * UNIT + R[1]] = true;

        int time = 0;
        while (!q.isEmpty()) {
            if(time == 11) break;
            int len = q.size();
//            System.out.println("시간 : " + time);
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();
//                System.out.println("기존 : " + Arrays.toString(cur));

                // 정답 처리 코드 자리
                if(cur[2] == O[0] && cur[3] == O[1]){ // 빨간공이 구멍에 들어간 경우
                    System.out.println(time);
                    return;
                }
                for(int i = 0 ; i < 4 ; i++){
                    int[] next_B = new int[2];
                    int[] next_R = new int[2];
                    if(i == 0){ // 위로 가는거 -> c가 작은거부터
                        if(cur[0] < cur[2]){
                            next_B = move(cur[0], cur[1], i, cur[2], cur[3]);
                            next_R = move(cur[2], cur[3], i, next_B[0], next_B[1]);
                        }else{
                            next_R = move(cur[2], cur[3], i, cur[0], cur[1]);
                            next_B = move(cur[0], cur[1], i, next_R[0], next_R[1]);
                        }
                    }
                    else if (i == 1) { // 오른쪽으로 가는거 -> r이 큰거부터
                        if(cur[1] < cur[3]){
                            next_R = move(cur[2], cur[3], i, cur[0], cur[1]);
                            next_B = move(cur[0], cur[1], i, next_R[0], next_R[1]);
                        }else{
                            next_B = move(cur[0], cur[1], i, cur[2], cur[3]);
                            next_R = move(cur[2], cur[3], i, next_B[0], next_B[1]);
                        }
                    }
                    else if (i == 2) {
                        if(cur[0] > cur[2]){ // 아래로 가는거 -> c가 큰거부터
                            next_B = move(cur[0], cur[1], i, cur[2], cur[3]);
                            next_R = move(cur[2], cur[3], i, next_B[0], next_B[1]);
                        }else{
                            next_R = move(cur[2], cur[3], i, cur[0], cur[1]);
                            next_B = move(cur[0], cur[1], i, next_R[0], next_R[1]);
                        }
                    }
                    else {
                        if(cur[1] > cur[3]){ // 왼쪽으로 가는거 -> r이 작은거부터
                            next_R = move(cur[2], cur[3], i, cur[0], cur[1]);
                            next_B = move(cur[0], cur[1], i, next_R[0], next_R[1]);
                        }else{
                            next_B = move(cur[0], cur[1], i, cur[2], cur[3]);
                            next_R = move(cur[2], cur[3], i, next_B[0], next_B[1]);
                        }
                    }

                    // 파란 공이 구멍에 들어간 경우는 제외
                    if(next_B[0] == O[0] && next_B[1] == O[1]) continue;

                    // 중복된 방문을 제거
                    if(visited[next_B[0] * UNIT + next_B[1]][next_R[0] * UNIT + next_R[1]]) continue;
                    visited[next_B[0] * UNIT + next_B[1]][next_R[0] * UNIT + next_R[1]] = true;
                    q.offer(new int[]{next_B[0], next_B[1], next_R[0], next_R[1]});
//                    print(i, next_B, next_R);
                }

            }
            time++;
        }

        System.out.println(-1);

    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static int[] move(int c, int r, int d, int noc, int nor){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{c, r});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int n_c = cur[0] + dc[d];
            int n_r = cur[1] + dr[d];
            if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) return cur; // 경계값
            if(n_c == O[0] && n_r == O[1]) return new int[]{n_c, n_r}; // 구멍에 들어가는 경우
            if(n_c == noc && n_r == nor) return cur;
            if(map[n_c][n_r]) return cur;
            q.offer(new int[]{n_c, n_r});
        }

        return new int[]{c, r};
    }


    public static void print(int d, int[] B, int[] R){
        StringBuilder sb = new StringBuilder();
        sb.append("방향 : " + d).append("\n");
        sb.append("R ").append(Arrays.toString(R)).append(" | ");
        sb.append("B ").append(Arrays.toString(B)).append(" | ");
        sb.append("O ").append(Arrays.toString(O)).append("\n");
        for (int c = 0; c < H; c++) {
            for(int r = 0 ; r < W ; r++){
                if(B[0] == c && B[1] == r) sb.append("B ");
                else if(R[0] == c && R[1] == r) sb.append("R ");
                else if(O[0] == c && O[1] == r) sb.append("O ");
                else sb.append(map[c][r] ? "# " : "_ ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
