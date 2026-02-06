import java.io.*;
import java.util.*;
public class Main {

    public static class Edge implements Comparable<Edge>{
        int e;
        int w;

        public Edge(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.w, o.w);
        }

    }

    static int N;
    static ArrayList<Edge>[] adjList;
    static int[][] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < T ; t++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 인접 리스트 생성하기
            adjList = new ArrayList[N];
            for(int i = 0 ; i < N ; i++){
                adjList[i] = new ArrayList<>();
            }

            // 인접 리스트 입력받기
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());

                adjList[a].add(new Edge(b, w));
                adjList[b].add(new Edge(a, w));
            }

            // 전역변수 선언 및 다익스트라 돌리기
            dist = new int[N][N];
            for(int i = 0 ; i < N ; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
                dijkstra(i);
            }

            // 친구의 리스트 구하기
            int K = Integer.parseInt(br.readLine());
            int[] friends = new int[K];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < K ; i++){
                friends[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            // 최소값 구하기
            int idx = -1;
            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i < N ; i++){

                // 해당 장소까지의 최소 합산 거리 계산
                int sum = 0;
                for(int friend : friends){
                    sum += dist[i][friend];
                }

                if(min > sum){
                    min = sum;
                    idx = i+1;
                }
            }
            sb.append(idx).append("\n");
        }

        System.out.print(sb);
    }

    public static void dijkstra(int s){
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(s, 0));
        dist[s][s] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(dist[s][cur.e] < cur.w) continue;

            for(Edge next : adjList[cur.e]){
                if(dist[s][next.e] > cur.w + next.w){
                    dist[s][next.e] = cur.w + next.w;
                    pq.offer(new Edge(next.e, dist[s][next.e]));
                }
            }
        }
    }
}
