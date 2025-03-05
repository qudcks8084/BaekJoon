import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long a = Long.parseLong(br.readLine());
        if(a == 4 || a == 7) {
            System.out.println(-1);
            return;
        }
        long[] num = {0, 1, 2, 1, 2};
        long tmp = a / 5 ;
        long b = num[(int) (a % 5)];
        System.out.println(tmp + b);
    }
}
