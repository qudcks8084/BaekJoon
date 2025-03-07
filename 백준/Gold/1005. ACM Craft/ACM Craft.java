import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int testcase = 0 ; testcase < T ; testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 건설 딜레이를 지정할 int 배열 선언
            int[] delay = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++){
                delay[i] = Integer.parseInt(st.nextToken());
            }

            // 건설의 상관관계 조사를 위한 adjList 생성
            ArrayList<Integer>[] adjList = new ArrayList[N];
            for(int i = 0 ; i < N ; i++){
                adjList[i] = new ArrayList<>();
            }

            // 입력 차수를 저장할 int 배열을 선언
            int[] input_degree = new int[N];

            // 건설 관계 입력 받기
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                input_degree[e]++;
                adjList[s].add(e);
            }

            // 최종 타겟을 입력받음
            int X = Integer.parseInt(br.readLine()) - 1;

            // 진입 차수가 0인 것들을 찾아 큐에 기록
            ArrayDeque<int[]> q = new ArrayDeque<>();
            for(int i = 0 ; i < N ; i++){
                if(input_degree[i] == 0) q.offer(new int[]{i, 0});
            }

            // 최대 대기 시간을 기록할 배열을 추가
            int[] max_delay = new int[N];

            // Topology sort
            while (!q.isEmpty()){
                int[] cur = q.poll();
                int c = cur[0];
                int t = cur[1];
                max_delay[c] = Math.max(max_delay[c], t);
                for(int next : adjList[c]){ // 갈 수 있는 것들에 대해서 추가 가중치를 삽입한다.
                    input_degree[next]--;
                    max_delay[next] = Math.max(max_delay[next], max_delay[c] + delay[c]);
                    if(input_degree[next] == 0){
                        q.offer(new int[]{next, t + delay[c]});
                    }
                }
            }

            //정답 출력
            sb.append(max_delay[X] + delay[X]).append("\n");

        }

        System.out.println(sb);
    }
}
