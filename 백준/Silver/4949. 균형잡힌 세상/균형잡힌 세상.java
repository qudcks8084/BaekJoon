import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();
            if(input.equals(".")) break;
            String trimmed = input.replaceAll("[^()\\[\\]]", "");
            Stack<Character> stack = new Stack<>();
            boolean no = false;
            for(int i = 0 ; i < trimmed.length() ; i++){
                char tmp = trimmed.charAt(i);
                if(no) break;
                if(tmp == ')'){
                    if(stack.isEmpty() || stack.pop() != '(') no = true;
                } else if(tmp == ']'){
                    if(stack.isEmpty() || stack.pop() != '[') no = true;
                } else{
                    stack.push(tmp);
                }
            }
            if (no || !stack.isEmpty()) System.out.println("no");
            else System.out.println("yes");
        }

        System.out.println(sb);
    }
}
