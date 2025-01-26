import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int first = (n+1) / 2;
            int second = n - first;
            for(int j = 0 ; j < first ; j++){
                sb.append("* ");
            }
            sb.append("\n");
            sb.append(" ");
            for(int j = 0 ; j < second ; j++){
                sb.append("* ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
