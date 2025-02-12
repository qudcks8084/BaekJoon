import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 중요 * 무조건 이동범위 최대값으로 설정
        boolean[] visited = new boolean[1000001];
        int numOfAnswer = 0;
        int time = 0;
        boolean find = false;

        if(K == N){ // 둘이 같은 위치에 있는 경우
            sb.append("0\n1");
            System.out.println(sb);
            return;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(K); // 수빈이의 위치 투입
        while (!q.isEmpty()) {
            time++;
            int len = q.size();
            for(int l = 0 ; l < len ; l++){ // 시간별로 나누어서 탐색
                int now = q.poll();
                visited[now] = true;
                if(now + 1 == N || now - 1 == N || now * 2 == N) { // 동생을 찾음
                    numOfAnswer++;
                    find = true;
                    continue;
                }

                int[] next_position = new int[]{now - 1, now + 1, now * 2};
                for(int next : next_position){
                    if(next < 0 || next > 100000) continue; // 최대범위 검색
                    if(visited[next]) continue;
                    q.offer(next);
                }
            }

            if(find) { // 동생을 찾은 경우
                sb.append(time).append("\n").append(numOfAnswer);
                System.out.println(sb);
                return;
            }
        }
    }
}
