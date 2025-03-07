import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adjList;
    static int N;
    static int[] time;
    static int[] input_degree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        time = new int[N];
        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        input_degree = new int[N];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()){
                int s = Integer.parseInt(st.nextToken()) - 1;
                if(s != -2){ // -1이 아니라면 _ 선행 노드들
                    input_degree[i]++;
                    adjList[s].add(i);
                }
            }
        }

        // shortest job first 현재 가장 짧은 실행시간을 가지는 프로그램 먼저 실행
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0){
                q.offer(new int[]{i, 0});
            }
        }

        int[] max_delay = new int[N];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int t = cur[1];
            max_delay[c] = Math.max(max_delay[c], t); // 최종 대기 시간을 갱신
            for(int next : adjList[c]){
                input_degree[next]--;
                // 기다려야하는 최대 시간을 계속해서 생신
                max_delay[next] = Math.max(max_delay[next], max_delay[c] + time[c]);
                if(input_degree[next] == 0){
                    q.offer(new int[]{next, cur[1] + time[c]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            sb.append(max_delay[i] + time[i]).append("\n");
        }
        System.out.println(sb);
    }
}