import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bitSet = 0;
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operator = st.nextToken();
            
            if (operator.equals("add")) {
                int target = Integer.parseInt(st.nextToken());
                bitSet |= (1 << target);
            } 
            else if (operator.equals("remove")) {
                int target = Integer.parseInt(st.nextToken());
                bitSet &= ~(1 << target);
            } 
            else if (operator.equals("check")) {
                int target = Integer.parseInt(st.nextToken());
                sb.append((bitSet & (1 << target)) != 0 ? "1\n" : "0\n");
            } 
            else if (operator.equals("toggle")) {
                int target = Integer.parseInt(st.nextToken());
                bitSet ^= (1 << target);
            } 
            else if (operator.equals("all")) {
                bitSet = (1 << 21) - 1;
            } 
            else if (operator.equals("empty")) {
                bitSet = 0;
            }
        }
        
        System.out.println(sb);
    }
}
