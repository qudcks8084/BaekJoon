
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int MAX = 500_001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[MAX];
        int[] dp = new int[MAX];
        int[] p = new int[MAX];
        Arrays.fill(p, -10);

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            arr[idx] = value;
        }

        int size = 0;
        for(int i = 0 ; i < MAX ; i++){
            if(arr[i] == 0) continue;
            int pos = Arrays.binarySearch(dp, 0 , size,  arr[i]);
            if(pos >= 0){
                dp[pos] = arr[i];
                p[i] = pos;
            }else{
                int idx = Math.abs(pos) - 1;
                p[i] = idx;
                dp[idx] = arr[i];
                if(idx == size) ++size;
            }
        }

        int cnt = size - 1;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = MAX - 1 ; i >= 0 ; i--){
            if(p[i] == -10) continue;
            if(p[i] == cnt){
                cnt--;
            }else{
                stack.push(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N-size).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append("\n");
        }
        System.out.println(sb);
    }
}
