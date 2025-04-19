import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] liquid = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);

        int lp = 0;
        int rp = N - 1;

        int gap = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();

        while(lp < rp){
            int sum = liquid[lp] + liquid[rp];

            if(Math.abs(sum) < gap){ // 현재 0보다 가까운 위치임
                gap = Math.abs(sum);
                sb = new StringBuilder();
                sb.append(liquid[lp]).append(" ").append(liquid[rp]);
                if(sum == 0){
                    System.out.println(sb);
                    return;
                }
            }

            if(sum > 0){
                rp--;
            }else{
                lp++;
            }
        }

        System.out.println(sb);
    }
}
