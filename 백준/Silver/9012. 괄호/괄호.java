import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            Stack<Character> stack = new Stack<>();
            boolean No = false;
            for(int j = 0 ; j < input.length() ; j++){
                if(No) break;
                char tmp = input.charAt(j);
                if(tmp == '(') stack.push(tmp);
                else{
                    if(stack.isEmpty()) No = true;
                    else stack.pop();
                }
            }
            if(No || !stack.isEmpty()) sb.append("NO");
            else sb.append("YES");

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
