import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        long[] data = new long[N];
        for(int i = 0 ; i < N ; i++){
            data[i] = Long.parseLong(br.readLine());
        }

        int len = input.length;
        ArrayDeque<Double> stack = new ArrayDeque<>();
        for(int i = 0 ; i < len ; i++){
            if(Character.isAlphabetic(input[i])){ // 데이터
                stack.push((double)data[input[i]-'A']);
            }else{ // 연산식
                if(input[i] == '+'){
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a + b);
                }else if(input[i] == '-'){
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a - b);
                }else if(input[i] == '*'){
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a * b);
                }else if(input[i] == '/'){
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a / b);
                }
            }
        }
        System.out.printf("%.2f",stack.pop());
    }
}
