import java.io.*;
import java.util.*;

public class Main{

    static class Edge implements Comparable<Edge>{
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
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, w));
            adjList[b].add(new Edge(a, w));
        }

        // 성하와 지헌이의 위치를 찾는다.
        st = new StringTokenizer(br.readLine());
        int j_idx = Integer.parseInt(st.nextToken()) - 1;
        int s_idx = Integer.parseInt(st.nextToken()) - 1;

        // 성하와 지헌이에서 다익스트라를 1번씩 쓴다.
        int[] j_dist = dijkstra(j_idx);
        int[] s_dist = dijkstra(s_idx);

        // 일단 최소 값을 찾아
        int t_min = Integer.MAX_VALUE / 2;
        for(int i = 0 ; i < N ; i++){
            // 2. 성하와 지헌이의 장소는 제외된다.
            if(i == s_idx || i == j_idx) continue;

            t_min = Math.min(t_min, s_dist[i] + j_dist[i]);
        }

        // 이제 조건을 만족하는 최적의 장소를 고른다.
        int idx = -1;
        int j_min = Integer.MAX_VALUE / 2;
        // 다음 조건을 거르면서 정답을 찾아.
        for(int i = 0 ; i < N ; i++){

            // 1. 성하와 지헌이의 장소는 제외된다.
            if(i == s_idx || i == j_idx) continue;

            // 2. 만약 최소가 아니라면 제외된다.
            if(t_min < s_dist[i] + j_dist[i]) continue;

//            System.out.println((i+1) + "번에서 만난다면? 합산 거리 : " + (s_dist[i] + j_dist[i]) + " | 지헌이의 이동 거리는 : " + j_dist[i]);

            // 3. 만약 지헌이가 성하보다 멀리 있으면 제외된다.
            if(s_dist[i] < j_dist[i]) continue;

            // 4. 만약 최소 거리로 같으면 지헌이가 가까울수록 좋다.
            if(j_min > j_dist[i]){
                j_min = j_dist[i];
                idx = i;
            }

        }

        if(idx == -1) System.out.println(idx);
        else System.out.println(idx + 1);
    }

    public static int[] dijkstra(int start){
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(dist[cur.e] < cur.w) continue;

            for(Edge next : adjList[cur.e]){
                if(dist[next.e] > cur.w + next.w){
                    dist[next.e] = cur.w + next.w;
                    pq.offer(new Edge(next.e, dist[next.e]));
                }
            }
        }

        return dist;
    }
}
