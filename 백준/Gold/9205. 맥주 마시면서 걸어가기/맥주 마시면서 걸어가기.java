import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        tc:for(int t = 0 ; t < T ; t++){

            /*
            *   1. 집과 펜타포트 락 페스티벌 좌표는 저장.
            *   2. 편의점이 100개임으로 각 편의점별 n+2 * n+2개의 AdjMatrix를 생성
            *       - if 2 편의점 사이의 거리가 맨해튼 거리가 1000 이하면 갈 수 있음.
            *   3. 시작을 편의점에 Q에 넣고 BFS를 탐색.
            *   4. Q가 빌때까지 편의점에 도착하면 happy, 아니면 sad
            * */

            int N = Integer.parseInt(br.readLine());

            boolean[][] adjMatrix = new boolean[N+2][N+2];
            int[][] location = new int[N+2][2];

            for(int i = 0 ; i < N+2 ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                location[i][0] = Integer.parseInt(st.nextToken());
                location[i][1] = Integer.parseInt(st.nextToken());
            }

            // 방문 가능 adjMatrix 생성
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if(i == j) continue;

                    int x_gap = Math.abs(location[i][0] - location[j][0]);
                    int y_gap = Math.abs(location[i][1] - location[j][1]);
                    if( x_gap + y_gap <= 1000 ){
                        adjMatrix[i][j] = true;
                        adjMatrix[j][i] = true;
                    }
                }
            }

            // 시작점에서부터 BFS

            boolean[] visited = new boolean[N+2];
            ArrayDeque<Integer> q = new ArrayDeque<>();

            visited[0] = true;
            q.offer(0);

            while(!q.isEmpty()){
                int cur = q.poll();

                // 다음 편의점 or 도착지를 찾아 떠난다.
                for (int i = 0; i < N + 2; i++) {
                    if(visited[i] || i == cur) continue;
                    if(adjMatrix[cur][i]){
                        visited[i] = true;

                        // 펜타포트 락 페스티벌 도착
                        if (i == N + 1) {
                            sb.append("happy\n");
                            continue tc;
                        }

                        q.offer(i);
                    }
                }
            }

            // 만약 그냥 끝났다면
            sb.append("sad\n");
        }

        System.out.print(sb);
    }
}
