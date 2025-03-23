import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H][N-1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()) - 1;

            // h 높이에 s에서 s+1로 연결되는 선이 존재함
            ladder[h][s] = true;
        }

        // 이미 조건을 만족하는지 확인
        if(check()) {
            System.out.println(0);
            return;
        }

        // 1개부터 3개까지 가로선 추가
        for(int i = 1; i <= 3; i++) {
            dfs(0, 0, 0, i);
            if(answer <= i) {
                System.out.println(answer);
                return;
            }
        }

        System.out.println(-1);
    }

    // cnt: 현재까지 추가한 가로선 개수, goal: 목표 가로선 개수
    static void dfs(int x, int y, int cnt, int goal) {
        if(cnt == goal) {
            if(check()) answer = goal;
            return;
        }
        
        for(int i = x; i < H; i++) {
            for(int j = (i == x ? y : 0); j < N-1; j++) {
                // 이미 있늬~?
                if(ladder[i][j]) continue;
                
                // 오른쪽 왼쪽 있늬~?
                if(j > 0 && ladder[i][j-1]) continue;
                if(j < N-2 && ladder[i][j+1]) continue;

                ladder[i][j] = true;
                // 다음 위치로 이동, 같은 행이면 j+2로, 다른 행이면 처음부터
                if(j + 2 < N-1) {
                    dfs(i, j+2, cnt+1, goal);
                } else {
                    dfs(i+1, 0, cnt+1, goal);
                }
                ladder[i][j] = false;
            }
        }
    }

    static boolean check() {
        for(int start = 0; start < N; start++) {
            int cur = start;
            for(int i = 0; i < H; i++) {
                if(cur > 0 && ladder[i][cur-1]) {
                    cur--;
                } else if(cur < N-1 && ladder[i][cur]) {
                    cur++;
                }
            }
            if(cur != start) return false;
        }
        return true;
    }
}