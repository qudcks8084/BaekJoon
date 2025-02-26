import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

        // 부모 배열 초기화 (각 노드는 자기 자신이 부모)
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new int[]{nodeA, nodeB, weight});
        }

        int sum = 0;
        int cnt = 0;

        while (cnt < V - 1) {
            int[] cur = pq.poll();
            int nodeA = cur[0];
            int nodeB = cur[1];
            int weight = cur[2];

            // 부모가 같은 경우 = 이미 같은 집합(사이클 발생 가능)
            if (find(nodeA) == find(nodeB)) continue;

            // 유니온 연산 (두 노드를 같은 집합으로 합침)
            union(nodeA, nodeB);

            sum += weight;
            cnt++;
        }

        System.out.println(sum);
    }

    // 부모 노드 찾기 (경로 압축 최적화 적용)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // 두 노드 연결 (유니온 연산)
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) parent[rootB] = rootA;
    }
}
