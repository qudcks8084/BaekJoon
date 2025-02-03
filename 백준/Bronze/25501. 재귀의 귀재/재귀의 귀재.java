import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            num = 0;
            sb.append(isPalindrome(br.readLine())).append(" ").append(num).append("\n");
        }

        System.out.println(sb);
    }

    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }

    public static int recursion(String s, int l, int r){
        num++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
}