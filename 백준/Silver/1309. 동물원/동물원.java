
import java.io.*;
import java.util.*;
public class Main {

    /*

        가로 2칸, 세로 N칸
        칸 안에 사자는 가로, 세로로는 붙어있을 수 없다.

        그렇다면...?

        n == 1 -> 2가지
        x   x   0
        x   0   x

        n == 2 -> 2가지

        x x     x 0     x x     x 0     0 x     0 x
        x x     x x     x 0     0 x     x 0     x x

        즉 한줄에 나올 수 있는 경우의 수는 총 3개
        x 0 x
        x x 0

        dp는 1줄로 해도 될듯

        dp[0] = 3가지
        dp[n] = dp[n-1]

        xx -> 3개 나옴.
        ox -> 2개 늘어남.

            0   1   2   3
        xx  1   3   7   17
        ox  1   2   5   12
        xo  1   2   5   12
            3   7   17  41

    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N][3];

        Arrays.fill(dp[0], 1);

        for(int i = 1 ; i < N ; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][2]) % 9901;
        }

        System.out.println((dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % 9901);
    }
}
