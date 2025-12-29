import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        // 간선을 저장할 배열을 생성
        boolean[][] connect = new boolean[N][N];

        for(int i = 0 ; i < p ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            connect[a][b] = true;
            connect[b][a] = true;
        }

        // 0번 컴퓨터가 감염되는 경우에 퍼지는 것을 감지
        boolean[] visited = new boolean[N];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 시작점 0번 진입 및 방문처리
        q.offer(0);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0 ; i < N ; i++){
                if(i == cur || visited[i]) continue;
                if(connect[cur][i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        // visited로 개수 세기
        int cnt = 0;
        for(int i = 0 ; i < N ; i++){
            if(visited[i]) cnt++;
        }

        if(cnt > 0) cnt--;

        System.out.println(cnt);
    }
}
