import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Bus implements Comparable<Bus>{
        int before, vertex, weight;

        public Bus(int before, int vertex, int weight) {
            this.before = before;
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, M;
    static ArrayList<Bus>[] adjList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 각 배열의 이동 경로를 저장하기 위해서 저장
        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        // 버스의 노선 정보를 입력받음
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Bus(s, e, w));
        }

        // 출발점과 도착점의 좌표를 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken()) - 1;

        // 다익스트라에 필요한 데이터 구조 생성
        // 1. 방문 기록과, 이전 위치를 기록하기 위한 visited 배열 생성
        // 2. 시작점에서 나머지 좌표와의 최단 거리를 기록할 dp 배열 생성
        int[] visited = new int[N];
        Arrays.fill(visited, -1);
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 다익스트라를 위한 ProirityQueue 생성 및 초기값 생성
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(S, S, 0));

        // Dijkstra
        while (!pq.isEmpty()) {
            Bus cur = pq.poll();
            int v = cur.vertex;
            if (visited[v] != -1) continue;
            visited[v] = cur.before ; // 갱신되는 경우 이전 이동값을 변경
            dp[v] = cur.weight;
            for(Bus next : adjList[v]){
                if (dp[next.vertex] > dp[v] + next.weight) {
                    dp[next.vertex] = dp[v] + next.weight;
                    pq.offer(new Bus(v, next.vertex, dp[next.vertex]));
                }
            }

        }


        StringBuilder sb = new StringBuilder();

        // 최단거리를 출력
        sb.append(dp[E]).append("\n");

        // 방문 경로를 역추적
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = E;
        stack.push(E + 1);
        while(true){
            if(index == S) break;

            stack.push(visited[index] + 1);
            index = visited[index];
        }

        // 총 방문한 도시의 수를 출력
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        System.out.println(sb);
    }

}
