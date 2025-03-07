import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class E implements Comparable<E>{
        int v, w;

        public E(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(E o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int N, M, X;
    static ArrayList<E>[] adjList;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        // 각각의 도시에서 출발하는 edge를 저장하기 위한 adjList를 생성
        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new E(e, w));
        }

        // 각 배열간의 최단 거리를 저장할 배열 선언
        dp = new int[N][N];
        for(int start = 0 ; start < N ; start++){
            Arrays.fill(dp[start], INF); // dijkstra를 위해서 각 최단거리 배열을 INF로 초기화
            dijkstra(start);
        }

        // 최종 정답 구하기
        int max_distance = -1;
        for(int i = 0 ; i < N ; i++){
            if(i == X) continue; // 파티 위치는 제외
            max_distance = Math.max(max_distance, dp[i][X] + dp[X][i]);
        }

        System.out.println(max_distance);

    }

    public static void dijkstra(int start){
        // 간선으로 우선순위 정렬을 위한 우선순위 큐 생성
        PriorityQueue<E> pq = new PriorityQueue<>();

        // 확정 거리를 측정할 방문 배열을 생성
        boolean[] visited = new boolean[N];

        // 자기 자신부터 시작
        dp[start][start] = 0;
        pq.offer(new E(start, start));

        while (!pq.isEmpty()) {
            E cur = pq.poll();
            int v = cur.v;

            // 이미 최단거리가 갱신된 곳인지를 확인
            if(visited[v]) continue;
            visited[v] = true;

            // 연결되는 다음 통로의 값을 최신화
            for(E next : adjList[v]){
                if(dp[start][next.v] > next.w + dp[start][v]){
                    dp[start][next.v] = next.w + dp[start][v];
                    pq.offer(new E(next.v, dp[start][next.v]));
                }
            }
        }
    }
}
