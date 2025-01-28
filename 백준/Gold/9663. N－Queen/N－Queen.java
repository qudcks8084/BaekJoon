import java.util.Scanner;

public class Main {
    static int[] rows;  // x 좌표
    static int[] cols;  // y 좌표
    static int N, answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        rows = new int[N];
        cols = new int[N];
        answer = 0;

        DFS(0);

        System.out.println(answer);
    }

    public static void DFS(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        rows[depth] = depth;
        for (int j = 0; j < N; j++) {
            if (isValid(depth, j)) {
                cols[depth] = j;
                DFS(depth + 1);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        for (int i = 0; i < x; i++) {
            if (y == cols[i] || Math.abs(y - cols[i]) == Math.abs(x - i)) {
                return false;
            }
        }
        return true;
    }
}