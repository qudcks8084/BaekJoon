import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] paper;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        answer = new int[3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Check(N, 0, 0);

        for (int num_paper : answer) {
            System.out.println(num_paper);
        }
    }

    public static void Check(int size, int x, int y) {
        if (Same(size, x, y)) {
            answer[paper[x][y] + 1]++;
        } else {
            int len = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Check(len, x + i * len, y + j * len);
                }
            }
        }
    }

    public static boolean Same(int size, int x, int y) {
        int value = paper[x][y];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (paper[x + i][y + j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
