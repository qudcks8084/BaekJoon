import java.io.*;
import java.util.*;

public class Main {

    public static class Edge implements Comparable<Edge>{
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++)
            adjList[i] = new ArrayList<>();

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            adjList[s].add(new Edge(s, e, w));
            adjList[e].add(new Edge(e, s, w));
        }

        // 다익스트리를 하면서 K
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE / 10);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[0] = 0;
        for(Edge next : adjList[0]){
            pq.offer(next);
            dist[next.e] = next.w;
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){

            Edge cur = pq.poll();

            if(dist[cur.e] < cur.w) continue;

            // 최단 거리면 그걸 이제 출력해.

            cnt++;
            sb.append(cur.s + 1).append(" ").append(cur.e + 1).append("\n");

            // 다음 경로 찾기
            for(Edge next : adjList[cur.e]){
                if(dist[next.e] > cur.w + next.w){
                    dist[next.e] = cur.w + next.w;
                    pq.offer(new Edge(next.s, next.e, dist[next.e]));
                }
            }
        }
        System.out.println(cnt);
        System.out.print(sb);
    }
}


