import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solveSudoku(0, 0);
    }

    static boolean solveSudoku(int row, int col) {
        if(col == 9) {
            row++;
            col = 0;
        }

        if(row == 9) {
            printBoard();
            return true;
        }

        if(board[row][col] != 0) {
            return solveSudoku(row, col + 1);
        }

        for(int num = 1; num <= 9; num++) {
            if(isValid(row, col, num)) {
                board[row][col] = num;
                if(solveSudoku(row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    static boolean isValid(int row, int col, int num) {
        // Check row
        for(int x = 0; x < 9; x++) {
            if(board[row][x] == num) return false;
        }

        // Check column
        for(int x = 0; x < 9; x++) {
            if(board[x][col] == num) return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i + startRow][j + startCol] == num) return false;
            }
        }

        return true;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}