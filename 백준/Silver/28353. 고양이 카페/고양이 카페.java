import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cat = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            cat[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cat);

        int lp = 0;
        int rp = N - 1;

        int answer = 0;
        while(lp < rp){
            int sum = cat[lp] + cat[rp];

            if(sum > K){
                rp--;
            }else{
                answer++;
                rp--;
                lp++;
            }
        }

        System.out.println(answer);
    }
}
