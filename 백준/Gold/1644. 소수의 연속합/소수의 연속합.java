import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> soSu = new ArrayList<>();

        // 2부터 N까지의 소수를 구함
        for (int i = 2; i <= N; i++) {
            boolean isPrime = true;
            int sqrt_i = (int) Math.sqrt(i);
            for (int j = 2; j <= sqrt_i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) soSu.add(i);
        }

        // 투 포인터 알고리즘
        int l_p = 0, r_p = 0, sum = 0, answer = 0;

        while (l_p < soSu.size()) {
            if (sum < N && r_p < soSu.size()) {
                sum += soSu.get(r_p++);
            } else {
                sum -= soSu.get(l_p++);
            }

            if (sum == N) answer++;
        }

        System.out.println(answer);
    }
}
