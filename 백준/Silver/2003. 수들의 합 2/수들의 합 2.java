import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int lp = 0;
        int rp = 0;
        int sum = 0;
        int cnt = 0;

        while(true) {
            
            if(sum > M) sum -= num[lp++];
            else if(rp == N) break;
            else sum += num[rp++];

            if(sum == M) cnt++;
        }

        System.out.println(cnt);
    }
}