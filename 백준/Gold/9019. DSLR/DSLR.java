
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 10_000;
    static char[] answer = {'_', 'D', 'S', 'L', 'R'};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            int[] visited = new int[MAX];
            int[] prev = new int[MAX];

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.offer(start);
            visited[start] = -1;

            while (!q.isEmpty()){
                int cur = q.poll();

                if(cur == target){
                    StringBuilder sb = new StringBuilder();

                    int cnt = cur;
                    while (true){
                        sb.append(answer[visited[cnt]]);
                        cnt = prev[cnt];
                        if(cnt == start) break;
                    }
                    result.append(sb.reverse()).append("\n");
                    break;
                }

                int[] nextList = new int[]{
                        (cur * 2) % MAX,
                        (cur + MAX - 1) % MAX,
                        (cur % 1_000) * 10 + cur / 1000,
                        cur / 10 +  (cur % 10) * MAX / 10
                };

                for (int i = 0 ; i < 4 ; i++){
                    int next = nextList[i];
                    if(visited[next] > 0) continue;
                    visited[next] = i + 1;
                    prev[next] = cur;
                    q.offer(next);
                }
            }
        }

        System.out.println(result);
    }
}
