import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int numOfBlank, N = 9;
    static int[][] map = new int[N][N];
    static int[][] blanks;
    static boolean find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        numOfBlank = 0;
        for(int c = 0; c < N; c++) {
            st = new StringTokenizer(br.readLine());
            for(int r = 0; r < N; r++) {
                map[c][r] = Integer.parseInt(st.nextToken());
                if(map[c][r] == 0) numOfBlank++;
            }
        }

        blanks = new int[numOfBlank][2];

        int index = 0;
        for(int c = 0; c < N; c++) {
            for(int r = 0; r < N; r++) {
                if(map[c][r] == 0) {
                    blanks[index++] = new int[]{c, r};
                }
            }
        }

        backTracking(0);
    }

    public static void backTracking(int depth) {
        if(find) return;
        if(depth == numOfBlank) {
            find = true;
            StringBuilder sb = new StringBuilder();
            for(int c = 0; c < N; c++) {
                for(int r = 0; r < N; r++) {
                    sb.append(map[c][r]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            return;
        }

        int row = blanks[depth][0];
        int col = blanks[depth][1];

        for(int num = 1; num <= 9; num++) {
            if(isValid(row, col, num)) {
                map[row][col] = num;
                backTracking(depth + 1);
                if(find) return;
                map[row][col] = 0;
            }
        }
    }

    public static boolean isValid(int row, int col, int num) {
        // 가로 검사
        for(int x = 0; x < 9; x++) {
            if(map[row][x] == num) return false;
        }

        // 세로 검사
        for(int x = 0; x < 9; x++) {
            if(map[x][col] == num) return false;
        }

        // 3x3 박스 검사
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for(int i = startRow; i < startRow + 3; i++) {
            for(int j = startCol; j < startCol + 3; j++) {
                if(map[i][j] == num) return false;
            }
        }

        return true;
    }
}