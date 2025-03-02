import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new int[]{e, w});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        boolean[] visited = new boolean[N];
        int[] dis = new int[N];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        }));

        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int e = cur[0];
            int w = cur[1];
            if(visited[e]) continue;
            visited[e] = true;
            for(int[] next : adjList[e]){
                int n_e = next[0];
                int n_w = next[1];
                if(dis[n_e] > w + n_w){
                    dis[n_e] = w + n_w;
                    pq.offer(new int[]{n_e, w + n_w});
                }
            }
        }

        System.out.println(dis[dest]);
    }
}
