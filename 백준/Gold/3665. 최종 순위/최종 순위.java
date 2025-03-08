import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        testcase : for(int testcase = 1 ; testcase <= T ; testcase++){

            int N = Integer.parseInt(br.readLine());

            // 관계 저장을 위한 adjMatrix 를 생성
            boolean[][] adjMatrix = new boolean[N][N];

            // 작년의 등수를 입력 받음
            int[] prev_rank = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ;  i < N ; i++){
                prev_rank[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            // 작년의 등수를 이용해서 이전 상관관계 집어넣기
            for(int i = 0 ; i < N ; i++){
                for (int j = i + 1; j < N; j++) {
                    adjMatrix[prev_rank[i]][prev_rank[j]] = true;
                }
            }

            // 올해 수정되는 등수를 입력받음
            int numOfModify = Integer.parseInt(br.readLine());

            boolean notAllowed = false;
            for(int i = 0 ; i < numOfModify ; i++) {
                st = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(st.nextToken()) - 1;
                int next = Integer.parseInt(st.nextToken()) - 1;

                // 데이터의 일관성을 퐉인 만약 순위가 바뀌지 않는 데이터가 들어온다면 "IMPOSSIBLE" 출력
                // 이전 대회에서 거꾸로된 결과가 존재한다면
                if (adjMatrix[next][prev]) {
                    adjMatrix[next][prev] = false;
                    adjMatrix[prev][next] = true;
                }else if(adjMatrix[prev][next]){
                    adjMatrix[prev][next] = false;
                    adjMatrix[next][prev] = true;
                }else{
                    notAllowed = true;
                }
            }

            if(notAllowed){
                System.out.println("IMPOSSIBLE");
                continue testcase;
            }

            // 전입 차수 Input_degree를 측정
            int[] input_degree = new int[N];
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(adjMatrix[j][i]) input_degree[i]++;
                }
            }

            // Topology Sort 를 위한 큐를 생성 및 전입 차수가 0인 것을 집어넣기
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for(int i = 0 ; i < N ; i++){
                if(input_degree[i] == 0) q.offer(i);
            }

            int numOfRank = 0;
            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()){
                numOfRank++;
                int cur = q.poll();
                sb.append(cur + 1).append(" ");

                for(int i = 0 ; i < N ; i++){
                    if(adjMatrix[cur][i]){ // 현재 위치에서 연결되어있다면
                        input_degree[i]--;
                        if(input_degree[i] == 0){
                            q.offer(i);
                        }
                    }
                }

            }

            if(numOfRank == N){
                System.out.println(sb);
            }else{
                System.out.println("IMPOSSIBLE");
            }

        }

    }

}
