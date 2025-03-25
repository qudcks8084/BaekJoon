import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] max;
    static int[][] nexts = {{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0}, {2,0,1}, {2,1,0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 3;
        max = new int[N];

        boolean[][][] visited = new boolean[201][201][201];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            max[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Integer> set = new HashSet<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[0][0][max[2]] = true;
        q.offer(new int[]{0, 0, max[2]});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == 0) set.add(cur[2]);

            for(int[] n : nexts){
                int[] next = move(cur, n[0], n[1], n[2]);
                if(visited[next[0]][next[1]][next[2]]) continue;
                visited[next[0]][next[1]][next[2]] = true;
                q.offer(next);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i : set) {
            answer.add(i);
        }

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for(int num : answer) sb.append(num).append(" ");
        System.out.println(sb);
    }

    public static int[] move(int[] before, int src, int dst1, int dst2){
        // 현재 상태를 복사하여 결과 배열 생성
        int[] result = Arrays.copyOf(before, 3);

        // 출발 물통이 비어있다면 현재 상태 그대로 반환
        if(before[src] == 0) return result;

        // dst1 물통에 부을 수 있는 물의 양 계산
        int pourAmount1 = Math.min(before[src], max[dst1] - before[dst1]);

        // 출발 물통과 dst1 물통의 물의 양 업데이트
        result[src] -= pourAmount1;
        result[dst1] += pourAmount1;

        // 출발 물통이 비거나 dst1 물통이 가득 차면 현재 상태 반환
        if(result[src] == 0 || result[dst1] == max[dst1]) return result;

        // dst2 물통에 부을 수 있는 물의 양 계산
        int pourAmount2 = Math.min(result[src], max[dst2] - before[dst2]);

        // 출발 물통과 dst2 물통의 물의 양 업데이트
        result[src] -= pourAmount2;
        result[dst2] += pourAmount2;

        // 업데이트된 물통 상태 반환
        return result;
    }

}
