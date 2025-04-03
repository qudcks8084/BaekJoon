import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T ; t++){
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            for(int i = 0 ; i < N ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int d = Integer.parseInt(st.nextToken()) - 1;
                int m = Integer.parseInt(st.nextToken());
                arr[d] = m;
            }

            // 위에서부터 검토, 단. 1번째꺼는 무조건 통과
            int cnt = 1; // 첫 번째 후보자는 항상 통과
            int max_meeting = arr[0];
            for (int i = 1; i < N; i++) {
                if (max_meeting > arr[i]) {
                    cnt++;
                    max_meeting = arr[i];
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
