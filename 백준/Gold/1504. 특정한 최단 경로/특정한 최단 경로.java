import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Vertex implements Comparable<Vertex>{
        int v; // 위치
        int w; // 가중치
        int f; // A와 B를 flag로 관리

        public Vertex(int v, int w, int f) {
            this.v = v;
            this.w = w;
            this.f = f;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int N, E;
    static int A, B;
    static ArrayList<Vertex>[] adjList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 간선 정보를 저장할 adjList를 생성 및 초기화
        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        // 간선 정보를 저장
        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Vertex(e, w, 0));
            adjList[e].add(new Vertex(s, w, 0));
        }

        // 거쳐야 하는 2 정점을 입력받는다.
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;

        // 최종 경로 확정 관리를 위한 2차원 visited
        // 1번째 차원은 노드의 방문을 관리
        // 2번째 차원은 A와 B노드의 방문을 관리
        // 각 차원별로 최단 거리를 기록할 dp 배열을 생성
        boolean[][] visited = new boolean[N][4];
        int[][] dp = new int[N][4];
        for(int i = 0 ; i < N ; i ++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        dp[0][0] = 0;  // 시작 정점의 초기 거리
        if (A == 0) pq.offer(new Vertex(0, 0, 1));
        else pq.offer(new Vertex(0, 0, 0));

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            int v = cur.v;
            int w = cur.w;
            int f = cur.f;
            if(visited[v][f]) continue;
            visited[v][f] = true;
            dp[v][f] = w;
            for(Vertex next : adjList[v]){
                int n_v = next.v;
                int n_w = next.w;
                int n_f = f;
                // A가 다음 위치라면
                if(n_v == A) n_f |= 1;
                // B가 다음 위치라면
                if(n_v == B) n_f |= 2;
                if(dp[n_v][n_f] > w + n_w){
                    dp[n_v][n_f] = w + n_w;
                    pq.offer(new Vertex(n_v, w + n_w, n_f));
                }
            }
        }

        if(dp[N-1][3] == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(dp[N-1][3]);
    }
}
