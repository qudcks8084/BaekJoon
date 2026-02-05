import java.util.*;
import java.io.*;

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()) - 1;

            // 인접 리스트 생성
            ArrayList<Edge>[] adjList = new ArrayList[N];
            for(int i = 0 ; i < N ; i++)
                adjList[i] = new ArrayList<>();

            // 간선 입력
            for(int i = 0 ; i < D ; i++){
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());

                // e가 감염되면 w초 이후에 s가 감염
                adjList[e].add(new Edge(s, w));
            }

            // 감염의 여부를 저장
            int[] infection = new int[N];
            Arrays.fill(infection, Integer.MAX_VALUE / 10);

            // C에서 부터 시작해서 시간이 빠른 순서대로 감염.
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(C, 0));
            infection[C] = 0;

            int max = 0;
            int cnt = 0;
            while(!pq.isEmpty()){
                Edge cur = pq.poll();

                if(infection[cur.e] < cur.w) continue;

                cnt++;
                max = Math.max(max, cur.w);

                for(Edge next : adjList[cur.e]){
                    if(infection[next.e] <= cur.w + next.w) continue;
                    infection[next.e] = cur.w + next.w;
                    pq.offer(new Edge(next.e, cur.w + next.w));
                }
            }

            sb.append(cnt).append(" ").append(max).append("\n");

        }
        System.out.println(sb);
    }
}
