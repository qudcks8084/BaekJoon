import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] patternA = {0, 0, 1};
    static int[] patternB = {1, 2, 2};
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static HashSet<Long> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        q.add(new int[]{A,B,C});
        set.add(customHash(new int[]{A,B,C}));

        // 돌을 같은 개수로 만들 수 없는 경우
        // 2. 6개의 조합에 대해서 바뀌기 전과 후가 같은 경우
        while (!q.isEmpty()){
            int[] cur = q.poll();

            // System.out.println(Arrays.toString(cur));

            if(checkAnswer(cur)){
                System.out.println(1);
                return;
            }

            // 한턴에 총 6번의 연산 진행
            for(int i = 0 ; i < 3 ; i++){
                // 만약 연산을 하는 경우 둘의 숫자가 바뀌는 경우는 넣지 않음
                int beforeA = cur[patternA[i]];
                int beforeB = cur[patternB[i]];

                // System.out.println(beforeA + "(" +patternA[i] + ")," + beforeB + "(" + patternB[i] + ")");

                // 같으면 연산을 진행하지 못함
                if(beforeA == beforeB) continue;

                // B가 더 작은 경우
                else if(beforeA > beforeB){
                    int afterA = beforeA - beforeB;
                    int afterB = beforeB * 2;

                    if(afterA == beforeB && afterB == beforeA) continue;

                    int[] next = Arrays.copyOf(cur, 3);

                    next[patternA[i]] = afterA;
                    next[patternB[i]] = afterB;

                    if(set.contains(customHash(next))) continue;
                    set.add(customHash(next));
                    q.offer(next);
                }

                // A가 더 작은 경우
                else{
                    int afterA = beforeA * 2;
                    int afterB = beforeB - beforeA;

                    if(afterA == beforeB && afterB == beforeA) continue;

                    int[] next = Arrays.copyOf(cur, 3);

                    next[patternA[i]] = afterA;
                    next[patternB[i]] = afterB;

                    if(set.contains(customHash(next))) continue;
                    set.add(customHash(next));
                    q.offer(next);
                }
            }
        }

        System.out.println(0);
    }

    public static long customHash(int[] num){
        return num[0] * 2000 * 2000 + num[1] * 2000 + num[2];
    }

    // 정답 확인 함수
    public static boolean checkAnswer(int[] num){
        return num[0] == num[1] && num[0] == num[2];
    }
}
