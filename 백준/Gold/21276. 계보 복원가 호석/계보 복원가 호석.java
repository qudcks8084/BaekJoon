import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // index로 이름을 조회하기 위한 방법 / Integer -> String
        String[] name = new String[N];

        // 이름으로 index를 조회하기 위한 자료구조 / String -> Ingeger
        HashMap<String, Integer> map = new HashMap<>();

        // 각각의 데이터를 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            String n = st.nextToken();
            map.put(n, i);
            name[i] = n;
        }

        // 상관 관계를 저장하기 위한 adjList를 생성
        ArrayList<Integer>[] adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        // 입력 차수를 세기 위한 배열 생성
        int[] input_degree = new int[N];

        // 상관 관계를 입력받음
        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int child = map.get(st.nextToken());
            int parent = map.get(st.nextToken());
            input_degree[child]++;
            adjList[parent].add(child);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 입력 차수가 0인 것을 찾아
        int numOfStart = 0;
        ArrayList<String> first = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0){
                numOfStart++;
                first.add(name[i]);
                q.offer(new int[]{i, 0});
            }
        }

        int[] max_Time = new int[N];
        // Topology Sort를 이용해서 조상과의 거리를 찾는다

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_name = cur[0];
            int cur_time = cur[1];
            max_Time[cur_name] = Math.max(max_Time[cur_name], cur_time);
            for(int next : adjList[cur_name]){
                input_degree[next]--;
                max_Time[next] = Math.max(max_Time[next], max_Time[cur_name] + 1);
                if(input_degree[next] == 0) q.offer(new int[]{next, max_Time[next]});
            }
        }

        Collections.sort(first);

        StringBuilder sb = new StringBuilder();
        sb.append(numOfStart).append("\n");
        for(String n : first) sb.append(n).append(" ");
        sb.append("\n");

        String[] order = Arrays.copyOf(name, name.length);

        Arrays.sort(order);

        for(String start_name : order){
            sb.append(start_name).append(" ");
            int parent_time = max_Time[map.get(start_name)];
            ArrayList<String> child_name = new ArrayList<>();
            for (int child_index : adjList[map.get(start_name)]) {
                if(max_Time[child_index] - parent_time == 1){
                    child_name.add(name[child_index]);
                }
            }

            Collections.sort(child_name);
            sb.append(child_name.size()).append(" ");
            for(String each_name : child_name){
                sb.append(each_name).append(" ");
            }
            sb.append("\n");
        }


        System.out.println(sb);

    }
}
