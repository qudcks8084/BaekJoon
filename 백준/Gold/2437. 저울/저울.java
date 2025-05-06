import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        for(int i = 0 ; i < N ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        int sum = 0;
        for(int i = 0 ; i < N ; i++){
            if (sum + 1 < num[i]) {
                System.out.println(sum + 1);
                return;
            }
            sum += num[i];
        }

        System.out.println(sum + 1);
    }
}