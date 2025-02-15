import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] arrayLists = new ArrayList[N+1];
        int[] who_is_my_parent = new int[N+1];

        for(int i = 0 ; i <= N ; i++){
            arrayLists[i] = new ArrayList<Integer>();
        }

        for(int i = 1 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrayLists[a].add(b);
            arrayLists[b].add(a);
        }

        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[1] = true;
        q.offer(1);
        while (!q.isEmpty()) {
            int cur = q.poll();
            ArrayList<Integer> child_list = arrayLists[cur];
            for (int child : child_list) {
                if(visited[child]) continue;
                who_is_my_parent[child] = cur;
                visited[child] = true;
                q.offer(child);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 2 ; i <= N ; i++){
            sb.append(who_is_my_parent[i]).append("\n");
        }

        System.out.println(sb);
    }
}
