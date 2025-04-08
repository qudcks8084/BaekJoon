import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[501];
        int[] dp = new int[501];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            arr[idx] = value;
        }

        int size = 0;
        for(int i = 0 ; i <= 500 ; i++){
            if(arr[i] == 0) continue;
            int pos = Arrays.binarySearch(dp, 0 , size,  arr[i]);
            if(pos >= 0) continue;

            int idx = Math.abs(pos) - 1;
            dp[idx] = arr[i];
            if(idx == size) ++size;
        }

        System.out.println(N - size);
    }
}
