import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] arr = new boolean[2000001];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[Integer.parseInt(st.nextToken())+1000000] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 2000000 ; i++){
            if(arr[i]){
                sb.append(i-1000000).append(" ");
                break;
            }
        }
        for(int i = 2000000 ; i >= 0 ; i--){
            if(arr[i]){
                sb.append(i-1000000);
                break;
            }
        }
        System.out.println(sb);
    }
}
