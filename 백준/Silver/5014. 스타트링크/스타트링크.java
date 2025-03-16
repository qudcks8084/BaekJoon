import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(S);
        visited[S] = true;

        int time = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                int cur = q.poll();
                if(cur == G){
                    System.out.println(time);
                    return;
                }
                int[] next_floor = {cur + U, cur - D};
                for(int next : next_floor){
                    if(next > F || next < 1 || visited[next] ) continue;
                    visited[next] = true;
                    q.offer(next);
                }
            }
            time++;
        }
        System.out.println("use the stairs");
    }
}
