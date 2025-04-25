    
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        for(int i = 0 ; i < N ; i++){
            sb.append("Case #").append(i+1).append(": ").append(sc.nextInt() + sc.nextInt()).append("\n");
        }
        System.out.println(sb);
    }
}