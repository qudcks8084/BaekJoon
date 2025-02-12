import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < K ; i++){
            sum += arr[i];
        }

        int answer = sum;
        for(int i = 0 ; i < N - K ; i++){
            sum -= arr[i];
            sum += arr[i + K];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
