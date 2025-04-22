import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] list = br.readLine().toCharArray();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : list) {
            if(stack.isEmpty()) stack.add(c);
            else{
                if(stack.peek() == '(' && c == ')') stack.pop();
                else stack.push(c);
            }
        }

        System.out.println(stack.size());

    }
}
