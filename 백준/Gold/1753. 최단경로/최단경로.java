import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, S;
    static ArrayList<int[]>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine()) - 1;

        // 인접 리스트 초기화
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            // 더 작은 가중치의 간선이 있는지 확인
            boolean found = false;
            for (int[] edge : adjList[s]) {
                if (edge[0] == e) {
                    if (edge[1] > w) {
                        edge[1] = w;
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                adjList[s].add(new int[]{e, w});
            }
        }

        // 최단 거리를 저장할 배열
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[S] = 0;

        // 방문 여부를 체크할 배열
        boolean[] visited = new boolean[V];

        // w에 따라 오름차순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{S, 0}); // [정점, 거리]

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int dist = current[1];

            // 직전에서 이미 탐색된 노드는 탐색 종료 -> 이미 최적의 경로로 왔음
            if (visited[vertex]) continue;
            visited[vertex] = true;

            // 인접 리스트를 사용하여 연결된 정점만 탐색
            for (int[] edge : adjList[vertex]) {
                int nextVertex = edge[0];
                int weight = edge[1];

                int newDist = dist + weight;
                if (newDist < distance[nextVertex]) {
                    distance[nextVertex] = newDist;
                    pq.offer(new int[]{nextVertex, newDist});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            if (S == i) sb.append("0");
            else if (distance[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(distance[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}