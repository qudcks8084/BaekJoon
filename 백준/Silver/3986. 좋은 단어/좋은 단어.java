import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int numOfGoodWord = 0;

        for (int testcase = 1; testcase <= T; testcase++) {
            char[] input = br.readLine().toCharArray();
            int N = input.length;
            // 단어의 개수가 홀수면 좋은 단어가 될 수 없다.
            if(N % 2 == 1) continue;

            ArrayDeque<Character> stack = new ArrayDeque<>();

            for(int i = 0 ; i < N ; i++){
                if(stack.isEmpty()) stack.push(input[i]);
                else{
                    if (input[i] == stack.peek()) { //넣으려는 것과 같다면
                        stack.pop();
                    }else{
                        stack.push(input[i]);
                    }
                }
            }

            if(stack.isEmpty()) numOfGoodWord++;
        }

        System.out.println(numOfGoodWord);

    }
}
