import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[2000001];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[Integer.parseInt(st.nextToken())+1000000]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 2000000 ; i++){
            if(arr[i] != 0){
                sb.append(i-1000000).append(" ");
                break;
            }
        }
        for(int i = 2000000 ; i >= 0 ; i--){
            if(arr[i] != 0){
                sb.append(i-1000000);
                break;
            }
        }
        System.out.println(sb);
    }
}
