import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] input = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 60개를 나타내는 차원
        boolean[] visited = new boolean[1_000_000];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(input);
        visited[input[0] * 10_000 + input[1] * 100 + input[2]] = true;

        int time = 1;
        while (!q.isEmpty()) {
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                int[] cur = q.poll();

                // 공격을 해
                for(int[] minus : attack){
                    int[] next = new int[3];
                    for(int i = 0 ; i < N ; i++){
                        next[i] = Math.max(cur[i] - minus[i], 0); // 체력이 -가 되는 것을 방지
                    }

                    // 정답 처리 + visited 관리를 위한 index를 계산
                    int index = getIndex(next);

                    // 전부다 0인 경우 index는 무조건 0이다.
                    if(index == 0){
                        System.out.println(time);
                        return;
                    }

                    // 중복 방무을 제거한다.
                    if(visited[index]) continue;
                    visited[index] = true;
                    q.offer(next);
                }
            }
            time++;
        }
    }

    public static int getIndex(int[] target){
        return target[0] * 10_000 + target[1] * 100 + target[2];
    }
}
