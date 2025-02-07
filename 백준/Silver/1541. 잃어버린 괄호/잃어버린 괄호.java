import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int minus_index = input.indexOf("-");

        if (minus_index == -1) { // 더하기 밖에 없다.
            int sum = 0;
            StringTokenizer st = new StringTokenizer(input, "+");
            while (st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken());
            }
            System.out.println(sum);
        }
        else{ // 빼기 연산이 존재한다.
            String plus = input.substring(0, minus_index);
            String minus = input.substring(minus_index);

            int sum = 0;
            StringTokenizer st = new StringTokenizer(plus, "+");
            while (st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(minus, "+-");
            while(st.hasMoreTokens()){
                sum -= Integer.parseInt(st.nextToken());
            }

            System.out.println(sum);
        }
    }
}
