import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, answer;
    static int[] input;
    static boolean[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        input = new int[N];
        arr = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        subset(0, 0, 0);
        System.out.println(answer);
    }

    public static void subset(int depth, int numOfTrue ,int sum){
        if(depth == N){
            if(sum == S && numOfTrue > 0) answer++;
        }else{
            arr[depth] = true;
            subset(depth + 1, numOfTrue+1, sum + input[depth]);
            arr[depth] = false;
            subset(depth + 1, numOfTrue, sum);
        }
    }

}
