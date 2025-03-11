
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class edge implements Comparable<edge>{
        int s, v, w;

        public edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<edge>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            adjList[i] = new ArrayList<>();
        }

        long maxdistance = 0;
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            maxdistance += w;
            adjList[s].add(new edge(e, w));
            adjList[e].add(new edge(s, w));
        }

        boolean[] visited = new boolean[N];

        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.offer(new edge(0, 0));

        long minDistance = 0;
        int numOfNode = 0;
        while (!pq.isEmpty()){
            if(numOfNode == N) break;
            edge cur = pq.poll();
            int v = cur.v;
            int w = cur.w;
            if(visited[v]) continue;
            visited[v] = true;
            minDistance += w;
            numOfNode++;
            for(edge next : adjList[v]){
                pq.offer(next);
            }
        }

        if(numOfNode == N){
            System.out.println(maxdistance - minDistance);
        }
        else System.out.println(-1);
    }
}
