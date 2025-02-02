import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] connection;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connection = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            connection[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int child = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            connection[parent].add(child);
        }

        int[] child_cnt = new int[N + 1];
        int max = 0;

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            child_cnt[i] = child_bfs(i);
            max = Math.max(max, child_cnt[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(child_cnt[i] == max){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    public static int child_bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        int count = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int child : connection[now]) {
                if (!visited[child]) {
                    visited[child] = true;
                    count++;
                    queue.offer(child);
                }
            }
        }

        return count;
    }
}