
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] C = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int size = 0; // LIS 길이
        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(C, 0, size, arr[i]); // 못찾으면 insertion Point -1
            if(pos >= 0) { // 찾는 값이 있다는 얘기는 값의 중복이 있다는 의미! : 문제에 따라 다르게 처리
                dp[i] = pos;
                C[pos] = arr[i];
            }else { // 음수인 경우
                int idx = Math.abs(pos) - 1;
                dp[i] = idx; 
                C[idx] = arr[i];
                if(idx == size) ++size;
            }
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int cnt = size;
        for(int i = N-1; i >= 0 ; i--) {
            if(dp[i] == cnt - 1) {
                stack.push(arr[i]);
                cnt--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(size);
        System.out.println(sb);
    }
}
