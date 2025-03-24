import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        for(int i = 0 ; i < N ; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);

        long min = 0;
        for(int i = 0 ; i < N ; i++){
            min += Math.abs(num[i] - (i+1));
        }
        System.out.println(min);
    }
}
