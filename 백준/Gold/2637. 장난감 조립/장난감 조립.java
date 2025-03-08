import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 각 부품의 상관관계를 저장하기 위해서 저장
        ArrayList<int[]>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        // 각 부품의 전입 차수를 저장하기 위해서 저장
        int[] input_degree = new int[N];

        // 각 부품이 중간, 최종 부품인지 확인하는 boolean 배열
        boolean[] isParentPart = new boolean[N];

        // 각 부품의 내용을 입력받음
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent_part = Integer.parseInt(st.nextToken()) - 1;
            int child_part = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            isParentPart[parent_part] = true; // 부모 부품 처리
            input_degree[child_part]++; // 자식의 전입 차수 추가
            adjList[parent_part].add(new int[]{child_part, value});
        }

        // 각 부품의 총 개수를 기록할 int 배열 생성
        int[] total_part = new int[N];

        // 위상 정렬을 위한 큐 생성과 초기값 추가
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0){
                total_part[i] = 1;
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            // 큐에서 뽑였다는것은 이미 필요한 총 합계가 정리 되었다는 의미
            int cur_value = total_part[cur];
            for(int[] next : adjList[cur]){
                int next_part = next[0];
                int next_value = next[1];
                total_part[next_part] += next_value * cur_value;
                input_degree[next_part]--;
                if(input_degree[next_part] == 0){
                    q.offer(next_part);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            if(isParentPart[i]) continue;
            sb.append(i + 1).append(" ").append(total_part[i]).append("\n");
        }

        System.out.println(sb);

    }
}
