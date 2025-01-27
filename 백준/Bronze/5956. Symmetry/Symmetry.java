import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        System.out.println(SplitCow(N, M, 0, 1));
    }

    private static int SplitCow(int row, int col, int cnt, int r) {
        if (row % 2 != 1 || col % 2 != 1) {
            return cnt;
        }
        return SplitCow(row / 2, col / 2, cnt + r, r * 4);
    }


}