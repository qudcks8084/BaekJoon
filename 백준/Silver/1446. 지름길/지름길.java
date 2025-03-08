import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Road implements Comparable<Road> {
        int p, w;

        public Road(int p, int w) {
            this.p = p;
            this.w = w;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Road>[] adjList = new ArrayList[D + 1];
        for (int i = 0; i <= D; i++) {
            adjList[i] = new ArrayList<>();
            // 기본적으로 1칸 더 움직이는 도로를 추가
            if (i < D) adjList[i].add(new Road(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 역주행은 못해 -> 더 큰 값은 무시
            if (e > D) continue;
            adjList[s].add(new Road(e, w));
        }

        boolean[] visited = new boolean[D + 1];
        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(0, 0));

        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            int p = cur.p;
            int w = cur.w;

            if (visited[p]) continue;
            visited[p] = true;
            dp[p] = w;

            for (Road next : adjList[p]) {
                int np = next.p;
                int nw = next.w;

                if (dp[np] > w + nw) {
                    dp[np] = w + nw;
                    pq.offer(new Road(np, w + nw));
                }
            }
        }

        System.out.println(dp[D]);
    }
}