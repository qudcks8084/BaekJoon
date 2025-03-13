import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] a = new Integer[N];
        Integer[] b = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(b);

        int sum = 0;
        for(int i = 0 ; i < N ; i++){
            sum += a[i] * b[i];
        }
        System.out.println(sum);
    }
}
