import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        int answer = 0;
        int cnt = 0;
        int i = 0;
        
        while (i < M - 2) { 
            if (input[i] == 'I' && input[i+1] == 'O' && input[i+2] == 'I') {
                cnt++;
                if (cnt == N) {
                    answer++;
                    cnt--;
                }
                i += 2; 
            } else {
                cnt = 0;
                i += 1;
            }
        }

        System.out.println(answer);
    }
}