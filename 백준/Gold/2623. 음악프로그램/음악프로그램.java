import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 리스트 (ArrayList<List<Integer>>로 구현)
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 진입 차수 배열
        int[] input_degree = new int[N];

        // 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfSinger = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken()) - 1; // 첫 가수

            for (int j = 1; j < numOfSinger; j++) {
                int cur = Integer.parseInt(st.nextToken()) - 1;

                // 중복된 간선 방지는 필요 없음 (단순 연결)
                adjList.get(prev).add(cur);
                input_degree[cur]++;

                prev = cur; // 갱신
            }
        }

        // 위상 정렬을 위한 큐
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N]; // 방문 여부
        StringBuilder sb = new StringBuilder();
        int count = 0; // 정렬된 노드 개수 확인

        // 진입 차수 0인 노드 큐에 추가
        for (int i = 0; i < N; i++) {
            if (input_degree[i] == 0) {
                q.offer(i);
                visited[i] = true; // 중복 방문 방지
            }
        }

        // 위상 정렬 수행
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur + 1).append("\n");
            count++;

            for (int next : adjList.get(cur)) {
                input_degree[next]--;

                if (input_degree[next] == 0 && !visited[next]) {
                    q.offer(next);
                    visited[next] = true; // 큐에 넣을 때 방문 처리
                }
            }
        }

        // 모든 노드가 정렬되지 않았으면 사이클 존재
        if (count < N) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}