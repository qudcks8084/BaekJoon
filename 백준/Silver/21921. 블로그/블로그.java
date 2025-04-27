import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visitors = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            visitors[i] = Integer.parseInt(st.nextToken());
        }


        int sum = 0;
        // 초기 x값만큼의 값을 구한다.
        for(int i = 0 ; i < X ; i++){
            sum += visitors[i];
        }

        int cnt = 1;
        int max = sum;
        for(int i = X ; i < N ; i++){

            sum += visitors[i];
            sum -= visitors[i-X];

            if(sum > max){ // 새로운 최대값 등장
                max = sum;
                cnt = 1;
            }else if(sum == max){
                cnt++;
            }
        }

        if(max == 0){
            System.out.println("SAD");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(cnt);
        System.out.println(sb);
    }
}
