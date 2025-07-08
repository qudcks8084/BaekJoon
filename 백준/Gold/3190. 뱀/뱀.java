import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    static HashMap<Integer, Boolean> movement;
    static boolean[][] apple;
    static boolean[][] visited;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        apple = new boolean[N][N];

        int K = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < K ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            apple[r][c] = true;
        }

        // 각 시간에 따른 이동 방향의 변화를 해쉬맵에 저장
        int L = Integer.parseInt(br.readLine());
        movement = new HashMap<>();
        for(int i = 0 ; i < L ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()) ;
            String d = st.nextToken();
            if(d.equals("L")) movement.put(t, true);
            else movement.put(t, false);
        }

        // 0초에서부터 움직이기 시작
        // 뱀의 초기 상태는 0,0
        // 뱀의 초기 움직이는 방향은 오른쪽 즉 1로 설정
        // 뱀의 몸통을 기록할 큐 구현
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{0, 0, 1});

        // 현재 뱀의 영역을 기록할 boolean 배열 생성
        visited = new boolean[N][N];
        visited[0][0] = true;

        int t = 1;

        while(true){
            // 현재 뱀의 머리를 확인
            int[] prev = snake.peekFirst();
            int p_c = prev[0];
            int p_r = prev[1];
            int p_d = prev[2];

            // 다음 위치를 계산
            int n_c = p_c + dc[p_d];
            int n_r = p_r + dr[p_d];

            // 종료조건 1. 벽에 부딛히는 경우
            // 종료조건 2. 뱀에 부딛히는 경우
            if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N || visited[n_c][n_r]) {
                System.out.println(t);
                return;
            }

            // 다음 이동할 장소에 사과가 있는지 확인
            if(!apple[n_c][n_r]){ // 사과가 없다면 마지막 꼬리 위치를 빼서 visited 해제
                int[] tail = snake.removeLast();
                visited[tail[0]][tail[1]] = false;
            }else{
                apple[n_c][n_r] = false;
            }

            // 방향을 수정하는지 확인
            if(movement.containsKey(t)){ // 만약 수정한다면
                if(movement.get(t)){ // 왼쪽으로 돈다면
                    snake.addFirst(new int[]{n_c, n_r, (p_d + 3) % 4});
                }else{
                    snake.addFirst(new int[]{n_c, n_r, (p_d + 1) % 4});
                }
            }
            else snake.addFirst(new int[]{n_c, n_r, p_d});

            visited[n_c][n_r] = true;

            t++;

        }

    }
}
