import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        
        String[] parts = pattern.split("\\*");
        String regexPattern = "^" + parts[0] + ".*" + parts[1] + "$";
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(br.readLine().matches(regexPattern) ? "DA\n" : "NE\n");
        }
        System.out.println(sb);
    }
}