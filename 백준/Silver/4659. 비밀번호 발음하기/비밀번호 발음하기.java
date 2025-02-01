import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if(input.equals("end")) break;

            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;


            int num1 = 0; // 모음 연속
            int num2 = 0; // 자음 연속
            for(int i = 0 ; i < input.length() ; i++){
                char tmp = input.charAt(i);
                if (tmp == 'a' || tmp == 'i' || tmp == 'u' || tmp == 'e' || tmp == 'o') {
                    flag1 = true; // 모음 1개 이상 포함
                    num1++; // 연속 모음 ++
                    num2 = 0;
                }else{
                    num1 = 0;
                    num2++;
                }
                if(num1 == 3 || num2 == 3) flag2 = true;

                if (tmp != 'e' && tmp != 'o' && i < input.length() - 1 && tmp == input.charAt((i + 1))) {
                    flag3 = true;
                }


            }

            if(!flag1 || flag2 || flag3) sb.append("<").append(input).append("> is not acceptable.\n");
            else sb.append("<").append(input).append("> is acceptable.\n");
        }
        System.out.println(sb);
    }
}
