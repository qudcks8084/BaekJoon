import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M = 1_001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] height = new int[M];

        int min_index = M + 1;
        int max_index = -1;
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            min_index = Math.min(min_index, index);
            max_index = Math.max(max_index, index);
            height[index] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[M];
        int max = 0;
        for(int i = 0 ; i < M ; i++){
            if(height[i] > max){
                max = height[i];
            }

            answer[i] = Math.max(height[i], max);
        }

        max = 0;
        for(int i = M-1 ; i >= 0 ; i--){
            if(height[i] > max){
                max = height[i];
            }

            answer[i] = Math.min(answer[i], max);
        }

        int total = 0;
        for(int i = min_index ; i <= max_index ; i++){
            total += answer[i];
        }

        System.out.println(total);

    }
}
