import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String patternStr = br.readLine();
        
        String[] parts = patternStr.split("\\*");
        String regexPattern = "^" + parts[0] + ".*" + parts[1] + "$";
        
        // Compile the pattern once for better performance
        Pattern pattern = Pattern.compile(regexPattern);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            Matcher matcher = pattern.matcher(line);
            sb.append(matcher.matches() ? "DA\n" : "NE\n");
        }
        System.out.println(sb);
    }
}