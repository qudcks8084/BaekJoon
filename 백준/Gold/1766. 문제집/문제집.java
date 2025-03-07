import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] input_degree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        input_degree = new int[N];
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            input_degree[e]++; // 전입차수 추가
            adjList[s].add(e); // 이어지는 얘들 추가
        }

        // 전입 차수가 0인 얘들을 전부 추가
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0) pq.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur + 1).append(" ");

            // 현재 빼는 얘와 연결된 나머지 친구들의 차수를 감소시킨다.
            for(int next : adjList[cur]){
                input_degree[next]--;
                if(input_degree[next] == 0) pq.offer(next);
            }
        }

        System.out.println(sb);

    }
}
