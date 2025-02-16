import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int X = Integer.parseInt(br.readLine());

        Arrays.sort(num);

        int answer = 0;
        int lp = 0;
        int rp = N - 1;
        while (lp < rp){
            int sum = num[lp] + num[rp];
            if(sum == X) answer++;
            if(sum < X){
                lp++;
            }else{
                rp--;
            }
        }

        System.out.println(answer);
    }
}
