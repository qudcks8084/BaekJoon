import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testcase = 1; testcase <= T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;

            PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙
            PriorityQueue<Integer> rpq = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
            HashMap<Integer, Integer> countMap = new HashMap<>(); // 삭제 동기화

            int validCount = 0; // 삽입된 원소 개수

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int target = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    pq.offer(target);
                    rpq.offer(target);
                    countMap.put(target, countMap.getOrDefault(target, 0) + 1);
                    validCount++;
                } else if (command.equals("D")) {
                    if (validCount == 0) continue; // 유효한 데이터가 없으면 삭제 불가능

                    if (target == -1) { // 최소값 삭제
                        removeInvalid(pq, countMap);
                        if (!pq.isEmpty()) {
                            int minVal = pq.poll();
                            countMap.put(minVal, countMap.get(minVal) - 1);
                            validCount--;
                        }
                    } else { // 최대값 삭제
                        removeInvalid(rpq, countMap);
                        if (!rpq.isEmpty()) {
                            int maxVal = rpq.poll();
                            countMap.put(maxVal, countMap.get(maxVal) - 1);
                            validCount--;
                        }
                    }
                }
            }

            // 결과 정리
            if (validCount == 0) {
                sb.append("EMPTY\n");
            } else {
                removeInvalid(rpq, countMap);
                sb.append(rpq.peek()).append(" "); // 최대값
                
                removeInvalid(pq, countMap);
                sb.append(pq.peek()).append("\n"); // 최소값
            }
        }

        System.out.println(sb);
    }

    // PriorityQueue에서 이미 삭제된 값을 제거하는 함수
    private static void removeInvalid(PriorityQueue<Integer> pq, HashMap<Integer, Integer> countMap) {
        while (!pq.isEmpty() && countMap.getOrDefault(pq.peek(), 0) == 0) {
            pq.poll();
        }
    }
}
