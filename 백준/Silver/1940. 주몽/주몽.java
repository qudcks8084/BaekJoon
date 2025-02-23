import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main  {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(number);

        int answer = 0;
        int lp = 0;
        int rp = N-1;

        while(lp < rp){
            int sum = number[lp] + number[rp];
            if(sum == M) answer++;
            if( sum > M ){ //
                rp--;
            }else{
                lp++;
            }
        }

        System.out.println(answer);
    }
}
