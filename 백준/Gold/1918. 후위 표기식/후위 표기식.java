import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();
        System.out.println(infixToPostfix(infix));
    }

    public static String infixToPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // '(' 제거
            }
            else { // 연산자인 경우
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    if (stack.peek() == '(') break;
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // 스택에 남아있는 모든 연산자를 추가
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') stack.pop();
            else result.append(stack.pop());
        }

        return result.toString();
    }

    // 연산자 우선순위 반환
    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}