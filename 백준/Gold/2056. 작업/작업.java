import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 연관관계 저장를 위한 adjList 정의
        ArrayList<Integer>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        // 전입 차수를 기록하기 위한 배열을 생성
        int[] input_degree = new int[N];

        // 각 실행 시간을 저장할 배열을 생성
        int[] execution_time = new int[N];

        // 선행 조건 입력 받기
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            execution_time[i] = Integer.parseInt(st.nextToken());
            int numOfPrev = Integer.parseInt(st.nextToken());
            for(int k = 0 ; k < numOfPrev ; k++) {
                input_degree[i]++;
                int previous_condition = Integer.parseInt(st.nextToken()) - 1;
                adjList[previous_condition].add(i);
            }
        }

        // 가장 긴 대기 시간을 저장할 배열을 추가
        int[] max_delay = new int[N];

        // Topology Sort를 위한 Queue 생성 및 초기값 추가
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0) q.offer(new int[]{i, 0});
        }

        // Topology Sort 시작
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cur_work = cur[0]; // 현재 작업
            int cur_delay = cur[1]; // 현재까지의 딜레이

            // 현재 최대값과, 현재까지의 값을 비교해서 값을 갱신
            max_delay[cur_work] = Math.max(max_delay[cur_work], cur_delay);

            for (int next : adjList[cur_work]) {
                // 다음 값의 최대값을 현재 위치의 최대값과 자신의 작업 시간을 합쳐서 갱신
                max_delay[next] = Math.max(max_delay[next], max_delay[cur_work] + execution_time[cur_work]);

                input_degree[next]--;
                if(input_degree[next] == 0) q.offer(new int[]{next, max_delay[next]});
            }
        }

        int max = 0;
        for(int i = 0 ; i < N ; i++){
            max = Math.max(max, execution_time[i] + max_delay[i]);
        }
        System.out.println(max);
    }
}
