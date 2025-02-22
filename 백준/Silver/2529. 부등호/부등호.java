import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N = 10, K;
    static int[] number;
    static boolean[] operator;
    static boolean[] visited;
    static int min, max;
    static boolean foundMin = false, foundMax = false; // 최소/최대값 찾음 여부

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        operator = new boolean[K];
        number = new int[K + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            operator[i] = st.nextToken().equals(">");
        }

        // 최대값 찾기 (큰 숫자부터 탐색)
        visited = new boolean[N];
        for (int i = 9; i >= 0; i--) {
            visited[i] = true;
            number[0] = i;
            perm2(1, i);
            visited[i] = false;
            if (foundMax) break; // 첫 번째로 찾으면 종료
        }

        for (int number : number) {
            sb.append(number);
        }
        sb.append("\n");

        // 최소값 찾기 (작은 숫자부터 탐색)
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            number[0] = i;
            perm(1, i);
            visited[i] = false;
            if (foundMin) break; // 첫 번째로 찾으면 종료
        }

        for (int number : number) {
            sb.append(number);
        }

        System.out.println(sb);
    }

    public static void perm(int depth, int result) {
        if (foundMin) return; // 이미 찾았으면 종료
        if (depth == K + 1) {
            min = result;
            foundMin = true;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if ((operator[depth - 1] && number[depth - 1] > i) || (!operator[depth - 1] && number[depth - 1] < i)) {
                number[depth] = i;
                perm(depth + 1, result * 10 + i);
            }
            visited[i] = false;
            if (foundMin) return; // 찾았으면 재귀 탈출
        }
    }

    public static void perm2(int depth, int result) {
        if (foundMax) return; // 이미 찾았으면 종료
        if (depth == K + 1) {
            max = result;
            foundMax = true;
            return;
        }
        for (int i = 9; i >= 0; i--) {
            if (visited[i]) continue;
            visited[i] = true;
            if ((operator[depth - 1] && number[depth - 1] > i) || (!operator[depth - 1] && number[depth - 1] < i)) {
                number[depth] = i;
                perm2(depth + 1, result * 10 + i);
            }
            visited[i] = false;
            if (foundMax) return; // 찾았으면 재귀 탈출
        }
    }
}
