import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] p = new long[N]; 
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            p[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(p);

        long min = Long.MAX_VALUE;
        long[] answer = new long[3];  // Changed to long array
        
        // 1개를 잡고 나머지 2개에 대해서 binary search
        for(int i = 0; i < N; i++) {
            long first = p[i]; 
            long target = -1 * first; 

            // 나머지 2개의 숫자들에 대해서 two pointer
            int lp = i == 0 ? 1 : 0;
            int rp = i == N-1 ? N-2 : N-1;
            while(lp < rp) {
                long sum = p[lp] + p[rp]; 
                long gap = Math.abs(sum + first); 

                // System.out.println(first + " " + p[lp] + " " + p[rp] + " = " + (sum + first) + "[" + gap + "]" );
                if(min > gap) {
                    min = gap;
                    answer = new long[] {first, p[lp], p[rp]}; 
                    // System.out.println("정답 갱신 ! " + Arrays.toString(answer) + " " + min);
                }

                // 2개의 합이 목표보다 크거나 같은 경우 -> rp--;
                if(sum > target) {
                    rp--;
                    if(rp == i) rp--;
                } else {
                    lp++;
                    if(lp == i) lp++;
                }
            }
        }

        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(long num : answer) sb.append(num).append(" ");  // Use long in loop
        System.out.println(sb);
    }
}