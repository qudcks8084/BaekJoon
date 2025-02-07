import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static ArrayDeque<String> calculator;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] nums = input.split("-");

        int sum = 0;
        StringTokenizer plus = new StringTokenizer(nums[0], "+");
        while (plus.hasMoreTokens()) {
            sum += Integer.parseInt(plus.nextToken());
        }

        for(int i = 1 ; i < nums.length ; i++){
            StringTokenizer minus = new StringTokenizer(nums[i], "+");
            while(minus.hasMoreTokens()){
                sum -= Integer.parseInt(minus.nextToken());
            }
        }

        System.out.println(sum);
    }

}
