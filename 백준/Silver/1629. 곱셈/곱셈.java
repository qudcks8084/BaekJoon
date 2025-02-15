import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long A, B, C;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    private static long pow(long base, long exp) {
        if (exp == 0) return 1;
        if (exp == 1) return base % C;
        
        long temp = pow(base, exp/2);
        if (exp % 2 == 0) {
            return (temp * temp) % C;
        } else {
            return (((temp * temp) % C) * base) % C;
        }
    }
}